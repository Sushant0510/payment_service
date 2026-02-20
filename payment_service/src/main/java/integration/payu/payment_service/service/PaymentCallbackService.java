package integration.payu.payment_service.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import integration.payu.payment_service.dto.PaymentEvent;
import integration.payu.payment_service.entity.Payment;
import integration.payu.payment_service.entity.PaymentStatus;
import integration.payu.payment_service.entity.OutboxEvent;
import integration.payu.payment_service.repository.PaymentRepository;
import integration.payu.payment_service.repository.OutboxEventRepository;
import integration.payu.payment_service.exception.PaymentNotFoundException;

import jakarta.transaction.Transactional;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.UUID;

import com.fasterxml.jackson.databind.ObjectMapper;

@Service
@Slf4j
@Transactional
@RequiredArgsConstructor
public class PaymentCallbackService {

    private final PaymentRepository repository;
    private final OutboxEventRepository outboxRepository;
    private final ObjectMapper objectMapper; // inject as bean

    public void processCallback(Map<String, String> response) {

        String txnId = response.get("txnid");
        String status = response.get("status");

        Payment payment = repository.findByTxnId(txnId)
                .orElseThrow(() ->
                        new PaymentNotFoundException("Payment not found for txnId: " + txnId)
                );

        // 🔥 Prevent duplicate SUCCESS processing (important)
        if (payment.getStatus() == PaymentStatus.SUCCESS) {
            log.info("Payment already processed for txnId: {}", txnId);
            return;
        }

        if ("success".equalsIgnoreCase(status)) {

            log.info("Payment successful for txnId: {}", txnId);

            payment.setStatus(PaymentStatus.SUCCESS);
            payment.setPayuTransactionId(response.get("mihpayid"));
            repository.save(payment);

            // ✅ Create Business Event
            PaymentEvent paymentEvent = new PaymentEvent();
            paymentEvent.setEventId(UUID.randomUUID().toString());
            paymentEvent.setTxnId(payment.getTxnId());
            paymentEvent.setWalletId(payment.getWalletId());
            paymentEvent.setAmount(payment.getAmount());
            paymentEvent.setStatus("SUCCESS");
            paymentEvent.setTimestamp(Instant.now());

            try {
                String payload = objectMapper.writeValueAsString(paymentEvent);

                // ✅ Create Outbox Record
                OutboxEvent event = new OutboxEvent();
                event.setId(UUID.randomUUID());
                event.setAggregateType("PAYMENT");
                event.setAggregateId(payment.getId().toString());
                event.setEventType("PAYMENT_SUCCESS");
                event.setPayload(payload);
                event.setStatus("NEW");
                event.setCreatedAt(LocalDateTime.now());

                outboxRepository.save(event);

            } catch (Exception e) {
                throw new RuntimeException("Failed to serialize payment event", e);
            }

        } else {

            log.warn("Payment failed for txnId: {}", txnId);

            payment.setStatus(PaymentStatus.FAILED);
            repository.save(payment);
        }
    }
}

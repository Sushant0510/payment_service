package integration.payu.payment_service.service;

import java.util.List;

import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import integration.payu.payment_service.entity.OutboxEvent;
import integration.payu.payment_service.kafka.PaymentEventProducer;
import integration.payu.payment_service.repository.OutboxEventRepository;
import integration.payu.payment_service.repository.PaymentRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@EnableScheduling
@RequiredArgsConstructor
public class OutboxPublisherService {

    
    private final OutboxEventRepository outboxRepository;

    
    private final PaymentEventProducer producer;

    @Scheduled(fixedDelay = 60000)
    @Transactional
    public void publishEvents() {

        List<OutboxEvent> events = outboxRepository.findByStatus("NEW");

        for (OutboxEvent event : events) {
            try {
                producer.send(event.getPayload());
                event.setStatus("SENT");
            } catch (Exception e) {
                // log error, retry next cycle
            }
        }
    }
}


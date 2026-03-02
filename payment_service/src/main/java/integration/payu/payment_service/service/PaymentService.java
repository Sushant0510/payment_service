package integration.payu.payment_service.service;

import integration.payu.payment_service.dto.PayuRequestModel;
import integration.payu.payment_service.entity.Payment;
import integration.payu.payment_service.entity.PaymentStatus;
import integration.payu.payment_service.gateway.PayUGatewayClient;
import integration.payu.payment_service.repository.PaymentRepository;
import integration.payu.payment_service.util.HashGenerator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.Map;

@Service
@Slf4j
@RequiredArgsConstructor
public class PaymentService {

    private final PayUGatewayClient gatewayClient;
    private final HashGenerator hashGenerator;
    private final PaymentRepository paymentRepository;
    private final PaymentFormBuilder paymentFormBuilder;

    @Value("${payu.merchantKey}")
    private String merchantKey;

    public Map<String, String> initiatePayment(PayuRequestModel request) throws JsonProcessingException {
    	
    	
    	// 1️⃣ Save payment with INITIATED status

        Payment payment = new Payment();
        payment.setTxnId(request.getTxnid());
        payment.setWalletId(request.getWalletId());
        payment.setAmount(new BigDecimal(request.getAmount()));
        payment.setStatus(PaymentStatus.PENDING);
        payment.setCreatedAt(Instant.now());
        payment.setUpdatedAt(Instant.now());

        paymentRepository.save(payment);

        String hash = hashGenerator.generateHash(request, merchantKey);

        Map<String, String> requestData =
                gatewayClient.buildRequestMap(request, merchantKey, hash);
        
        log.debug("Prepared payment request data for txnId {}", request.getTxnid());

        return requestData;
    }

    public String createPayUForm(Map<String, String> requestData) {
        return paymentFormBuilder.build(requestData);
    }

}

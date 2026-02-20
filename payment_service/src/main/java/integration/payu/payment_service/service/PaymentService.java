package integration.payu.payment_service.service;

import integration.payu.payment_service.dto.PayuRequestModel;
import integration.payu.payment_service.entity.Payment;
import integration.payu.payment_service.entity.PaymentStatus;
import integration.payu.payment_service.gateway.PayUGatewayClient;
import integration.payu.payment_service.repository.PaymentRepository;
import integration.payu.payment_service.util.HashGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class PaymentService {

    private final PayUGatewayClient gatewayClient;
    private final HashGenerator hashGenerator;
    private final PaymentRepository paymentRepository;

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
        
        System.out.println("request data"+requestData);

        return requestData;
    }
    
    public String createPayUForm(Map<String, String> requestData) {

        StringBuilder html = new StringBuilder();

        html.append("<html>");
        html.append("<body onload='document.forms[0].submit()'>");
        html.append("<form action='https://test.payu.in/_payment' method='post'>");

        requestData.forEach((key, value) -> {
            html.append("<input type='hidden' name='")
                .append(key)
                .append("' value='")
                .append(value)
                .append("'/>");
        });

        html.append("</form>");
        html.append("</body>");
        html.append("</html>");

        return html.toString();
    }

}

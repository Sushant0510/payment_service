package integration.payu.payment_service.controller;

import integration.payu.payment_service.dto.ApiResponseLBA;
import integration.payu.payment_service.dto.PayuRequestModel;
import integration.payu.payment_service.service.PaymentCallbackService;
import integration.payu.payment_service.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.fasterxml.jackson.core.JsonProcessingException;

import java.util.Map;

@RestController
@RequestMapping("/payments")
@RequiredArgsConstructor
public class PaymentController {

    private final PaymentService paymentService;
    private final PaymentCallbackService callbackService;

    private static final Logger logger =
            LoggerFactory.getLogger(PaymentController.class);

    @PostMapping(value = "/initiate", produces = MediaType.TEXT_HTML_VALUE)
    public String initiate(@RequestBody PayuRequestModel request) throws JsonProcessingException {

        Map<String, String> requestData = paymentService.initiatePayment(request);

        return paymentService.createPayUForm(requestData);
    }


    @PostMapping("/callback")
    public ResponseEntity<String> handleCallback(
            @RequestParam Map<String, String> paymentResponse) {

        logger.info("Received callback: {}", paymentResponse);

        callbackService.processCallback(paymentResponse);

        return ResponseEntity.ok("Callback processed");
    }
}

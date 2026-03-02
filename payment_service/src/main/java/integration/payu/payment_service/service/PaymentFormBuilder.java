package integration.payu.payment_service.service;

import java.util.Map;

public interface PaymentFormBuilder {

    String build(Map<String, String> requestData);
}


package integration.payu.payment_service.gateway;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.client.WebClient;

import integration.payu.payment_service.dto.PayuRequestModel;

import java.util.HashMap;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class PayUGatewayClient {

    private final WebClient webClient;

    @Value("${payu.url}")
    private String payuUrl;

    public Map<String, String> buildRequestMap(
            PayuRequestModel request,
            String merchantKey,
            String hash) {

        Map<String, String> requestData = new HashMap<>();

        requestData.put("key", merchantKey);
        requestData.put("txnid", request.getTxnid());
        requestData.put("amount", request.getAmount());
        requestData.put("productinfo", request.getProductinfo());
        requestData.put("firstname", request.getFirstname());
        requestData.put("email", request.getEmail());
        requestData.put("phone", request.getPhone());
        requestData.put("udf1", request.getUdf1());
        requestData.put("udf2", request.getUdf2());
        requestData.put("udf3", request.getUdf3());
        requestData.put("udf4", request.getUdf4());
        requestData.put("udf5", request.getUdf5());
        requestData.put("surl", request.getSurl());
        requestData.put("furl", request.getFurl());
        requestData.put("hash", hash);

        return requestData;
    }

    

}

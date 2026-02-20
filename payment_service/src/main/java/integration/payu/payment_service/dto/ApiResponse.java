package integration.payu.payment_service.dto;

import org.json.JSONObject;

public record ApiResponse<JSONObject>(int statusCode, String message, JSONObject data, boolean error) {
}


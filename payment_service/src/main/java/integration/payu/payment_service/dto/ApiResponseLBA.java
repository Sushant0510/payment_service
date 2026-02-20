package integration.payu.payment_service.dto;

public class ApiResponseLBA {

    private int code;
    private String message;
    private boolean isError;
    private Object data;

    // Constructor
    public ApiResponseLBA(int code, String message, boolean isError, Object data) {
        this.code = code;
        this.message = message;
        this.isError = isError;
        this.data = data;
    }
    

public static ApiResponseLBA success(String message, Object data) {
    return new ApiResponseLBA(200, message, false, data);
}


    // Getters and Setters
    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isError() {
        return isError;
    }

    public void setError(boolean error) {
        isError = error;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}


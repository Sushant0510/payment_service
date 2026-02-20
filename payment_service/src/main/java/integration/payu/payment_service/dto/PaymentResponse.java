package integration.payu.payment_service.dto;

public class PaymentResponse {
    private String mihpayid;
    private String transactionId;
    private String status;
    private String amount;

    // Constructor
    public PaymentResponse(String mihpayid, String transactionId, String status, String amount) {
        this.mihpayid = mihpayid;
        this.transactionId = transactionId;
        this.status = status;
        this.amount = amount;
    }

    // Getters
    public String getMihpayid() {
        return mihpayid;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public String getStatus() {
        return status;
    }

    @Override
	public String toString() {
		return "PaymentResponse [mihpayid=" + mihpayid + ", transactionId=" + transactionId + ", status=" + status
				+ ", amount=" + amount + "]";
	}

	public String getAmount() {
        return amount;
    }
}


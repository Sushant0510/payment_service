package integration.payu.payment_service.dto;

public class TransactionStatusResponse {
    private String transactionStatus;
    private TransactionDetails details;

    public TransactionStatusResponse(String transactionStatus, TransactionDetails details) {
        this.transactionStatus = transactionStatus;
        this.details = details;
    }

	public String getTransactionStatus() {
		return transactionStatus;
	}

	public void setTransactionStatus(String transactionStatus) {
		this.transactionStatus = transactionStatus;
	}

	public TransactionDetails getDetails() {
		return details;
	}

	public void setDetails(TransactionDetails details) {
		this.details = details;
	}

	@Override
	public String toString() {
		return "TransactionStatusResponse [transactionStatus=" + transactionStatus + "]";
	}

    
}

package integration.payu.payment_service.dto;

public class TransactionDetails {
    private String transactionId;
    private String status;

    public TransactionDetails(String transactionId, String status) {
        this.transactionId = transactionId;
        this.status = status;
    }
    
    
    

	public String getTransactionId() {
		return transactionId;
	}




	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}




	public String getStatus() {
		return status;
	}




	public void setStatus(String status) {
		this.status = status;
	}




	@Override
	public String toString() {
		return "TransactionDetails [transactionId=" + transactionId + ", status=" + status + "]";
	}

    // Getters and setters (if needed)
    
    
}

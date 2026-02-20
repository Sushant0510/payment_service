package integration.payu.payment_service.dto;

import lombok.Data;

@Data
public class PayuRequestModel {
	
	private String key;
	private String surl;
	private String furl;
	private String pg;
	private String bankcode;
	private String txnid;
	private String amount;
	private String productinfo;
	private String firstname;
	private String email;
	private String vpa;
	private String phone;
	private String ccnum;
	private String hash;
    private BeneficiaryDetail beneficiarydetail; // Change here
	private String api_version;
	private String udf1; // New field
	private String udf2; // New field
	private String udf3; // New field
	private String udf4; // New field
	private String udf5; // New field
	private Long walletId; // New field for wallet ID
	
	
	
	
     
	

}

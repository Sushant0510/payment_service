package integration.payu.payment_service.service;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
//import com.integration.payu_integration.model.PayuRequestModel;

import integration.payu.payment_service.dto.PayuRequestModel;
import integration.payu.payment_service.dto.PaymentRequestModel.AggregatorSplitInfo;

@Service
public class GenerateHash {
	
//	   @Value("${MerchantKey}")	
//	   private String merchantKey;
//	   
//	   
//	   @Value("${MerchantKeyForPVL}")
//	   private static String MerchantKeyPVL;
	   
	   
	   
//	 //production key
//	 private final String merchantKey = "WFLoPW"; // Replace with your actual key

	
//	@Value("${SALT}")
//	private static String salt;
	
//	private String benefeciaryDetail;

	public static String calculateHash(PayuRequestModel payuNetBanking, String merchantKey) throws JsonProcessingException {
		StringBuilder stringBuilder = new StringBuilder();
	    ObjectMapper objectMapper = new ObjectMapper();


// Concatenating the values as per the specified format
		stringBuilder.append(merchantKey).append("|")
		 .append(payuNetBanking.getTxnid()).append("|")
       .append(payuNetBanking.getAmount()).append("|")
       .append(payuNetBanking.getProductinfo()).append("|")
       .append(payuNetBanking.getFirstname()).append("|")
       .append(payuNetBanking.getEmail()).append("|")
       .append(payuNetBanking.getUdf1()).append("|")
       .append(payuNetBanking.getUdf2()).append("|")
       .append(payuNetBanking.getUdf3()).append("|")
       .append(payuNetBanking.getUdf4()).append("|")
       .append(payuNetBanking.getUdf5()).append("|")
        .append("|||||") // 11 empty fields
//        .append(objectMapper.writeValueAsString(payuNetBanking.getBeneficiarydetail())).append("|")
        .append("eBZRJ2BLEbgYnwBghAIvgLPqwYlQpQfr");   //for staging
//        .append("BETCsA0yhCtmfgD631b7OGifjgJRBCdH");   // for prod
//        .append("HLrXZ3FztMPWBL6PTSfVqATWG77LVGz6");   // for prod

		
// Calculate SHA-512 hash
      System.out.println("String before hash---"+stringBuilder);

		
		return sha512(stringBuilder.toString());
	}
	
	


	

	private static String sha512(String input) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-512");
            byte[] hash = digest.digest(input.getBytes());
            StringBuilder hexString = new StringBuilder();
            for (byte b : hash) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
	}  

}

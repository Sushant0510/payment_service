package integration.payu.payment_service.util;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;

import integration.payu.payment_service.dto.PayuRequestModel;



@Service
public class HashGenerator {
	


	public static String generateHash(PayuRequestModel payuRequest, String merchantKey) throws JsonProcessingException {
		StringBuilder stringBuilder = new StringBuilder();


// Concatenating the values as per the specified format
		stringBuilder.append(merchantKey).append("|")
		 .append(payuRequest.getTxnid()).append("|")
       .append(payuRequest.getAmount()).append("|")
       .append(payuRequest.getProductinfo()).append("|")
       .append(payuRequest.getFirstname()).append("|")
       .append(payuRequest.getEmail()).append("|")
       .append(payuRequest.getUdf1()).append("|")
       .append(payuRequest.getUdf2()).append("|")
       .append(payuRequest.getUdf3()).append("|")
       .append(payuRequest.getUdf4()).append("|")
       .append(payuRequest.getUdf5()).append("|")
        .append("|||||") // 11 empty fields
        .append("eBZRJ2BLEbgYnwBghAIvgLPqwYlQpQfr");   //for staging


		
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

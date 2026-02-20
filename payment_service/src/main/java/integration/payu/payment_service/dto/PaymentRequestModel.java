package integration.payu.payment_service.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import java.util.Map;

public class PaymentRequestModel {

    @JsonProperty("isAmountFilledByCustomer")
    private boolean isAmountFilledByCustomer;

    @JsonProperty("source")
    private String source;

    @JsonProperty("customer")
    private Customer customer;

    @JsonProperty("address")
    private Address address;

    @JsonProperty("aggregatorSplitInfo")
    private AggregatorSplitInfo aggregatorSplitInfo;

    @JsonProperty("invoiceNumber")
    private String invoiceNumber;

    @JsonProperty("subAmount")
    private double subAmount;

    @JsonProperty("description")
    private String description;

    @JsonProperty("isPartialPaymentAllowed")
    private boolean isPartialPaymentAllowed;

    @JsonProperty("maxPaymentsAllowed")
    private int maxPaymentsAllowed;

    @JsonProperty("batchId")
    private String batchId;

    @JsonProperty("expiryDate")
    private String expiryDate;

    @JsonProperty("viaEmail")
    private boolean viaEmail;

    @JsonProperty("viaSms")
    private boolean viaSms;

    @JsonProperty("successURL")
    private String successURL;

    @JsonProperty("failureURL")
    private String failureURL;

    @JsonProperty("enforcePayMethod")
    private String enforcePayMethod;

    @JsonProperty("enforcePG")
    private String enforcePG;

    @JsonProperty("transactionId")
    private String transactionId;

    @JsonProperty("isActive")
    private String isActive;
    
    @JsonProperty("udf")
    private UdfParameters udfparameter;
    

    // Getters and setters
    
    
    
    
    public static class UdfParameters {
    	
    	private String udf1;
    	private String udf2;
    	private String udf3;
    	private String udf4;
    	private String udf5;
    	
    	
    	public String getUdf1() {
    		return udf1;
    	}
    	public void setUdf1(String udf1) {
    		this.udf1 = udf1;
    	}
    	public String getUdf2() {
    		return udf2;
    	}
    	public void setUdf2(String udf2) {
    		this.udf2 = udf2;
    	}
    	public String getUdf3() {
    		return udf3;
    	}
    	public void setUdf3(String udf3) {
    		this.udf3 = udf3;
    	}
    	public String getUdf4() {
    		return udf4;
    	}
    	public void setUdf4(String udf4) {
    		this.udf4 = udf4;
    	}
    	public String getUdf5() {
    		return udf5;
    	}
    	public void setUdf5(String udf5) {
    		this.udf5 = udf5;
    	}
    	@Override
		public String toString() {
			return "UdfParameters [udf1=" + udf1 + ", udf2=" + udf2 + ", udf3=" + udf3 + ", udf4=" + udf4 + ", udf5="
					+ udf5 + "]";
		}
    	
    }	
    
    
  
    

    public UdfParameters getUdfparameter() {
		return udfparameter;
	}

	public void setUdfparameter(UdfParameters udfparameter) {
		this.udfparameter = udfparameter;
	}




	public static class Customer {
        @JsonProperty("name")
        private String name;

        @JsonProperty("email")
        private String email;

        @JsonProperty("phone")
        private String phone;

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getEmail() {
			return email;
		}

		public void setEmail(String email) {
			this.email = email;
		}

		public String getPhone() {
			return phone;
		}

		public void setPhone(String phone) {
			this.phone = phone;
		}

		@Override
		public String toString() {
			return "Customer [name=" + name + ", email=" + email + ", phone=" + phone + "]";
		}

        // Getters and setters
        
		
        
    }

    public static class Address {
        @JsonProperty("line1")
        private String line1;

        @JsonProperty("city")
        private String city;

        @JsonProperty("state")
        private String state;

        @JsonProperty("zipCode")
        private String zipCode;

		public String getLine1() {
			return line1;
		}

		public void setLine1(String line1) {
			this.line1 = line1;
		}

		public String getCity() {
			return city;
		}

		public void setCity(String city) {
			this.city = city;
		}

		public String getState() {
			return state;
		}

		public void setState(String state) {
			this.state = state;
		}

		public String getZipCode() {
			return zipCode;
		}

		public void setZipCode(String zipCode) {
			this.zipCode = zipCode;
		}

		@Override
		public String toString() {
			return "Address [line1=" + line1 + ", city=" + city + ", state=" + state + ", zipCode=" + zipCode + "]";
		}

        // Getters and setters
        
        
    }

    public static class AggregatorSplitInfo {
    	
    	@JsonProperty("splitInfo")
    	 private Map<String, SplitInfo> splitInfo; // map where "8607110" is the key
    	 @JsonProperty("type")
    	    private String type;

    	    @JsonProperty("payuId")
    	    private String payuId;

        // Getters and setters

       
            
      

        public static class SplitInfo {
        	
        	
        	
        	
            @JsonProperty("aggregatorSubTxnId")
            private String aggregatorSubTxnId;

            @JsonProperty("aggregatorSubAmt")
            private String aggregatorSubAmt;

            @JsonProperty("aggregatorCharges")
            private String aggregatorCharges;

            // Getters and setters

            public String getAggregatorSubTxnId() {
                return aggregatorSubTxnId;
            }

            public void setAggregatorSubTxnId(String aggregatorSubTxnId) {
                this.aggregatorSubTxnId = aggregatorSubTxnId;
            }

            public String getAggregatorSubAmt() {
                return aggregatorSubAmt;
            }

            public void setAggregatorSubAmt(String aggregatorSubAmt) {
                this.aggregatorSubAmt = aggregatorSubAmt;
            }

            public String getAggregatorCharges() {
                return aggregatorCharges;
            }

            public void setAggregatorCharges(String aggregatorCharges) {
                this.aggregatorCharges = aggregatorCharges;
            }
            
            
            

			

			@Override
			public String toString() {
				return "SplitInfo [aggregatorSubTxnId=" + aggregatorSubTxnId + ", aggregatorSubAmt=" + aggregatorSubAmt
						+ ", aggregatorCharges=" + aggregatorCharges + "]";
			}
            
            
        }

        public Map<String, SplitInfo> getSplitInfo() {
            return splitInfo;
        }

        public void setSplitInfo(Map<String, SplitInfo> splitInfo) {
            this.splitInfo = splitInfo;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }
        
        public String getPayuId() {
			return payuId;
		}

		public void setPayuId(String payuId) {
			this.payuId = payuId;
		}

		@Override
		public String toString() {
			return "AggregatorSplitInfo [splitInfo=" + splitInfo + ", type=" + type + ", payuId=" + payuId + "]";
		}
        
        
        
    }

    // Add getters and setters for all fields

    public boolean isAmountFilledByCustomer() {
        return isAmountFilledByCustomer;
    }

    public void setAmountFilledByCustomer(boolean amountFilledByCustomer) {
        isAmountFilledByCustomer = amountFilledByCustomer;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public AggregatorSplitInfo getAggregatorSplitInfo() {
        return aggregatorSplitInfo;
    }

    public void setAggregatorSplitInfo(AggregatorSplitInfo aggregatorSplitInfo) {
        this.aggregatorSplitInfo = aggregatorSplitInfo;
    }

    public String getInvoiceNumber() {
        return invoiceNumber;
    }

    public void setInvoiceNumber(String invoiceNumber) {
        this.invoiceNumber = invoiceNumber;
    }

    public double getSubAmount() {
        return subAmount;
    }

    public void setSubAmount(double subAmount) {
        this.subAmount = subAmount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isPartialPaymentAllowed() {
        return isPartialPaymentAllowed;
    }

    public void setPartialPaymentAllowed(boolean partialPaymentAllowed) {
        isPartialPaymentAllowed = partialPaymentAllowed;
    }

    public int getMaxPaymentsAllowed() {
        return maxPaymentsAllowed;
    }

    public void setMaxPaymentsAllowed(int maxPaymentsAllowed) {
        this.maxPaymentsAllowed = maxPaymentsAllowed;
    }

    public String getBatchId() {
        return batchId;
    }

    public void setBatchId(String batchId) {
        this.batchId = batchId;
    }

    public String getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(String expiryDate) {
        this.expiryDate = expiryDate;
    }

    public boolean isViaEmail() {
        return viaEmail;
    }

    public void setViaEmail(boolean viaEmail) {
        this.viaEmail = viaEmail;
    }

    public boolean isViaSms() {
        return viaSms;
    }

    public void setViaSms(boolean viaSms) {
        this.viaSms = viaSms;
    }

    public String getSuccessURL() {
        return successURL;
    }

    public void setSuccessURL(String successURL) {
        this.successURL = successURL;
    }

    public String getFailureURL() {
        return failureURL;
    }

    public void setFailureURL(String failureURL) {
        this.failureURL = failureURL;
    }

    public String getEnforcePayMethod() {
        return enforcePayMethod;
    }

    public void setEnforcePayMethod(String enforcePayMethod) {
        this.enforcePayMethod = enforcePayMethod;
    }

    public String getEnforcePG() {
        return enforcePG;
    }

    public void setEnforcePG(String enforcePG) {
        this.enforcePG = enforcePG;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public String getIsActive() {
        return isActive;
    }

    public void setIsActive(String isActive) {
        this.isActive = isActive;
    }

	@Override
	public String toString() {
		return "PaymentRequestModel [isAmountFilledByCustomer=" + isAmountFilledByCustomer + ", source=" + source
				+ ", customer=" + customer + ", address=" + address + ", aggregatorSplitInfo=" + aggregatorSplitInfo
				+ ", invoiceNumber=" + invoiceNumber + ", subAmount=" + subAmount + ", description=" + description
				+ ", isPartialPaymentAllowed=" + isPartialPaymentAllowed + ", maxPaymentsAllowed=" + maxPaymentsAllowed
				+ ", batchId=" + batchId + ", expiryDate=" + expiryDate + ", viaEmail=" + viaEmail + ", viaSms="
				+ viaSms + ", successURL=" + successURL + ", failureURL=" + failureURL + ", enforcePayMethod="
				+ enforcePayMethod + ", enforcePG=" + enforcePG + ", transactionId=" + transactionId + ", isActive="
				+ isActive + ", udfparameter=" + udfparameter + "]";
	}
    
    
    


    
    
}
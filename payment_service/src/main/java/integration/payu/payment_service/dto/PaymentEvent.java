package integration.payu.payment_service.dto;

import java.math.BigDecimal;
import java.time.Instant;

import lombok.Data;

@Data
public class PaymentEvent {

    private String eventId;

    private String txnId;

    private Long walletId;

    private BigDecimal amount;

    private String status;

    private Instant timestamp;
}


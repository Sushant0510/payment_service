package integration.payu.payment_service.entity;



import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "outbox_event")
@Data
public class OutboxEvent {

    @Id
    private UUID id;

    private String aggregateType;
    private String aggregateId;
    private String eventType;

    @Column(columnDefinition = "TEXT")
    private String payload;

    private String status; // NEW, SENT

    private LocalDateTime createdAt;

    // Getters & Setters
}


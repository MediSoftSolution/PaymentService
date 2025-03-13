package com.hospital.payment.model;
import jakarta.persistence.*;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "payments")
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String transactionId;
    private Double amount;
    private String currency;
    private String paymentMethod;
    private String intent;
    private String description;
    private String status;
}
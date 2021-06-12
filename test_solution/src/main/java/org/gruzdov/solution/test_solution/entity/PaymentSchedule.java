package org.gruzdov.solution.test_solution.entity;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity
@Table(name = "PAYMENT_SCHEDULE")
public class PaymentSchedule implements Serializable {
    private static final long serialVersionUID = -5150857881422152651L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "PAYMENT_SCHEDULE_ID")
    private UUID id;

    @NotNull(message = "Payment date is required field")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "PAYMENT_SCHEDULE_PAYMENT_DATE")
    private LocalDate paymentDate;

    @NotNull(message = "Payment amount limit is required field")
    @Min(value = 1, message = "Must be greater than 1")
    @Max(value = 200030001, message = "Must be less than 200030001")
    @Column(name = "PAYMENT_SCHEDULE_PAYMENT_AMOUNT")
    private BigDecimal paymentAmount;

    @NotNull(message = "Amount of the body is required field")
    @Min(value = 1, message = "Must be greater than 1")
    @Max(value = 200030001, message = "Must be less than 200030001")
    @Column(name = "PAYMENT_SCHEDULE_AMOUNT_OF_THE_BODY")
    private BigDecimal amountOfTheBody;

    @NotNull(message = "Amount of the percent is required field")
    @Min(value = 1, message = "Must be greater than 1")
    @Max(value = 200030001, message = "Must be less than 200030001")
    @Column(name = "PAYMENT_SCHEDULE_AMOUNT_OF_THE_PERCENT")
    private BigDecimal amountOfThePercent;

    @Column(name = "PAYMENT_SCHEDULE_REMAINS")
    private BigDecimal remains;

    @ToString.Exclude
    @ManyToOne
    @JoinColumn(name = "CREDIT_OFFER_ID")
    private CreditOffer creditOffer;
}

package org.gruzdov.solution.test_solution.entity;

import lombok.*;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.util.List;

/**
 * @author Vladislav Gruzdov
 */
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "CREDIT_OFFER")
public class CreditOffer extends BaseUuidEntity {

    private static final long serialVersionUID = -4750857881422152651L;

    @NotBlank(message = "Named is required field")
    @Size(min = 2, message = "Named must be min 2 symbols")
    @Column(name = "CREDIT_OFFER_NAMED")
    private String named;

    @NotNull(message = "Amount is required field")
    @DecimalMin(value = "1.00", message = "Must be greater than 1.00")
    @DecimalMax(value = "200030001.00", message = "Must be less than 200030001.00")
    @Digits(integer = 9, fraction = 2, message = "The numbers before the dot must be no more than 9 and " +
            "after no more than 2, for example: 200030000.99")
    @Column(name = "CREDIT_OFFER_AMOUNT")
    private BigDecimal amount;

    @NotNull(message = "Period In Months is required field")
    @Min(value = 2, message = "Must be greater than 2")
    @Max(value = 480, message = "Must be less than 480")
    @Column(name = "CREDIT_OFFER_PERIOD_IN_MONTHS")
    private Integer periodInMonths;

    @DecimalMax(value = "200030001.00", message = "Must be less than 200030001.00")
    @Digits(integer = 9, fraction = 2, message = "The numbers before the dot must be no more than 9 and " +
            "after no more than 2, for example: 200030000.99")
    @Column(name = "CREDIT_OFFER_FIRST_PAY")
    private BigDecimal firstPay;

    @Column(name = "CREDIT_OFFER_PERCENT_SUM")
    private BigDecimal percentSum;

    @ToString.Exclude
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "BANK_ID")
    private Bank bank;

    @NotNull(message = "Credit is required field")
    @ToString.Exclude
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "CREDIT_ID")
    private Credit credit;

    @ToString.Exclude
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "CLIENT_ID")
    private Client client;

    @ToString.Exclude
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "creditOffer")
    private List<PaymentSchedule> paymentSchedules;

}

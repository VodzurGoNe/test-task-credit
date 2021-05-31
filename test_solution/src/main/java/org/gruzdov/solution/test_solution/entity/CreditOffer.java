package org.gruzdov.solution.test_solution.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "CREDIT_OFFER")
public class CreditOffer {
    private static final long serialVersionUID = -8750857881422152651L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "CREDIT_OFFER_ID")
    private UUID id;

    @NotBlank(message = "Credit Offer Named is required field")
    @Size(min = 2, message = "Named must be min 2 symbols")

    @Column(name = "CREDIT_OFFER_NAMED")
    private String named;

//    @NotBlank(message = "Credit Offer Amount is required field")
    @Min(value = 1, message = "must be greater than 1")
    @Max(value = 200030001, message = "must be less than 200030001")

    @Column(name = "CREDIT_OFFER_AMOUNT")
    private BigDecimal amount;

//    @NotBlank(message = "Credit Offer Period In Months is required field")
    @Min(value = 2, message = "must be greater than 2")
    @Max(value = 480, message = "must be less than 480")

    @Column(name = "CREDIT_OFFER_PERIOD_IN_MONTHS")
    private Integer periodInMonths;

    @Max(value = 200030001, message = "must be less than 200030001")

    @Column(name = "CREDIT_OFFER_FIRSTPAY")
    private BigDecimal firstPay;

    @Column(name = "CREDIT_OFFER_PERCENTSUM")
    private BigDecimal percentSum;

    @ToString.Exclude
    @ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.REFRESH
            , CascadeType.DETACH, CascadeType.MERGE }
            , fetch = FetchType.LAZY)
    @JoinColumn(name = "BANK_ID")
    private Bank bank;

    @ToString.Exclude
    @ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.REFRESH
            , CascadeType.DETACH, CascadeType.MERGE }
            , fetch = FetchType.LAZY)
    @JoinColumn(name = "CREDIT_ID")
    private Credit credit;

    @ToString.Exclude
    @ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.REFRESH
            , CascadeType.DETACH, CascadeType.MERGE }
            , fetch = FetchType.LAZY)
    @JoinColumn(name = "CLIENT_ID")
    private Client client;

    @ToString.Exclude
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "creditOffer")
    private List<PaymentSchedule> paymentSchedules;
}

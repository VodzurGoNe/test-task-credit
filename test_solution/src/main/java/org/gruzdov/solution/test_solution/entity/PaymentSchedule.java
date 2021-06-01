package org.gruzdov.solution.test_solution.entity;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity
@Table(name = "PAYMENT_SCHEDULE")
public class PaymentSchedule {
    private static final long serialVersionUID = -8150857881422152651L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "PAYMENT_SCHEDULE_ID")
    private UUID id;

    @Column(name = "PAYMENT_SCHEDULE_PAYMENT_DATE")
    private LocalDate paymentDate;

    @Column(name = "PAYMENT_SCHEDULE_PAYMENT_AMOUNT")
    private BigDecimal paymentAmount;

    @Column(name = "PAYMENT_SCHEDULE_AMOUNT_OF_THE_BODY")
    private BigDecimal amountOfTheBody;

    @Column(name = "PAYMENT_SCHEDULE_AMOUNT_OF_THE_PERCENT")
    private BigDecimal amountOfThePercent;

    @Column(name = "PAYMENT_SCHEDULE_REMAINS")
    private BigDecimal remains;

    @ToString.Exclude
    @ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.REFRESH,
            CascadeType.DETACH, CascadeType.MERGE }, fetch = FetchType.LAZY)
    @JoinColumn(name = "CREDIT_OFFER_ID")
    private CreditOffer creditOffer;

}

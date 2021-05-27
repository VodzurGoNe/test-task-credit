package org.gruzdov.solution.test_solution.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@NoArgsConstructor
@Data
@Entity
@Table(name = "PAYMENTSCHEDULE")
public class PaymentSchedule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PAYMENTSCHEDULE_ID")
    private Integer id;

    @Column(name = "PAYMENTSCHEDULE_PAYMENT_DATE")
    private LocalDate paymentDate;

    @Column(name = "PAYMENTSCHEDULE_PAYMENT_AMOUNT")
    private BigDecimal paymentAmount;

    @Column(name = "PAYMENTSCHEDULE_AMOUNT_OF_THE_BODY")
    private BigDecimal amountOfTheBody;

    @Column(name = "PAYMENTSCHEDULE_AMOUNT_OF_THE_PERCENT")
    private BigDecimal amountOfThePercent;

    @Column(name = "PAYMENTSCHEDULE_REMAINS")
    private BigDecimal remains;

    @ToString.Exclude
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY) // тут всё верно
    @JoinColumn(name = "CREDITOFFER_ID")
    private CreditOffer creditOffer;

}

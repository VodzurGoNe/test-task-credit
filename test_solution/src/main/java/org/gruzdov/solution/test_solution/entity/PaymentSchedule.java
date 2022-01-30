package org.gruzdov.solution.test_solution.entity;

import lombok.*;
import org.hibernate.Hibernate;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;
import java.util.UUID;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
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
    @DecimalMin(value = "1.00", message = "Must be greater than 1.00")
    @DecimalMax(value = "200030001.00", message = "Must be less than 200030001.00")
    @Digits(integer = 9, fraction = 2, message = "The numbers before the dot must be no more than 9 and " +
            "after no more than 2, for example: 200030000.99")
    @Column(name = "PAYMENT_SCHEDULE_PAYMENT_AMOUNT")
    private BigDecimal paymentAmount;

    @NotNull(message = "Amount of the body is required field")
    @DecimalMin(value = "1.00", message = "Must be greater than 1.00")
    @DecimalMax(value = "200030001.00", message = "Must be less than 200030001.00")
    @Digits(integer = 9, fraction = 2, message = "The numbers before the dot must be no more than 9 and " +
            "after no more than 2, for example: 200030000.99")
    @Column(name = "PAYMENT_SCHEDULE_AMOUNT_OF_THE_BODY")
    private BigDecimal amountOfTheBody;

    @NotNull(message = "Amount of the percent is required field")
    @DecimalMin(value = "1.00", message = "Must be greater than 1.00")
    @DecimalMax(value = "200030001.00", message = "Must be less than 200030001.00")
    @Digits(integer = 9, fraction = 2, message = "The numbers before the dot must be no more than 9 and " +
            "after no more than 2, for example: 200030000.99")
    @Column(name = "PAYMENT_SCHEDULE_AMOUNT_OF_THE_PERCENT")
    private BigDecimal amountOfThePercent;

    @Column(name = "PAYMENT_SCHEDULE_REMAINS")
    private BigDecimal remains;

    @ToString.Exclude
    @ManyToOne
    @JoinColumn(name = "CREDIT_OFFER_ID")
    private CreditOffer creditOffer;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        PaymentSchedule that = (PaymentSchedule) o;

        return id != null && id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(paymentDate, paymentAmount, amountOfTheBody, amountOfThePercent, remains);
    }
}

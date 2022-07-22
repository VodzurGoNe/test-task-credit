package org.gruzdov.solution.test_solution.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "CREDIT")
public class Credit extends BaseUuidEntity {

    private static final long serialVersionUID = -3150854881422152651L;

    @NotBlank(message = "Title is required field")
    @Size(min = 2, message = "Title must be min 2 symbols")
    @Column(name = "CREDIT_TITLE")
    private String title;

    @NotNull(message = "Credit limit is required field")
    @DecimalMin(value = "1.00", message = "Must be greater than 1.00")
    @DecimalMax(value = "200030001.00", message = "Must be less than 200030001.00")
    @Digits(integer = 9, fraction = 2, message = "The numbers before the dot must be no more than 9 and " +
            "after no more than 2, for example: 200030000.99")
    @Column(name = "CREDIT_LIMIT")
    private BigDecimal limit;

    @NotNull(message = "Loan interest rate per year is required field")
    @DecimalMin(value = "5.00", message = "Must be greater than 5.00")
    @DecimalMax(value = "360.00", message = "Must be less than 360.00")
    @Digits(integer = 3, fraction = 2, message = "The numbers before the dot must be no more than 3 and " +
            "after no more than 2, for example: 359.99")
    @Column(name = "CREDIT_PERCENT")
    private BigDecimal percent;

    @ManyToOne
    @JoinColumn(name = "BANK_ID")
    private Bank bank;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "credit")
    private List<CreditOffer> creditOffers;

    @Override
    public String toString() {
        return String.format("Title: %s, %nLimit: %.2f, %nInterest rate (%%): %.2f", title, limit, percent);
    }

}



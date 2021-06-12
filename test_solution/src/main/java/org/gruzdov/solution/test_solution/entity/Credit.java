package org.gruzdov.solution.test_solution.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@NoArgsConstructor
@Data
@Entity
@Table(name = "CREDIT")
public class Credit implements Serializable {
    private static final long serialVersionUID = -3150854881422152651L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "CREDIT_ID")
    private UUID id;

    @NotBlank(message = "Title is required field")
    @Size(min = 2, message = "Title must be min 2 symbols")
    @Column(name = "CREDIT_TITLE")
    private String title;

    @NotNull(message = "Credit limit is required field")
    @Min(value = 1, message = "Must be greater than 1")
    @Max(value = 200030001, message = "Must be less than 200030001")
    @Column(name = "CREDIT_LIMIT")
    private BigDecimal limit;

    @NotNull(message = "Loan interest rate per year is required field")
    @Min(value = 5, message = "Must be greater than 5")
    @Max(value = 360, message = "Must be less than 360")
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



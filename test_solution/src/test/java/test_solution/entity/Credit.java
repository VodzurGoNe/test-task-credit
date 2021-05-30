package test_solution.entity;

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

@NoArgsConstructor
@Data
@Entity
@Table(name = "CREDIT")
public class Credit {
    private static final long serialVersionUID = -8150854881422152651L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "CREDIT_ID")
    private UUID id;

    @NotBlank(message = "title is required field")
    @Size(min = 2, message = "title must be min 2 symbols")

    @Column(name = "CREDIT_TITLE")
    private String title;

    @Min(value = 1, message = "must be greater than 1")
    @Max(value = 200030001, message = "must be less than 200030001")

    @Column(name = "CREDIT_LIMIT")
    private BigDecimal limit;

    @Min(value = 5, message = "must be greater than 5")
    @Max(value = 360, message = "must be less than 360")

    @Column(name = "CREDIT_PERCENT")
    private BigDecimal percent;

    @ToString.Exclude
    @ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.REFRESH
            , CascadeType.DETACH, CascadeType.MERGE })
    @JoinColumn(name = "BANK_ID")
    private Bank bank;

    @ToString.Exclude
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "credit")
    private List<CreditOffer> creditOffers;

}



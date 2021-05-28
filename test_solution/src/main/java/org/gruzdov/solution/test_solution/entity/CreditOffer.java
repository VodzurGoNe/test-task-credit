package org.gruzdov.solution.test_solution.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "CREDITOFFER")
public class CreditOffer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "CREDITOFFER_ID")
    private UUID id;

    @Column(name = "CREDITOFFER_NAMED")
    private String named;

    @Column(name = "CREDITOFFER_AMOUNT")
    private BigDecimal amount;

    @Column(name = "CREDITOFFER_LOANTERM")
    private Integer periodInMonths;

    @Column(name = "CREDITOFFER_FIRSTPAY")
    private BigDecimal firstPay;

    @Column(name = "CREDITOFFER_PERCENTSUM")
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

package org.gruzdov.solution.test_solution.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.UUID;

@NoArgsConstructor
@Data
@Entity
@Table(name = "CREDIT")
public class Credit {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "CREDIT_ID")
    private UUID id;

    @Column(name = "CREDIT_TITLE")
    private String title;

    @Column(name = "CREDIT_LIMIT")
    private BigDecimal limit;

    @Column(name = "CREDIT_PERCENT")
    private BigDecimal percent;

    @ManyToOne
    @JoinColumn(name = "BANK_ID")
    private Bank bank;

}



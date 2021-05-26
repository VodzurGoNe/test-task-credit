package org.gruzdov.solution.test_solution.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "BANK_ID")
    private Bank bank;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CLIENT_ID")
    private Client client;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CREDIT_ID")
    private Credit credit;

//    @OneToMany(mappedBy = "CREDITOFFERS", cascade = CascadeType.ALL)
//    @JoinTable(name = "monthlypayments")
    //@Column(nullable = false)
//    @OneToMany(mappedBy = "creditoffer", cascade = CascadeType.ALL)
//    private List<PaymentSchedule> paymentScheduleList;

}

package org.gruzdov.solution.test_solution.entity;

import lombok.*;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@NoArgsConstructor
@Data
@Entity
@Table(name = "CLIENT")
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "CLIENT_ID")
    private UUID id;

    @Column(name = "CLIENT_FIO")
    private String fio;

    @Column(name = "CLIENT_PHONENUMBER")
    private String phoneNumber;

    @Column(name = "CLIENT_EMAIL")
    private String email;

    @Column(name = "CLIENT_PASSPORTNUMBER")
    private Integer passportNumber;

    @ToString.Exclude
    @ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.REFRESH
            , CascadeType.DETACH, CascadeType.MERGE })
    @JoinColumn(name = "BANK_ID")
    private Bank bank;

    @ToString.Exclude
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "client")
    private List<CreditOffer> creditOffers;

}



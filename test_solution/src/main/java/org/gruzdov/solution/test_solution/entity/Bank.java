package org.gruzdov.solution.test_solution.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@NoArgsConstructor
@Data
@Entity
@Table(name = "BANK")
public class Bank {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "BANK_ID")
    private UUID id;

    @Column(name = "BANK_TITLE")
    private String title;

    //@OneToMany(mappedBy = "bank", cascade = CascadeType.ALL)
    //@JoinTable(name = "credit")//, joinColumns = @JoinColumn(name = "credit_id"))//, mappedBy = "bank")
    //@OneToMany(mappedBy = "bank", cascade = CascadeType.ALL)
    //@OneToMany
    ///@JoinColumn(name = "credit_id")
    //@Column(nullable = false)
    @OneToMany(mappedBy = "bank", cascade = CascadeType.ALL)
    private List<Credit> credits;

    //@Column(nullable = false)
    @OneToMany(mappedBy = "bank", cascade = CascadeType.ALL)
    private List<Client> clients;

}


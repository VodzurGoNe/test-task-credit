package org.gruzdov.solution.test_solution.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

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

    @ToString.Exclude
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "bank")
    private List<Credit> credits;

    @ToString.Exclude
    @OneToMany(mappedBy = "bank", cascade = CascadeType.ALL)
    private List<Client> clients;

}


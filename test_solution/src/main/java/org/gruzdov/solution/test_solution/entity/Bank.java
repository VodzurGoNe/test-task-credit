package org.gruzdov.solution.test_solution.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@NoArgsConstructor
@Data
@Entity
@Table(name = "BANKS")
public class Bank {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private UUID id;

    @Column(name = "TITLE")
    private String title;

//    @OneToMany(cascade = CascadeType.ALL, mappedBy = "CREDITS")
//    private List<Credit> credits;

//    @OneToMany(cascade = CascadeType.ALL, mappedBy = "CLIENTS")
//    private List<Client> clients;
}


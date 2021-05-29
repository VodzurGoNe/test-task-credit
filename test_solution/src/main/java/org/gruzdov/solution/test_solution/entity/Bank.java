package org.gruzdov.solution.test_solution.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.ArrayList;
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

    @NotBlank(message = "Bank Title is required field")
    @Size(min = 2, message = "Title must be min 2 symbols")

    @Column(name = "BANK_TITLE")
    private String title;

    @ToString.Exclude
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "bank")
    private List<Credit> credits;

    @ToString.Exclude
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "bank")
    private List<Client> clients;

    public void addCreditToBank(Credit credit) {
        if (credits == null)
            credits = new ArrayList<>();

        credit.setBank(this);
        credits.add(credit);
    }

    public void addClientToBank(Client client) {
        if (credits == null)
            credits = new ArrayList<>();

        client.setBank(this);
        clients.add(client);
    }
}


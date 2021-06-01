package org.gruzdov.solution.test_solution.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.List;
import java.util.UUID;

@NoArgsConstructor
@Data
@Entity
@Table(name = "BANK")
public class Bank {
    private static final long serialVersionUID = -8150857821422152651L;

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

    @Override public String toString() {
        return "Bank Title: " + getTitle();
    }
}


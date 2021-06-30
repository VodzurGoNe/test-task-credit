package org.gruzdov.solution.test_solution.entity;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "BANK")
public class Bank implements Serializable {
    private static final long serialVersionUID = -1150857821422152651L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "BANK_ID")
    private UUID id;

    @NotBlank(message = "Bank Title is required field")
    @Size(min = 2, message = "Title must be min 2 symbols")
    @Column(name = "BANK_TITLE")
    private String title;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "bank")
    @ToString.Exclude
    private List<Credit> credits;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "bank")
    @ToString.Exclude
    private List<Client> clients;

    @Override
    public String toString() {
        return "Bank Title: " + title;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Bank bank = (Bank) o;

        return id != null && id.equals(bank.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title);
    }
}


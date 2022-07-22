package org.gruzdov.solution.test_solution.entity;

import lombok.*;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.List;

/**
 * @author Vladislav Gruzdov
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "BANK")
public class Bank extends BaseUuidEntity {

    private static final long serialVersionUID = -1150857821422152651L;

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

}


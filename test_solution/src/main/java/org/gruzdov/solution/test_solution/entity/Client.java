package org.gruzdov.solution.test_solution.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
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

    @ManyToOne
    @JoinColumn(name = "BANK_ID")
    private Bank bank;
//    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL)
//    List<Credit> creditList;

    public Client(String fio, String phoneNumber, String email, Integer passportNumber) {
        this.fio = fio;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.passportNumber = passportNumber;
    }
}



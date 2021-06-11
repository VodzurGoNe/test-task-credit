package org.gruzdov.solution.test_solution.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.util.List;
import java.util.UUID;

@NoArgsConstructor
@Data
@Entity
@Table(name = "CLIENT")
public class Client {
    private static final long serialVersionUID = -2150857881422153651L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "CLIENT_ID")
    private UUID id;

    @NotBlank(message = "FIO is required field")
    @Pattern(regexp = "^[a-zA-ZА-Яа-я '-]{2,}([-][a-zA-ZА-ЯЁ][а-яё]{2,})?\\s[a-zA-ZА-ЯЁ][a-zA-ZАа-яё]{2,}" +
            "\\s[a-zA-ZА-ЯЁ][a-zA-ZАа-яё]{2,}$", message = "Please use pattern \"Last Name First Name Middle Name\" " +
            "for example \"Gruzdov Vladislav Grigoryevich\"")
    @Column(name = "CLIENT_FIO")
    private String fio;

    @NotBlank(message = "Phone Number is required field")
    @Pattern(regexp = "\\d{3}-\\d{3}-\\d{2}-\\d{2}",
            message = "Please use pattern XXX-XXX-XX-XX for example \"987-964-39-60\"")
    @Column(name = "CLIENT_PHONE_NUMBER")
    private String phoneNumber;

    @Pattern(regexp = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*" +
            "(\\.[A-Za-z]{2,})$", message = "Please use pattern \"login@siteAddress.domainName\" " +
            "for example \"bestuser@gmail.com\"")
    @Column(name = "CLIENT_EMAIL")
    private String email;

    @NotBlank(message = "Passport Number is required field")
    @Pattern(regexp = "\\d{2}-\\d{2}-\\d{6}", message = "Please use pattern XX-XX-XXXXXX for example \"36-11-502830\"")
    @Column(name = "CLIENT_PASSPORT_NUMBER")
    private String passportNumber;

    @ToString.Exclude
    @ManyToOne
    @JoinColumn(name = "BANK_ID")
    private Bank bank;

    @ToString.Exclude
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "client")
    private List<CreditOffer> creditOffers;

    @Override
    public String toString() {
        return "Last Name, First Name, Middle Name: \n" + fio;
    }
}



package org.gruzdov.solution.test_solution.entity;

import lombok.*;
import org.gruzdov.solution.test_solution.validation.CheckEmail;

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
    private static final long serialVersionUID = -8150857881422153651L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "CLIENT_ID")
    private UUID id;

    @NotBlank(message = "FIO is required field")
    @Pattern(regexp = "^([\\w]+) (\\w{1})* (\\w{1})*$",
            message = "please use pattern \"Last Name First Name Middle Name\"")
    @Column(name = "CLIENT_FIO")
    private String fio;

    @NotBlank(message = "Phone Number is required field")
    @Pattern(regexp = "\\d{3}-\\d{3}-\\d{2}-\\d{2}", message = "please use pattern XXX-XXX-XX-XX")
    @Column(name = "CLIENT_PHONE_NUMBER")
    private String phoneNumber;

    @CheckEmail(value = "random.com", message = "email must ends with \"random.com\"")
    @Column(name = "CLIENT_EMAIL")
    private String email;

    @NotBlank(message = "Passport Number is required field")
    @Pattern(regexp = "\\d{2}-\\d{2}-\\d{6}", message = "please use pattern XX-XX-XXXXXX")
    @Column(name = "CLIENT_PASSPORT_NUMBER")
    private String passportNumber;

    @ToString.Exclude
    @ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.REFRESH,
            CascadeType.DETACH, CascadeType.MERGE })
    @JoinColumn(name = "BANK_ID")
    private Bank bank;

    @ToString.Exclude
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "client")
    private List<CreditOffer> creditOffers;

    @Override public String toString() {
        return "Last Name, First Name, Middle Name : \n" + getFio();
    }
}



package pl.sda.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "PERSON")
@Getter @Setter
public class PersonEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //identity = przerzucenie odpowiedzialnosci za tworzenie ID na baze danych
    @Column(name = "ID")
    private Long id;

    @Column(name = "FIRST_NAME")
    private String firstName;

    @Column(name = "LAST_NAME")
    private String lastName;

    @Column(name = "AGE")
    private Integer age;

    @Column(name = "PESEL")
    private String pesel;

    @Column(name = "PASSWORD")
    private String password;

    @ManyToMany
    private List<AuthorityEntity> authorities;

    @JsonIgnore
    @OneToMany(mappedBy="person", fetch = FetchType.EAGER)
    private List<AccountEntity> accounts;

    @Override
    public String toString() {
        return "PersonEntity{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", age=" + age +
                ", pesel='" + pesel +
                '}';
    }
}

package pl.sda.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
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
    @NotEmpty
    private String firstName;

    @Column(name = "LAST_NAME")
    @NotEmpty
    private String lastName;

    @Column(name = "AGE")
    @NotNull
    private Integer age;

    @Column(name = "PESEL")
    @NotEmpty
    private String pesel;

    @Column(name = "PASSWORD")
    @NotEmpty
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

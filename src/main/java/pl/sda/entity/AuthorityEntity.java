package pl.sda.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "AUTHORITY")
public class AuthorityEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String authorityName;

}

package pl.niewiel.weekopspring_thymeleaf.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.util.Set;

@Data
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private long id;

    @Column
    private String userName;

    @Email
    @Column
    private String email;

    @Column
    private boolean isActive;

    @Column
    private String password;

    @OneToMany(cascade = CascadeType.ALL,targetEntity = Role.class)
    private Set<Role> roles;
}

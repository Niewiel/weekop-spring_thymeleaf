package pl.niewiel.weekopspring_thymeleaf.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.util.HashSet;
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

    @ManyToMany(cascade = CascadeType.PERSIST,targetEntity = Authorities.class)
    @JoinTable(
            name = "user_authorities",
            joinColumns = @JoinColumn(name = "user_fk",referencedColumnName = "id",foreignKey = @ForeignKey(name = "user_key")),
            inverseJoinColumns = @JoinColumn(name = "authority_fk",referencedColumnName = "authority",foreignKey = @ForeignKey(name = "authority_key"))
    )
    private Set<Authorities> roles = new HashSet<>();
}

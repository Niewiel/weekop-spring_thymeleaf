package pl.niewiel.weekopspring_thymeleaf.model;

import lombok.Data;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.constraints.UniqueElements;

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

    @NotEmpty
    @UniqueElements
    @Column
    private String userName;

    @NotEmpty
    @UniqueElements
    @Email
    @Column
    private String email;

    @Column
    @ColumnDefault("TRUE")
    private boolean isActive;

    @NotEmpty
    @Column
    private String password;

    @ManyToMany(cascade = CascadeType.PERSIST,targetEntity = Authority.class)
    @JoinTable(
            name = "user_authorities",
            joinColumns = @JoinColumn(name = "user_fk",referencedColumnName = "id",foreignKey = @ForeignKey(name = "user_key")),
            inverseJoinColumns = @JoinColumn(name = "authority_fk",referencedColumnName = "authority",foreignKey = @ForeignKey(name = "authority_key"))
    )
    private Set<Authority> authorities = new HashSet<>();
}

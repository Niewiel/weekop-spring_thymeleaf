package pl.niewiel.weekopspring_thymeleaf.model;

import org.hibernate.validator.constraints.UniqueElements;
import org.springframework.validation.annotation.Validated;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import java.util.List;


@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private long id;

    @Column(unique = true)
    @UniqueElements( message = "taki użytkownik jest już zarejestrowany")
    private String userName;

    @Min(4)
    @Column(unique = true)
    private String email;

    @Column
    private boolean isActive;


    @Min(6)
    @Column
    private String password;

    @ManyToMany(cascade = CascadeType.PERSIST, targetEntity = Authority.class)
    @JoinTable(
            name = "user_authorities",
            joinColumns = @JoinColumn(name = "user_fk", referencedColumnName = "id", foreignKey = @ForeignKey(name = "user_key")),
            inverseJoinColumns = @JoinColumn(name = "authority_fk", referencedColumnName = "authority", foreignKey = @ForeignKey(name = "authority_key"))
    )
    private List<Authority> authorities;

    public User() {
    }

    public User( String userName,  String email, String password) {
        this.userName = userName;
        this.email = email;
        this.password = password;
        this.isActive = true;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Authority> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(List<Authority> authorities) {
        this.authorities = authorities;
    }
}

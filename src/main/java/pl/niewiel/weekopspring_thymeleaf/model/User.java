package pl.niewiel.weekopspring_thymeleaf.model;

import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.constraints.UniqueElements;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;


@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private long id;

    @UniqueElements(message = "taki użytkownik już istnieje")
    @NotNull(message = "pole wymagane")
    @Size(min = 3,message = "podaj min. 3 znaki")
    @Column
    private String userName;


    @Email(message = "podaj prawidłowy email")
    @Column
    private String email;

    @Column
    private boolean isActive;


    @Column
    @Min(value = 6,message = "hasło musi mieć przynajmniej 6 znaków")
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

    public User(@UniqueElements String userName, @UniqueElements @Email String email, String password) {
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

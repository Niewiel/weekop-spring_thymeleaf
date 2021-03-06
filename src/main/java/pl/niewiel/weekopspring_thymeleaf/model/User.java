package pl.niewiel.weekopspring_thymeleaf.model;


import org.hibernate.validator.constraints.Length;
import pl.niewiel.weekopspring_thymeleaf.validator.unique.user.UniqueUser;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import java.util.List;
import java.util.Objects;


@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private long id;

    @Length(min = 3,max = 25)
    @UniqueUser
    @Column(unique = true)
    private String userName;

    @Length(min = 4,message = "musi zawierać przynajmniej 4 znaki")
    @Email(message = "podaj prawidłowy adres")
    @Column(unique = true)
    private String email;

    @Column
    private boolean isActive;


    @Length(min = 6)
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        User user = (User) o;
        return Objects.equals(getUserName(), user.getUserName()) &&
                Objects.equals(getEmail(), user.getEmail()) &&
                Objects.equals(getAuthorities(), user.getAuthorities());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUserName(), getEmail(), getAuthorities());
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

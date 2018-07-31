package pl.niewiel.weekopspring_thymeleaf.model;

import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import java.net.URL;
import java.sql.Timestamp;

@Data
@Entity
@Table
public class Discovery {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private long discoveryId;


    @Column
    private String name;


    @Column
    private String description;


    @Column
    private String url;


    @ManyToOne(cascade = CascadeType.ALL, targetEntity = User.class)
    private User user;

    @Column
    private Timestamp date;

    @Column
    private int upVote=0;

    @Column
    private int downVote=0;

    public Discovery() {
    }

    public Discovery(String name, String description, String url, User user) {
        this.name = name;
        this.description = description;
        this.url = url;
        this.user = user;
        this.upVote=0;
        this.downVote=0;
    }

    public long getDiscoveryId() {
        return discoveryId;
    }

    public void setDiscoveryId(long discoveryId) {
        this.discoveryId = discoveryId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public int getUpVote() {
        return upVote;
    }

    public void setUpVote(int upVote) {
        this.upVote = upVote;
    }

    public int getDownVote() {
        return downVote;
    }

    public void setDownVote(int downVote) {
        this.downVote = downVote;
    }
}

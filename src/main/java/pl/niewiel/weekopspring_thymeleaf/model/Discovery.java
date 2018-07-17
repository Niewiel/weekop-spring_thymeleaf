package pl.niewiel.weekopspring_thymeleaf.model;

import lombok.Data;

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
    private URL url;

    @ManyToOne(cascade = CascadeType.ALL, targetEntity = User.class)
    private User user;

    @Column
    private Timestamp date;


}

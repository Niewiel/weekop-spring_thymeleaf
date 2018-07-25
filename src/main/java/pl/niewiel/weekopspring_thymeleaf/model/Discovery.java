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

    @NotEmpty
    @Column
    private String name;

    @NotEmpty
    @Column
    private String description;

    @NotEmpty
    @Column
    private URL url;

    @NotEmpty
    @ManyToOne(cascade = CascadeType.ALL, targetEntity = User.class)
    private User user;

    @Column
    private Timestamp date;


}

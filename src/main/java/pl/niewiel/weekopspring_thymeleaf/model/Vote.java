package pl.niewiel.weekopspring_thymeleaf.model;

import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import java.sql.Timestamp;

@Data
@Entity
@Table
public class Vote {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private long vote_id;

    @NotEmpty
    @ManyToOne(targetEntity = Discovery.class)
    private Discovery discovery;

    @NotEmpty
    @ManyToOne(targetEntity = User.class)
    private User user;

    @Column
    private Timestamp date;

    @Column
    private String type;
}

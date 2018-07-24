package pl.niewiel.weekopspring_thymeleaf.model;


import lombok.Data;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "authorities")
public class Authorities {

    @Id
    @Column(name = "authority")
    @ColumnDefault("USER")
    private String roleName;

    @Column
    private String description;
}

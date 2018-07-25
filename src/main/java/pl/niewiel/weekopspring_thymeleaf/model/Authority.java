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
public class Authority {

    @Id
    @Column(name = "authority")
    private String roleName;

    @Column
    private String description;
}

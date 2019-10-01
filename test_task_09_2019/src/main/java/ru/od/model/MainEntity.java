package ru.od.model;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class MainEntity {
    @GeneratedValue @Id
    private Long id;
    private String name;
    @OneToMany private List<SubEntity> subEntities = new ArrayList<>();
}

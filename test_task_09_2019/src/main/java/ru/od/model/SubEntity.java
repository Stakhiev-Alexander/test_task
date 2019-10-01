package ru.od.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class SubEntity {
    @GeneratedValue @Id
    private Long id;
    private String name;
}

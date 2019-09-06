package ru.od.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
public class MainEntity {
    @GeneratedValue
    @Id
    private Long id;
    private String name;
    @OneToMany
    private List<SubEntity> subEntities = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<SubEntity> getSubEntities() {
        return subEntities;
    }

    public void setSubEntities(List<SubEntity> subEntities) {
        this.subEntities = Objects.requireNonNull(subEntities);
    }

}

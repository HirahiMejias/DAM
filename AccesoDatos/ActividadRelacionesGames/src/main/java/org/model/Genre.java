package org.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "genres")
public class Genre {
    @Id
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "name")
    private String name;

    @ManyToMany(mappedBy = "genres")
    private Set<Game> games = new LinkedHashSet<>();

}
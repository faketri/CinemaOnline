package com.Online.Cinema.entity;

import com.Online.Cinema.entity.enums.ECategories;
import jakarta.persistence.*;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Data
@Entity(name = "film")
@Table(schema = "public")
public class Film {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column
    private String name;
    @Column
    private String description;
    @Column
    private String path;
    @ManyToOne()
    @JoinColumn(name="user_id", nullable=false)
    private User user;

    @ElementCollection(targetClass = ECategories.class)
    @CollectionTable(name = "fil_categories",
            joinColumns = @JoinColumn(name = "film_id"))
    private Set<ECategories> categories = new HashSet<ECategories>();

    public Film(
            long id,
            String name,
            String description,
            String path,
            User user,
            ECategories categories)
    {
        this.id = id;
        this.name = name;
        this.description = description;
        this.path = path;
        this.user = user;
        this.categories.add(categories);
    }
    public Film(
            long id,
            String name,
            String description,
            String path,
            User user,
            Set<ECategories> categories)
    {
        this.id = id;
        this.name = name;
        this.description = description;
        this.path = path;
        this.user = user;
        this.categories = categories;
    }

    public void addCategories(ECategories categories){
        this.categories.add(categories);
    }
}

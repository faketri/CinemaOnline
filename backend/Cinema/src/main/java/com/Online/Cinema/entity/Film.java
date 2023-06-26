package com.Online.Cinema.entity;

import com.Online.Cinema.entity.enums.ECategories;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Data
@Entity(name = "film")
@Table(schema = "public")
@AllArgsConstructor
@NoArgsConstructor
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

    @ElementCollection(targetClass = ECategories.class)
    @CollectionTable(name = "film_categories",
            joinColumns = @JoinColumn(name = "film_id"))
    private Set<ECategories> categories = new HashSet<ECategories>();

    public void addCategories(ECategories categories){
        this.categories.add(categories);
    }
}

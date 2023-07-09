package com.Online.Cinema.entity;

import com.Online.Cinema.entity.enums.ECategories;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.*;

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
    private Date releaseDate;
    @Column
    private String studio;

    @OneToMany
    private List<Video> videos = new ArrayList<>();

    @OneToMany
    private List<Image> image = new ArrayList<>();

    @Column
    private String smallDescription;
    @Column
    private String description;

    @ElementCollection(targetClass = ECategories.class)
    @CollectionTable(name = "film_categories",
            joinColumns = @JoinColumn(name = "film_id"))
    private Set<ECategories> categories = new HashSet<ECategories>();

    public void addCategories(ECategories categories){
        this.categories.add(categories);
    }
}

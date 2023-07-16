package com.Online.Cinema.entity;

import com.Online.Cinema.entity.enums.ERole;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@Entity(name = "user")
@Table(schema = "public")
@AllArgsConstructor
@NoArgsConstructor
public class User{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(nullable = false)
    private String email;
    @Column(nullable = false, unique = true)
    private String login;
    @Column(nullable = false, length = 3000)
    private String password;
    @Column
    private String aboutMe;
    @Column
    private String city;
    @OneToOne
    private Image avatar;

    @ElementCollection(targetClass = ERole.class)
    @CollectionTable(name = "user_role",
            joinColumns = @JoinColumn(name = "user_id"))
    private Set<ERole> role = new HashSet<>();

    @ElementCollection(targetClass = Film.class)
    @CollectionTable(name = "user_followed_film",
            joinColumns = @JoinColumn(name = "user_id"))
    private Set<Film> films = new HashSet<>();
}

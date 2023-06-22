package com.Online.Cinema.entity;

import com.Online.Cinema.entity.enums.ERole;
import jakarta.persistence.*;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Data
@Entity(name = "user")
@Table(schema = "public")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(nullable = false)
    private String email;
    @Column(nullable = false)
    private String login;
    @Column(nullable = false, length = 3000)
    private String password;

    @ElementCollection(targetClass = ERole.class)
    @CollectionTable(name = "user_role",
            joinColumns = @JoinColumn(name = "user_id"))
    private Set<ERole> role = new HashSet<ERole>();

    @OneToMany(mappedBy = "user")
    private Set<Film> liked_film = new HashSet<Film>();

    public User(long id, String email, String login, String password, ERole role) {
        this.id = id;
        this.email = email;
        this.login = login;
        this.password = password;
        this.role.add(role);
    }

    public void addRole(ERole role){
        this.role.add(role);
    }
}

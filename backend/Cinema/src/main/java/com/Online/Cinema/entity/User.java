package com.Online.Cinema.entity;

import com.Online.Cinema.entity.enums.ERole;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity(name = "user")
@Table(schema = "public")
@AllArgsConstructor
@NoArgsConstructor
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(nullable = false)
    private String email;
    @Column(nullable = false, unique = true)
    private String login;
    @Column(nullable = false, length = 3000)
    private String password;
    @Column(name = "active")
    private boolean active;

    @ElementCollection(targetClass = ERole.class)
    @CollectionTable(name = "user_role",
            joinColumns = @JoinColumn(name = "user_id"))
    private Set<ERole> role = new HashSet<>();

    @ElementCollection(targetClass = Film.class)
    @CollectionTable(name = "user_followed_film",
            joinColumns = @JoinColumn(name = "user_id"))
    private Set<Film> films = new HashSet<>();

    public User(String login, String password, Collection<? extends GrantedAuthority> collect) {
        this.login = login;
        this.password = password;
        this.role = (Set<ERole>) collect;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return getRole();
    }

    @Override
    public String getUsername() {
        return login;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return active;
    }
}

package com.taskmanager.backend.model;


import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    @Column(unique = true, nullable = false)
    private String username;
    @NotBlank
    @Email
    @Column(unique = true, nullable = false)
    private String email;
    @NotBlank
    @Column(nullable = false)
    private String password;
    private boolean verified = false;
    @OneToMany(mappedBy = "owner", cascade = CascadeType.ALL)
    private Set<Project> projects = new HashSet<>();
    @OneToMany(mappedBy = "createdBy", cascade = CascadeType.ALL)
    private Set<Task> createdTasks = new HashSet<>();
    @OneToMany(mappedBy = "assignedTo", cascade = CascadeType.ALL)
    private Set<Task> assignedTasks = new HashSet<>();
    @Enumerated(EnumType.STRING)
    private Role role;

    public User(String username, String email, String encode) {
        this.username = username;
        this.email = email;
        this.password = encode;
        this.role = Role.USER;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
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
        return verified;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", verified=" + verified +
                ", role=" + role +
                '}';
    }
}

package com.alura.challenge.foro.model.user;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Set;

@Entity
@Table(name = "users")
@EqualsAndHashCode(of = "id")
@AllArgsConstructor
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    @NotBlank
    private String username;
    @Email
    @Column(unique = true)
    @NotBlank
    private String email;
    @NotBlank
    private String password;
    @NotNull
    @Enumerated(EnumType.STRING)
    private Set<ProfileEnum> profiles = Set.of(ProfileEnum.USER);

    public User(){}

    public User(Long id, String username) {
        this.id = id;
        this.username = username;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<ProfileEnum> getProfiles() {
        return profiles;
    }

    public void setProfiles(String profiles) {
        this.profiles.add(ProfileEnum.valueOf(profiles.toUpperCase()));
    }

    public void revokeProfile(String profile){
        this.profiles.remove(ProfileEnum.valueOf(profile.toUpperCase()));
    }
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return profiles.stream()
                .map(profile -> new SimpleGrantedAuthority("ROLE_" + profile.name()))
                .toList();
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
        return true;
    }
}

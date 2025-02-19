package com.salesianos.geekhub.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.NaturalId;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.time.Instant;
import java.util.*;
import java.util.stream.Collectors;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "user_entity")
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @NaturalId
    @Column(unique = true, updatable = false)
    private String username;
    private String email;
    private String password;
    private String name;
    private String surname;
    private String phone;
    private String address;
    private int cp;
    private String gender;
    private Date birthday;
    private String profilePicture;

    @ElementCollection(fetch = FetchType.EAGER)
    private Set<Role> roles;

    @Builder.Default
    private boolean enabled = false;

    private String activationToken;

    @Builder.Default
    private Instant createdAt = Instant.now();

    @ManyToMany(fetch = FetchType.LAZY)
    private Set<Interest> interests = new HashSet<>();



    //helpers
    public void addInterest(Interest i) {
        this.interests.add(i);
        i.getUsers().add(this);
    }

    public void removeInterest(Interest i) {
        this.interests.remove(i);
        i.getUsers().remove(this);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles.stream()
                .map(role -> "ROLE_" + role)
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toSet());
    }





}

package com.salesianos.geekhub.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "user_entity")
public class Interest {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String name;
    private String picture;

    @ManyToMany(mappedBy = "interests", fetch = FetchType.LAZY)
    private Set<User> users = new HashSet<>();


    //helpers
    public void addUser(User u) {
        this.users.add(u);
        u.getInterests().add(this);
    }

    public void removeUser(User u) {
        this.users.remove(u);
        u.getInterests().remove(this);
    }
}

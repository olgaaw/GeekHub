package com.salesianos.geekhub.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "image_entity")
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String imageUrl;

    @ManyToOne
    @MapsId("user_id")
    @JoinColumn(name = "user_id",
            foreignKey = @ForeignKey(name = "fk_user_image"))
    private User user;
}

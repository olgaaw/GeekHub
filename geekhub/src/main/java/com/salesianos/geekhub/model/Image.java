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
    @JoinColumn(name = "post_id",
            foreignKey = @ForeignKey(name = "fk_post_image"))
    private Post post;
}

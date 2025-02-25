package com.salesianos.geekhub.repository;

import com.salesianos.geekhub.model.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface ImageRepository extends JpaRepository<Image, UUID> {
    @Query("SELECT i FROM Image i WHERE i.post.id = :postId")
    List<Image> findImagesByPostId(@Param("postId") UUID postId);

}

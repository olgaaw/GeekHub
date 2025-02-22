package com.salesianos.geekhub.repository;

import com.salesianos.geekhub.model.Image;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ImageRepository extends JpaRepository<Image, UUID> {
}

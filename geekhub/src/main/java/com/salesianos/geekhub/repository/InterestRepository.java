package com.salesianos.geekhub.repository;

import com.salesianos.geekhub.model.Interest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface InterestRepository extends JpaRepository<Interest, UUID> {
}

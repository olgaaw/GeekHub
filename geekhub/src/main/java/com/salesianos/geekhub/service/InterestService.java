package com.salesianos.geekhub.service;

import com.salesianos.geekhub.dto.interest.EditInterestCmd;
import com.salesianos.geekhub.dto.interest.GetInterestDto;
import com.salesianos.geekhub.error.InterestNotFoundException;
import com.salesianos.geekhub.model.Interest;
import com.salesianos.geekhub.repository.InterestRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class InterestService {

    private final InterestRepository interestRepository;

    public Interest create(GetInterestDto interestDto) {

        Interest interest = Interest.builder()
                .name(interestDto.name())
                .picture(interestDto.picture())
                .build();

        return interestRepository.save(interest);
    }

    public Interest edit(UUID id, EditInterestCmd editInterest) {
        Interest interest = interestRepository.findById(id)
                .orElseThrow(() -> new InterestNotFoundException());

        interest.setName(editInterest.name());
        interest.setPicture(editInterest.picture());

        return interestRepository.save(interest);
    }

}

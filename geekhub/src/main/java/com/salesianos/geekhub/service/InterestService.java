package com.salesianos.geekhub.service;

import com.salesianos.geekhub.dto.GetInterestDto;
import com.salesianos.geekhub.model.Interest;
import com.salesianos.geekhub.repository.InterestRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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

}

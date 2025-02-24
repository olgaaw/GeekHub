package com.salesianos.geekhub.service;

import com.salesianos.geekhub.dto.GetInterestDto;
import com.salesianos.geekhub.model.Interest;
import com.salesianos.geekhub.model.User;
import com.salesianos.geekhub.repository.InterestRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class InterestService {

    private final InterestRepository interestRepository;

    public Interest create(GetInterestDto interestDto, User user) {

    }
}

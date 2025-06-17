package com.salesianos.geekhub.service;

import com.salesianos.geekhub.dto.interest.EditInterestCmd;
import com.salesianos.geekhub.dto.interest.GetInterestDto;
import com.salesianos.geekhub.error.InterestNotFoundException;
import com.salesianos.geekhub.error.UserNotFoundException;
import com.salesianos.geekhub.files.exception.StorageException;
import com.salesianos.geekhub.files.model.FileMetadata;
import com.salesianos.geekhub.files.service.StorageService;
import com.salesianos.geekhub.model.Comment;
import com.salesianos.geekhub.model.Interest;
import com.salesianos.geekhub.model.User;
import com.salesianos.geekhub.repository.InterestRepository;
import com.salesianos.geekhub.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class InterestService {

    private final InterestRepository interestRepository;
    private final UserRepository userRepository;
    private final StorageService storageService;

    public List<Interest> findAll() {
        List<Interest> interests = interestRepository.findAll();

        if (interests.isEmpty()) {
            throw new InterestNotFoundException();
        }

        return interests;
    }

    public Interest create(GetInterestDto interestDto, MultipartFile file) {

        Interest interest = Interest.builder()
                .name(interestDto.name())
                .picture(interestDto.picture())
                .build();

        if (file != null && !file.isEmpty()) {
            FileMetadata fileMetadata = storageService.store(file);
            String imageUrl = fileMetadata.getURL();
            if (imageUrl != null) {
                interest.setPicture(imageUrl);
            } else {
                throw new StorageException("Error al subir la imagen");
            }
        }



        return interestRepository.save(interest);
    }

    public Interest edit(UUID id, EditInterestCmd editInterest, MultipartFile file) {
        Interest interest = interestRepository.findById(id)
                .orElseThrow(() -> new InterestNotFoundException());

        interest.setName(editInterest.name());
        if (file != null && !file.isEmpty()) {
            FileMetadata fileMetadata = storageService.store(file);
            String imageUrl = fileMetadata.getURL();
            if (imageUrl != null) {
                interest.setPicture(imageUrl);
            } else {
                throw new StorageException("Error al subir la imagen");
            }
        }

        return interestRepository.save(interest);
    }

    @Transactional
    public void delete (UUID id, User userP) {
        Interest interest = interestRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("No existe interés con el id " + id));

        List<User> users = new ArrayList<>(interest.getUsers());
        for (User user : users) {
            interest.removeUser(user);
            userRepository.save(user);
        }

        interestRepository.deleteById(id);

    }

}

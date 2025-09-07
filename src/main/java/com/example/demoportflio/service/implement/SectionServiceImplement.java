package com.example.demoportflio.service.implement;

import com.example.demoportflio.exception.user.ApiExecptionHandler;

import com.example.demoportflio.model.Section;
import com.example.demoportflio.model.User;
import com.example.demoportflio.repository.SectionRepository;
import com.example.demoportflio.repository.UserRepository;
import com.example.demoportflio.service.SectionService;

import org.springframework.stereotype.Service;


import java.util.List;


@Service
public class SectionServiceImplement implements SectionService {

    private final SectionRepository sectionRepository;
    private final UserRepository userRepository;

    public SectionServiceImplement(SectionRepository sectionRepository, UserRepository userRepository) {
        this.sectionRepository = sectionRepository;
        this.userRepository=userRepository;
    }

    @Override
    public Section createSection(Section section) {

        Long userId = section.getUser().getId();
        User userFromDb = userRepository.findById(userId)
                .orElseThrow(() -> new ApiExecptionHandler.UserNotFoundException("Utilisateur introuvable avec l'ID : " + userId));

        // Réassocier le user complet à la section
        section.setUser(userFromDb);
        return sectionRepository.save(section);
    }



    @Override
    public List<Section> getSections() {
        return sectionRepository.findAll();
    }

    @Override
    public Section getSectionById(Long id) {
        return sectionRepository.findById(id).orElseThrow(() -> new ApiExecptionHandler.UserNotFoundException("Utilisateur introuvable"));

    }

    @Override
    public Section updateSection(Section section) {
        if (!sectionRepository.existsById( section.getId()) ){
            throw new ApiExecptionHandler.UserNotFoundException("Section avec id " + section.getId() + " n'existe pas");
        }

        return sectionRepository.save(section);
    }

    @Override
    public Section deleteSection(Long id) {

            Section section = sectionRepository.findById(id)
                    .orElseThrow(() -> new ApiExecptionHandler.UserNotFoundException("Utilisateur introuvable"));
            sectionRepository.delete(section);
            return section;

    }
}

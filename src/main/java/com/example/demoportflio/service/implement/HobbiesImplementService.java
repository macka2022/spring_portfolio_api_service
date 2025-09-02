package com.example.demoportflio.service.implement;

import com.example.demoportflio.exception.user.ApiRequestException;

import com.example.demoportflio.model.Hobbies;
import com.example.demoportflio.repository.HobbiesRRepository;
import com.example.demoportflio.repository.SectionRepository;
import com.example.demoportflio.service.HobbiesService;

import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class HobbiesImplementService implements HobbiesService {
    private final HobbiesRRepository hobbiesRRepository;
    private final SectionRepository sectionRepository;

    public HobbiesImplementService(HobbiesRRepository hobbiesRRepository, SectionRepository sectionRepository) {
        this.hobbiesRRepository = hobbiesRRepository;
        this.sectionRepository = sectionRepository;
    }

    @Override
    public List<Hobbies> getAllHobbies() {

        return hobbiesRRepository.findAll();
    }

    @Override
    public Hobbies getHobbies(Long id) {

        return hobbiesRRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Hobbies avec id " + id + " non trouvée"));
    }


    @Override
    public Hobbies addHobbies(Hobbies hobbies) {
        if (!sectionRepository.existsById(  hobbies.getSection().getId()) ) {
            throw new RuntimeException("Section avec id " + hobbies.getId() + " n'existe pas");
        }

        return hobbiesRRepository.save(hobbies);
    }

    @Override
    public Hobbies updateHobbies(Hobbies hobbies) {
        if (!sectionRepository.existsById(hobbies.getSection().getId()) ){
            throw new RuntimeException("Section avec id " + hobbies.getId() + " n'existe pas");
        }

        return hobbiesRRepository.save(hobbies);

    }

    @Override
    public Hobbies deleteHobbies(Long id) {
        Hobbies hobbies = hobbiesRRepository.findById(id)
                .orElseThrow(() -> new ApiRequestException("La section avec id " + id + " est introuvable.") );
        hobbiesRRepository.delete(hobbies);
        return  hobbies;
    }





}

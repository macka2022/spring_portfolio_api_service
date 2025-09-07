package com.example.demoportflio.service.implement;

import com.example.demoportflio.exception.user.ApiExecptionHandler;

import com.example.demoportflio.model.Langues;
import com.example.demoportflio.model.Section;
import com.example.demoportflio.repository.LanguesRepository;
import com.example.demoportflio.repository.SectionRepository;
import com.example.demoportflio.service.LanguesService;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class LanguesServiceImplement  implements LanguesService {
    private final SectionRepository sectionRepository;
    private final LanguesRepository languesRepository;

    public LanguesServiceImplement(SectionRepository sectionRepository, LanguesRepository languesRepository) {
        this.sectionRepository = sectionRepository;
        this.languesRepository = languesRepository;
    }

    @Override
    public Langues createLangues(Langues langues) {
        Long langueId= langues.getSection().getId();
        Section languesFromDb = sectionRepository.findById((langueId))
                .orElseThrow(() -> new RuntimeException("Section introuvable  avec l'ID : " + langueId));

        langues.setSection(languesFromDb);
        return languesRepository.save(langues);

    }

    @Override
    public List<Langues> getAllLangues() {
        return languesRepository.findAll()  ;
    }

    @Override
    public Langues getLanguesById(Long id) {
        return languesRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("langues avec id " + id + " non trouvée"));
    }

    @Override
    public Langues updateLangues(Langues langues) {
        if (!sectionRepository.existsById(   langues.getId())) {
            throw new RuntimeException("Section avec id " + langues.getId() + " n'existe pas");
        }
        return languesRepository.save(langues);
    }

    @Override
    public Langues deleteLangues(Long id) {
        Langues langues = languesRepository.findById(id)
                .orElseThrow(() -> new ApiExecptionHandler.UserNotFoundException("Experience introuvable"));
        languesRepository.delete(langues);
        return langues;
    }
}

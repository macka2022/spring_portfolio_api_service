package com.example.demoportflio.service.implement;

import com.example.demoportflio.exception.user.ApiExecptionHandler;
import com.example.demoportflio.model.Apropos;
import com.example.demoportflio.model.Experience;
import com.example.demoportflio.model.Section;
import com.example.demoportflio.repository.AproposRepository;
import com.example.demoportflio.repository.ExperienceRepository;
import com.example.demoportflio.repository.SectionRepository;
import com.example.demoportflio.service.AproposService;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class AproposServiceImplement implements AproposService {

    private final AproposRepository aproposRepository;
    private final SectionRepository sectionRepository;

    public AproposServiceImplement(AproposRepository aproposRepository, SectionRepository sectionRepository) {

        this.aproposRepository = aproposRepository;
        this.sectionRepository = sectionRepository;
    }


    @Override
    public Apropos createApropos(Apropos apropos) {

        Long ExperienceId = apropos.getSection().getId();


        Section ExperienceFromDb = sectionRepository.findById(ExperienceId)
                .orElseThrow(() -> new ApiExecptionHandler.UserNotFoundException("Section introuvable  avec l'ID : " + ExperienceId));
        apropos.setSection(ExperienceFromDb);
        return aproposRepository.save(apropos);
    }



    @Override
    public List<Apropos> getAllApropos() {
        return aproposRepository.findAll();
    }

    @Override
    public Apropos getAproposById(Long id) {
        return aproposRepository.findById(id)
                .orElseThrow(() -> new ApiExecptionHandler.UserNotFoundException("Section avec id " + id + " non trouvée"));
    }

    @Override
    public Apropos updateApropos(Apropos apropos) {
        if (!sectionRepository.existsById(apropos.getId() )) {
            throw new ApiExecptionHandler.UserNotFoundException("Section avec id " + apropos.getId() + " n'existe pas");
        }
        return aproposRepository.save(apropos);
    }

    @Override
    public Apropos deleteApropos(Long id) {
        Apropos apropos = aproposRepository.findById(id)
                .orElseThrow(() -> new ApiExecptionHandler.UserNotFoundException("Utilisateur introuvable"));
        aproposRepository.delete(apropos);
        return apropos;
    }
}

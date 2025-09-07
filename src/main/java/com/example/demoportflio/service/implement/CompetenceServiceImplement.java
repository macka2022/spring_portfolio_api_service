package com.example.demoportflio.service.implement;

import com.example.demoportflio.exception.user.ApiExecptionHandler;

import com.example.demoportflio.model.Competence;

import com.example.demoportflio.model.Section;
import com.example.demoportflio.repository.CompetenceRepository;
import com.example.demoportflio.repository.SectionRepository;
import com.example.demoportflio.service.CompetenceService;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class CompetenceServiceImplement implements CompetenceService {
    private final CompetenceRepository competenceRepository;
    private final SectionRepository sectionRepository;

    public CompetenceServiceImplement(CompetenceRepository competenceRepository, SectionRepository sectionRepository) {
        this.competenceRepository = competenceRepository;
        this.sectionRepository = sectionRepository;
    }

    @Override
    public List<Competence> findAllCompetence() {
        return competenceRepository.findAll();
    }

    @Override
    public List<Competence> createCompetence(Competence competence) {
        if (competence.getSection() == null) {
            throw new ApiExecptionHandler.UserNotFoundException("Section manquante pour cette certification");
        }

      Long  certificatId = competence.getSection().getId();
        Section certificatFromDb = sectionRepository.findById(certificatId)
                .orElseThrow(() -> new ApiExecptionHandler.UserNotFoundException(
                        "Section introuvable avec l'ID : " + certificatId));

        boolean exists = competenceRepository.existsByNiveauAndDescriptionAndTypesAndSectionAndNom(
                competence.getNiveau(),
                competence.getDescription(),
                competence.getTypes(),
                competence.getSection(),
                competence.getNom()
        );

        if (exists) {
            throw new ApiExecptionHandler.UserAlreadyExistsException("Cette certification existe déjà !");
        }

        List<Competence> savedSaveCompetences = new ArrayList<>();

        if (competence.getTypes() == Competence.Type.BOTH) {

            String groupId = UUID.randomUUID().toString();
            Competence compCv = cloneCompetence(competence, Competence.Type.CV, certificatFromDb);
            compCv.setGroupId(groupId);
            savedSaveCompetences.add(competenceRepository.save(compCv));

            // Copie pour PORTFOLIO
            Competence comPortfolio = cloneCompetence(competence, Competence.Type.PORTFOLIO, certificatFromDb);
            comPortfolio.setGroupId(groupId);
            savedSaveCompetences.add(competenceRepository.save(comPortfolio));
        } else {
            competence.setSection(certificatFromDb);
            savedSaveCompetences.add(competenceRepository.save(competence));
        }

        return savedSaveCompetences;
    }

    @Override
    public Competence updateCompetence(Competence competence) {

        Long ExperienceId = competence.getSection().getId();
        Section ExperienceFromDb = sectionRepository.findById(ExperienceId)
                .orElseThrow(() -> new ApiExecptionHandler.UserAlreadyExistsException("Section introuvable  avec l'ID : " + ExperienceId));
        competence.setSection(ExperienceFromDb);
        return competenceRepository.save(competence);
    }


    @Override
    public List<Competence> deleteCompetence(String groupId) {
        System.out.println("groupId: [" + groupId + "]");
        if (groupId == null || groupId.isEmpty()) {
            throw new ApiExecptionHandler.UserNotFoundException("GroupId invalide ou manquant.");
        }
        List<Competence> deleteteCompts = competenceRepository.findByGroupId(groupId);
        if (deleteteCompts.isEmpty() ) {
            System.out.println("groupId is null or empty");
            throw new ApiExecptionHandler.UserNotFoundException(
                    "Aucune certification trouvée : " + groupId
            );
        }
        competenceRepository.deleteAll(deleteteCompts);
        return deleteteCompts;
    }

    @Override
    public Competence findCompetenceById(Long id) {
         return competenceRepository.findById(id)
                .orElseThrow(() -> new ApiExecptionHandler.UserNotFoundException("Compétence non trouvée avec l'id : " + id)) ;
    }


    private Competence cloneCompetence(Competence source, Competence.Type type, Section section) {
        Competence target = new Competence();
        target.setNom(source.getNom());
        target.setNiveau(source.getNiveau());

        target.setTypes(type);
        target.setDescription(source.getDescription());
        target.setSection(section);


        return target;
    }

    @Override
    public List<Competence> findByType(String type) {
        Competence.Type compType;
        try {
            compType = Competence.Type.valueOf(type.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new ApiExecptionHandler.UserNotFoundException(
                    "Type invalide. Utilise CV, PORTFOLIO ou BOTH"
            );
        }

        List<Competence> compts = competenceRepository.findByTypes(compType);

        if (compts.isEmpty()) {
            throw new ApiExecptionHandler.UserNotFoundException(
                    "Aucune certification trouvée pour le type : " + compType
            );
        }


        return compts;
    }


}

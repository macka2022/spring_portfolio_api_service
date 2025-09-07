package com.example.demoportflio.service.implement;

import com.example.demoportflio.exception.user.ApiExecptionHandler;

import com.example.demoportflio.model.Experience;
import com.example.demoportflio.model.Section;

import com.example.demoportflio.repository.ExperienceRepository;
import com.example.demoportflio.repository.SectionRepository;
import com.example.demoportflio.service.ExperienceService;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class ExperienceServiceImplement  implements ExperienceService {

    private final ExperienceRepository experienceRepository;
    private final SectionRepository sectionRepository;

    public ExperienceServiceImplement(ExperienceRepository experienceRepository, SectionRepository sectionRepository) {

        this.experienceRepository = experienceRepository;
        this.sectionRepository = sectionRepository;
    }


    @Override
    public List<Experience> findByType(String type) {
        Experience.Type certType;
        try {
            certType = Experience.Type.valueOf(type.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new ApiExecptionHandler.UserNotFoundException(
                    "Type invalide. Utilise CV, PORTFOLIO ou BOTH"
            );
        }

        List<Experience> expers = experienceRepository.findByTypes(certType);

        if (expers.isEmpty()) {
            throw new ApiExecptionHandler.UserNotFoundException(
                    "Aucune experiences trouvée pour le type : " + certType
            );
        }


        return expers;
    }

    @Override
    public List<Experience> createExperience(Experience experience) {

        if (experience.getSection() == null) {
            throw new ApiExecptionHandler.UserNotFoundException("Section manquante pour cette certification");
        }

       Long experienceId = experience.getSection().getId();
        Section experienceFromDB = sectionRepository.findById(experienceId)
                .orElseThrow(() -> new ApiExecptionHandler.UserNotFoundException(
                        "Section introuvable avec l'ID : " + experienceId));

        boolean exists = experienceRepository.existsByTitleAndTypesAndDescriptionAndLocalisationAndDateDebutAndDateFinAndMessionAndRealisationAndEntrepriseAndSection(
                experience.getTitle(),
                experience.getTypes(),
                experience.getDescription(),
                experience.getLocalisation(),
                experience.getDateDebut(),
                experience.getDateFin(),
                experience.getMession(),
                experience.getRealisation(),
                experience.getEntreprise(),
                experience.getSection()
        );

        if (exists) {
            throw new ApiExecptionHandler.UserAlreadyExistsException("Cette experience existe déjà !");
        }

        List<Experience> savedExperiences = new ArrayList<>();

        if (experience.getTypes() == Experience.Type.BOTH) {

            String groupId = UUID.randomUUID().toString();
            Experience experCV = cloneExperience(experience, Experience.Type.CV, experienceFromDB);
            experCV.setGroupId(groupId);
            savedExperiences.add(experienceRepository.save(experCV));

            // Copie pour PORTFOLIO
            Experience experPortfolio = cloneExperience(experience, Experience.Type.PORTFOLIO, experienceFromDB);
            experPortfolio.setGroupId(groupId);
            savedExperiences.add(experienceRepository.save(experPortfolio));
        }else{
            experience.setSection(experienceFromDB);
            savedExperiences.add(experienceRepository.save(experience));
        }

        return savedExperiences;



    }



    @Override
    public List<Experience> getExperiences() {
        return experienceRepository.findAll();
    }

    @Override
    public Experience getExperienceById(Long id) {
        return experienceRepository.findById(id)
                .orElseThrow(() -> new ApiExecptionHandler.UserNotFoundException("Section avec id " + id + " non trouvée"));
    }

    @Override
    public Experience updateExperience(Experience experience) {
        if (!experienceRepository.existsById(experience.getId())) {
            throw new ApiExecptionHandler.UserNotFoundException(
                    "Expérience avec id " + experience.getId() + " n'existe pas");
        }

        if (experience.getSection() != null &&
                !sectionRepository.existsById(experience.getSection().getId())) {
            throw new ApiExecptionHandler.UserNotFoundException(
                    "Section avec id " + experience.getSection().getId() + " n'existe pas");
        }

        return experienceRepository.save(experience);
    }

    @Override
    public List<Experience> deleteExperience(String groupId) {

            System.out.println("groupId: [" + groupId + "]");
            if (groupId == null || groupId.isEmpty()) {

                throw new ApiExecptionHandler.UserNotFoundException("GroupId invalide ou manquant.");
            }

            List<Experience> deletedExpers = experienceRepository.findByGroupId(groupId);

            if (deletedExpers.isEmpty() ) {
                System.out.println("groupId is null or empty");
                throw new ApiExecptionHandler.UserNotFoundException(
                        "Aucune experiences trouvée : " + groupId
                );
            }

            experienceRepository.deleteAll(deletedExpers);

            return deletedExpers;
        }



    private Experience cloneExperience(Experience source, Experience.Type type, Section section) {
        Experience target = new Experience();
        target.setTitle(source.getTitle());
        target.setEntreprise(source.getEntreprise());
        target.setDateDebut(source.getDateDebut());
        target.setDateFin(source.getDateFin());
        target.setLocalisation(source.getLocalisation());
        target.setMession(source.getMession());
         target.setRealisation(source.getRealisation());
         target.setLogo(source.getLogo());

        target.setTypes(type);
        target.setDescription(source.getDescription());
        target.setSection(section);
        target.setLien(source.getLien());




        if (type == Experience.Type.PORTFOLIO) {
            target.setMedia(source.getMedia());
            target.setLogo(source.getLogo());
        } else if (type == Experience.Type.CV) {
            target.setMedia(null);
            target.setLogo(null);
        }

        return target;
    }

}


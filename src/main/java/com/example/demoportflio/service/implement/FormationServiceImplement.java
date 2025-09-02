package com.example.demoportflio.service.implement;

import com.example.demoportflio.exception.user.ApiExecptionHandler;
import com.example.demoportflio.model.Experience;
import com.example.demoportflio.model.Formation;
import com.example.demoportflio.model.Section;
import com.example.demoportflio.repository.FormationRepository;
import com.example.demoportflio.repository.SectionRepository;
import com.example.demoportflio.service.FormationService;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class FormationServiceImplement  implements FormationService {
private final FormationRepository formationRepository;
private final SectionRepository sectionRepository;
    public FormationServiceImplement( FormationRepository formationRepository, SectionRepository sectionRepository) {
        this.formationRepository = formationRepository;
        this.sectionRepository = sectionRepository;

    }

    @Override
    public List<Formation> findByType(String type) {
        Formation.Type formaType;
        try {
            formaType = Formation.Type.valueOf(type.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new ApiExecptionHandler.UserNotFoundException(
                    "Type invalide. Utilise CV, PORTFOLIO ou BOTH"
            );
        }

        List<Formation> formats = formationRepository.findByTypes(formaType);

        if (formats.isEmpty()) {
            throw new ApiExecptionHandler.UserNotFoundException(
                    "Aucune experiences trouvée pour le type : " + formaType
            );
        }


        return formats;
    }


    @Override
    public List<Formation> createFormation(Formation formation) {
        if (formation.getSection() == null) {
            throw new ApiExecptionHandler.UserNotFoundException("Section manquante pour cette certification");
        }

        Long formationId = formation.getSection().getId();
        Section experienceFromDB = sectionRepository.findById(formationId)
                .orElseThrow(() -> new ApiExecptionHandler.UserNotFoundException(
                        "Section introuvable avec l'ID : " + formationId));

        boolean exists = formationRepository.existsByTitreAndDescriptionAndOrganismeAndLieuAndDateDebutAndDateFinAndSigleAndCertificatIsAndDiplomate(
                formation.getTitre(),
                formation.getDescription(),
                formation.getOrganisme(),
                formation.getLieu(),
                formation.getDateDebut(),
                formation.getDateFin(),
                formation.getSigle(),
                formation.isCertificat(),
                formation.isDiplomate()


        );

        if (exists) {
            throw new ApiExecptionHandler.UserAlreadyExistsException("Cette formation existe déjà !");
        }

        List<Formation> savedFormations = new ArrayList<>();

        if (formation.getTypes() == Formation.Type.BOTH) {
            String groupId = UUID.randomUUID().toString();
            Formation formaCV = cloneFormation(formation, Formation.Type.CV, experienceFromDB);
            formaCV.setGroupId(groupId);
            savedFormations.add(formationRepository.save(formaCV));

            Formation experPortfolio = cloneFormation(formation, Formation.Type.PORTFOLIO, experienceFromDB);
            experPortfolio.setGroupId(groupId);
            savedFormations.add(formationRepository.save(experPortfolio));
        }else{
            formation.setSection(experienceFromDB);
            savedFormations.add(formationRepository.save(formation));
        }

        return savedFormations;


    }

    @Override
    public List<Formation> getAllFormation() {

        return formationRepository.findAll();
    }

    @Override
    public Formation getFormationById(Long id) {
        return formationRepository.findById(id)
                .orElseThrow(() -> new ApiExecptionHandler.UserNotFoundException("Section avec id " + id + " non trouvée"));

    }

    @Override
    public Formation updateFormation(Formation formation) {

        return formationRepository.save(formation);

    }


   @Override
    public List<Formation> deleteFormation(String groupId) {
       System.out.println("groupId: [" + groupId + "]");
       if (groupId == null || groupId.isEmpty()) {
           throw new ApiExecptionHandler.UserNotFoundException("GroupId invalide ou manquant.");
       }

       List<Formation> deletedFormations = formationRepository.findByGroupId(groupId);

       if (deletedFormations.isEmpty() ) {
           System.out.println("groupId is null or empty");
           throw new ApiExecptionHandler.UserNotFoundException(
                   "Aucune experiences trouvée : " + groupId
           );
       }

       formationRepository.deleteAll(deletedFormations);

       return deletedFormations;
    }



    private Formation cloneFormation(Formation source, Formation.Type type, Section section) {
        Formation target = new Formation();
        target.setTitre(source.getTitre());
        target.setOrganisme(source.getOrganisme());
        target.setDateDebut(source.getDateDebut());
        target.setDateFin(source.getDateFin());
        target.setDescription(source.getDescription());

        target.setSection(section);
        target.setLieu(source.getLieu());
        target.setSigle(source.getSigle());

        target.setLieu(source.getLieu());

        target.setTypes(type);

        if (type == Formation.Type.PORTFOLIO) {
            target.setLogo(source.getLogo());
            target.setCertificat(source.isCertificat());
            target.setDiplomate(source.isDiplomate());
        } else if (type == Formation.Type.CV) {
            target.setLogo(null);
        }

        return target;
    }
}

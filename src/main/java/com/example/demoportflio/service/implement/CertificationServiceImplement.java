package com.example.demoportflio.service.implement;

import com.example.demoportflio.exception.user.ApiExecptionHandler;

import com.example.demoportflio.model.Certification;
import com.example.demoportflio.model.Section;

import com.example.demoportflio.repository.CertificationRepository;
import com.example.demoportflio.repository.SectionRepository;

import com.example.demoportflio.service.CertificationService;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class CertificationServiceImplement implements CertificationService {
    private final CertificationRepository certificationRepository;
     private final SectionRepository sectionRepository;
    public CertificationServiceImplement(CertificationRepository certificationRepository, SectionRepository sectionRepository   ) {
        this.certificationRepository = certificationRepository;
        this.sectionRepository = sectionRepository;
    }

    @Override
    public Certification findCertificationById(Long id) {
        return certificationRepository.findById(id)
                .orElseThrow(() -> new ApiExecptionHandler.UserNotFoundException("Certificat avec id " + id + " non trouvée"));
    }

    @Override
    public List<Certification> findAllCertification() {
         return certificationRepository.findAll()  ;
    }

    @Override
    @Transactional
    public List<Certification> saveCertification(Certification certification) {

        if (certification.getSection() == null) {
            throw new ApiExecptionHandler.UserNotFoundException("Section manquante pour cette certification");
        }

        Long certificatId = certification.getSection().getId();
        Section certificatFromDb = sectionRepository.findById(certificatId)
                .orElseThrow(() -> new ApiExecptionHandler.UserNotFoundException(
                        "Section introuvable avec l'ID : " + certificatId));

        boolean exists = certificationRepository.existsByTitreAndOrganismeAndDateAndTypesAndSection(
                certification.getTitre(),
                certification.getOrganisme(),
                certification.getDate(),
                certification.getTypes(),
                certification.getSection()
        );

        if (exists) {
            throw new ApiExecptionHandler.UserAlreadyExistsException("Cette certification existe déjà !");
        }

        List<Certification> savedCertifications = new ArrayList<>();

        if (certification.getTypes() == Certification.Type.BOTH) {

            String groupId = UUID.randomUUID().toString();
            Certification certCv = cloneCertification(certification, Certification.Type.CV, certificatFromDb);
            certCv.setGroupId(groupId);
            savedCertifications.add(certificationRepository.save(certCv));

            // Copie pour PORTFOLIO
            Certification certPortfolio = cloneCertification(certification, Certification.Type.PORTFOLIO, certificatFromDb);
            certPortfolio.setGroupId(groupId);
            savedCertifications.add(certificationRepository.save(certPortfolio));
            }else{
            certification.setSection(certificatFromDb);
            savedCertifications.add(certificationRepository.save(certification));
            }

        return savedCertifications;

    }


    @Override
    public List<Certification> findByType(String type) {
        Certification.Type certType;
        try {
            certType = Certification.Type.valueOf(type.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new ApiExecptionHandler.UserNotFoundException(
                    "Type invalide. Utilise CV, PORTFOLIO ou BOTH"
            );
        }

        List<Certification> certs = certificationRepository.findByTypes(certType);

        if (certs.isEmpty()) {
            throw new ApiExecptionHandler.UserNotFoundException(
                    "Aucune certification trouvée pour le type : " + certType
            );
        }


        return certs;
    }
    @Override
    public List<Certification> deleteCertification(String groupId) {
        System.out.println("groupId: [" + groupId + "]");
        if (groupId == null || groupId.isEmpty()) {

            throw new ApiExecptionHandler.UserNotFoundException("GroupId invalide ou manquant.");
        }

        List<Certification> deletedCerts = certificationRepository.findByGroupId(groupId);

        if (deletedCerts.isEmpty() ) {
            System.out.println("groupId is null or empty");
            throw new ApiExecptionHandler.UserNotFoundException(
                    "Aucune certification trouvée : " + groupId
            );
        }

        certificationRepository.deleteAll(deletedCerts);

        return deletedCerts;
    }

    @Override
    public Certification updateCertification(Certification certification) {
        Long ExperienceId = certification.getSection().getId();
        Section ExperienceFromDb = sectionRepository.findById(ExperienceId)
                .orElseThrow(() -> new ApiExecptionHandler.UserAlreadyExistsException("Section introuvable  avec l'ID : " + ExperienceId));
        certification.setSection(ExperienceFromDb);
        return certificationRepository.save(certification);
    }


    private Certification cloneCertification(Certification source, Certification.Type type, Section section) {
        Certification target = new Certification();
        target.setTitre(source.getTitre());
        target.setOrganisme(source.getOrganisme());
        target.setDate(source.getDate());
        target.setTypes(type);
        target.setDescription(source.getDescription());
        target.setSection(section);

        target.setUrl(source.getUrl());
        target.setDateDexpiration(source.getDateDexpiration());
        target.setNiveau(source.getNiveau());


        if (type == Certification.Type.PORTFOLIO) {
            target.setPdf(source.getPdf());
            target.setLogo(source.getLogo());
        } else if (type == Certification.Type.CV) {
            target.setPdf(null);
            target.setLogo(null);
        }

        return target;
    }

}

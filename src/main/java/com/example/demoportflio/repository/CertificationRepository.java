package com.example.demoportflio.repository;

import com.example.demoportflio.model.Certification;
import com.example.demoportflio.model.Section;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CertificationRepository extends JpaRepository<Certification, Long> {
    public List<Certification> findByGroupId(String groupId);


    public List<Certification> findByTypes(Certification.Type type);
    public boolean  existsByTitreAndOrganismeAndDateAndTypesAndSection(
            String titre,
            String organisme,
            java.sql.Date date,
            Certification.Type types,
            Section section
    );
}

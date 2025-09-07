package com.example.demoportflio.repository;

import com.example.demoportflio.model.Competence;
import com.example.demoportflio.model.Section;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CompetenceRepository extends JpaRepository<Competence, Long> {
    List<Competence> findByTypes(Competence.Type types);
    List<Competence> findByGroupId(String groupId);
    boolean existsByNiveauAndDescriptionAndTypesAndSectionAndNom( String niveau,  String description, Competence.Type types, Section section,  String nom);
}

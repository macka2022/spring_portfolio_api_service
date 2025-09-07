package com.example.demoportflio.service;

import com.example.demoportflio.model.Competence;

import java.util.List;

public interface CompetenceService {
     List<Competence> findAllCompetence();
     List<Competence> createCompetence(Competence competence);
     Competence updateCompetence(Competence competence);
     List<Competence> deleteCompetence(String groupId);
     Competence findCompetenceById(Long id);
     List<Competence> findByType(String type);

}

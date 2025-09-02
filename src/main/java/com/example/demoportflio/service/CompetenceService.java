package com.example.demoportflio.service;

import com.example.demoportflio.model.Competence;

import java.util.List;

public interface CompetenceService {
    public List<Competence> findAllCompetence();
    public  List<Competence> createCompetence(Competence competence);
    public  Competence updateCompetence(Competence competence);
    public List<Competence> deleteCompetence(String groupId);
    public  Competence findCompetenceById(Long id);
    public List<Competence> findByType(String type);

}

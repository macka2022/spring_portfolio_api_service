package com.example.demoportflio.service;



import com.example.demoportflio.model.Formation;

import java.util.List;

public interface FormationService {
    List<Formation> createFormation(Formation formation);
    List<Formation> getAllFormation();
    Formation getFormationById(Long id);
    Formation updateFormation(Formation formation);
    List<Formation> deleteFormation(String idGroup);
     List<Formation> findByType(String type);
}

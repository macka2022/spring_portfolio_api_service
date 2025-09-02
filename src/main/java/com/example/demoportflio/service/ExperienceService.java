package com.example.demoportflio.service;

import com.example.demoportflio.model.Experience;
import com.example.demoportflio.model.Section;

import java.util.List;

public interface ExperienceService {
    List<Experience> createExperience(Experience experience);
    List<Experience> getExperiences();
    Experience getExperienceById(Long id);
    Experience updateExperience(Experience section);
    List<Experience> deleteExperience(String idGroup);
    List<Experience> findByType(String type);
}

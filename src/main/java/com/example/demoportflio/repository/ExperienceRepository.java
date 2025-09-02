package com.example.demoportflio.repository;

import com.example.demoportflio.model.Competence;
import com.example.demoportflio.model.Experience;
import com.example.demoportflio.model.Section;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.springframework.data.jpa.repository.JpaRepository;

import java.sql.Date;
import java.util.List;

public interface ExperienceRepository extends JpaRepository<Experience, Long> {
    List<Experience> findByTypes(Experience.Type types);
    public List<Experience> findByGroupId(String groupId);
    public boolean existsByTitleAndTypesAndDescriptionAndLocalisationAndDateDebutAndDateFinAndMessionAndRealisationAndEntrepriseAndSection(@Size(min = 4, max = 200) @NotBlank(message = "Titre requis") String title, Experience.Type types, @Size(min = 50, max = 400, message = "Description est entre 50/350") @NotBlank(message = "Description requise") String description, @NotBlank(message = "Localisation requise") String localisation, Date dateDebut, @NotNull(message = "La date est requise") Date dateFin, @Size(max = 300, message = "Mission Au maximum 300") String mession, @Size(max = 300, message = "Realisation au maximum 300") String realisation, @Size(min = 2, max = 200) @NotBlank(message = "Le nom entreprise requis") String entreprise, Section section);
}

package com.example.demoportflio.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
public class Temoignage {



    @Id
    @SequenceGenerator(
            name = "temoi_seq",               // logique Hibernate
            sequenceName = "temoi_sequence",  // objet séquence dans PostgreSQL
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "temoi_seq"
    )
    private Long id;

    @ManyToOne
    @JoinColumn(name = "section_id")
    private Section section;

    @NotBlank(message = "Le titre est requis")
    @Size(min = 3, max = 150, message = "Le titre doit avoir entre 3/100")
    private String title;

    @NotBlank(message = "La description est requise")
    @Size(min = 10, max = 500, message = "La description doit contenir entre 10/500 caractères")
    private String description;

    @NotBlank(message = "Le nom est requis")
    private String nom;

    @NotBlank(message = "La profession est requise")
    private String professional;

    @NotBlank(message = "L’URL de la vidéo est requise")
    @Size(min = 4, max = 255, message = "Url de la video doit contenir 4/255")
    private String url;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Section getSection() {
        return section;
    }

    public void setSection(Section section) {
        this.section = section;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getProfessional() {
        return professional;
    }

    public void setProfessional(String professional) {
        this.professional = professional;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}

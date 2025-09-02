package com.example.demoportflio.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
public class Partenaire {
    @Id
    @SequenceGenerator(
            name = "partenaire_seq",               // logique Hibernate
            sequenceName = "partenaire_sequence",  // objet séquence dans PostgreSQL
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "partenaire_seq"
    )
    private Long id;

    @NotBlank(message="Nom est requis")
    @Size(min=4, max=100, message="Nom est entre 4/100")
    private String nom;

    @NotBlank(message = "La url est requis")
    @Size(min=3, max=500, message="URL est entre 3/500")
    private String url;

    @NotBlank(message = "Le logo est requis")
    @Size(min=3, max=255, message="Le logo doit avoir entre 3 et 255 caractères")
    private String logo;
    @Column(columnDefinition = "TEXT")
    @Size(min = 200, max = 500, message = "Description est entre 200 et 500")
    private String description;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @ManyToOne
    @JoinColumn(name = "section_id")
    private Section section;

    // Getters et Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public Section getSection() {
        return section;
    }

    public void setSection(Section section) {
        this.section = section;
    }
}

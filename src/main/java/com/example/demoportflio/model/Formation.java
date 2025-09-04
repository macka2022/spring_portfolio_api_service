package com.example.demoportflio.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;


import java.time.LocalDate;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Formation {

    @Id
    @SequenceGenerator(
            name = "formation_seq",               // logique Hibernate
            sequenceName = "formation_sequence",  // objet séquence dans PostgreSQL
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "formation_seq"
    )
    private Long id;


    @NotBlank(message = "Le titre est requis")
    @Size(min = 3, max = 150, message = "Le titre doit contenir entre 3/150")
    private String titre;

    @NotBlank(message = "L'organisme est requis")
    @Size(min = 3, max = 200, message = "L'organisme doit contenir entre 3/200 ")
    private String organisme;

    @NotBlank(message = "Le lieu est requis")
    @Size(min = 3, max = 150, message = "Le lieu doit contenir entre 3/150")
    private String lieu;

    @NotNull(message = "La date de début est requise")
    private LocalDate dateDebut;

    @NotNull(message = "La date de fin est requise")
    private LocalDate dateFin;

    @NotBlank(message = "La description est requise")
    @Size(min = 50, max = 500, message = "La description doit contenir entre 50/500")
    @Column(columnDefinition = "TEXT")
    private String description;



   public enum Type {
        CV, PORTFOLIO, BOTH
    }
    private Type types;



    @Size(min = 3, max = 300, message = "L'URL du logo doit contenir entre 3/300 ")
    private String logo;
    private String sigle;
    private boolean certificat;
    private boolean diplomate;
    private String groupId;



    @ManyToOne
    @JoinColumn(name = "section_id")
    private Section section;


    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getOrganisme() {
        return organisme;
    }

    public void setOrganisme(String organisme) {
        this.organisme = organisme;
    }

    public String getLieu() {
        return lieu;
    }

    public void setLieu(String lieu) {
        this.lieu = lieu;
    }

    public LocalDate getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(LocalDate dateDebut) {
        this.dateDebut = dateDebut;
    }

    public LocalDate getDateFin() {
        return dateFin;
    }

    public void setDateFin(LocalDate dateFin) {
        this.dateFin = dateFin;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Type getTypes() {
        return types;
    }

    public void setTypes(Type types) {
        this.types = types;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getSigle() {
        return sigle;
    }

    public void setSigle(String sigle) {
        this.sigle = sigle;
    }

    public boolean isCertificat() {
        return certificat;
    }

    public void setCertificat(boolean certificat) {this.certificat = certificat;
    }

    public boolean isDiplomate() {
        return diplomate;
    }

    public void setDiplomate(boolean diplomate) {
        diplomate = diplomate;
    }

    public Section getSection() {
        return section;
    }

    public void setSection(Section section) {
        this.section = section;
    }
}

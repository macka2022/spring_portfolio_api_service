package com.example.demoportflio.model;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity

public class Competence {
    @Id
    @SequenceGenerator(
            name = "competence_seq",               // logique Hibernate
            sequenceName = "competence_sequence",  // objet séquence dans PostgreSQL
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "competence_seq"
    )

    private Long id;


    @Enumerated(EnumType.STRING)
    private Type types;
   public enum Type{
        CV, PORTFOLIO, BOTH
    }
    private String groupId;

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public Type getTypes() {
        return types;
    }

    public void setTypes(Type types) {
        this.types = types;
    }


    @NotBlank(message = "Nom est requis")
    @Size(min = 3, max = 150)
    private String nom;
    @NotBlank(message = "Description est requise")
    @Column(columnDefinition = "TEXT")
    @Size(message = "Description est entre 100/500")
    private String description;

    @NotBlank
    private String niveau;

    public Competence() {
    }


    @ManyToOne
    @JoinColumn(name = "user_id")
    private Section section;

    public Long getId() {
        return id;
    }
    @SuppressFBWarnings(
            value = "EI_EXPOSE_REP",
            justification = "Relation JPA : référence mutable nécessaire pour Hibernate"
    )

    public Section getSection() {
        return section;
    }
    @SuppressFBWarnings(
            value = "EI_EXPOSE_REP2",
            justification = "Relation JPA : référence mutable nécessaire pour Hibernate"
    )

    public void setSection(Section section) {
        this.section = section;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getNiveau() {
        return niveau;
    }

    public void setNiveau(String image) {
        this.niveau = image;
    }


}

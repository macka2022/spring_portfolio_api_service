package com.example.demoportflio.model;

import jakarta.persistence.*;

@Entity
public class Langues {
    @Id
    @SequenceGenerator(
            name = "langue_seq",               // logique Hibernate
            sequenceName = "langue_sequence",  // objet séquence dans PostgreSQL
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "langue_seq"
    )
    private Long id;

    private String nom;
    private Niveau niveau;
    enum  Niveau {
        Debutant,
        Elementaire,
        Intermediaire,
         Avance,
         Maitrise
    }
    @ManyToOne
    @JoinColumn(name = "user_id")
    private Section section;

    public Section getSection() {
        return section;
    }



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

    public void setSection(Section section) {
        this.section = section;
    }

    public Niveau getNiveau() {
        return niveau;
    }

    public void setNiveau(Niveau niveau) {
        this.niveau = niveau;
    }
}

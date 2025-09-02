package com.example.demoportflio.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;



@Entity
public class Atout {
    @Id
    @SequenceGenerator(
            name = "atout_seq",               // logique Hibernate
            sequenceName = "atout_sequence",  // objet séquence dans PostgreSQL
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "atout_seq"
    )
   private Long id;

    @NotBlank(message = "Titre est requis")
    private String title;

    @NotBlank(message = "Description requise")
    @Size(message = "Description  contenir 200 a 500", min = 200, max = 500)
    private String description;
    @ManyToOne
    @JoinColumn(name = "section_id")
    private Section section;

    private Type types;
    enum Type{
        CV, PORTFOLIO, BOTH
    }

    public Type getTypes() {
        return types;
    }

    public void setTypes(Type types) {
        this.types = types;
    }

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Section getSection() {
        return section;
    }

    public void setSection(Section section) {
        this.section = section;
    }
}

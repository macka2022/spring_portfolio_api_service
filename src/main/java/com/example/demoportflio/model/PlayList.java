package com.example.demoportflio.model;

import jakarta.persistence.*;

@Entity
public class PlayList {
    @Id
    @SequenceGenerator(
            name = "play_seq",               // logique Hibernate
            sequenceName = "play_sequence",  // objet séquence dans PostgreSQL
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "play_seq"
    )
    private Long id;


    private String titre ;
    private String description ;
    private String url;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private Section section;



    public void setId(Long id) {
        this.id = id;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Section getSection() {
        return section;
    }

    public void setSection(Section section) {
        this.section = section;
    }


    public Long getId() {
        return id;
    }

}

package com.example.demoportflio.model;

import com.example.demoportflio.exception.user.UniqueEmail;
import com.example.demoportflio.exception.user.UniquePhone;
import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.w3c.dom.Text;

import java.sql.Date;

@Entity
public class Apropos {
    @Id
    @SequenceGenerator(
            name = "propos_seq",               // logique Hibernate
            sequenceName = "propos_sequence",  // objet séquence dans PostgreSQL
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "propos_seq"
    )
    private Long id;




    @NotBlank(message = "Le nom est obligatoire")
    @Size(min = 4, max = 100)
    private String nom;

    @Size( max = 200)
    private String photo;

   // @NotBlank(message = "Le lien GitHub est obligatoire")

    @Column(name = "git_url")
    @Size( max = 200)
    private String git;

   // @NotBlank(message = "Le lien LinkedIn est obligatoire")

    @Column(name = "linkedin_url")
    @Size( max = 200)
    private String linkedin;

    @NotBlank(message = "L'email est obligatoire")
    @Email(message = "Email invalide")

    @Size(min = 4, max = 150)

    private String email;

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

    //@NotBlank(message = "Le téléphone est obligatoire")
    @Pattern(regexp = "^\\+?[0-9\\s-]{20,}$", message = "Numéro de téléphone invalide")
    //@UniquePhone
    private String telephone;

    @ManyToOne
//    @JoinColumn(name = "section_id")

    @JoinColumn(name = "section_id")
   // @edu.umd.cs.findbugs.annotations.SuppressFBWarnings(
       //     value = {"EI_EXPOSE_REP", "EI_EXPOSE_REP2"},
       //     justification = "Exposition normale d'une relation JPA, nécessaire pour Hibernate"
   // )
    private Section section;



    @NotBlank(message = "Niveau est obligatoire")
    @Size(min = 4, max =100)

    private String localisation;



    @Size(min = 4, max = 100)
    private String niveau;

    @NotBlank(message = "profile est obligatoire")
    @Size(min = 50, max = 300)
    private String profile;

    @NotBlank(message = "titre est obligatoire")
    @Size(min = 4, max = 200)
    private String titre;

    // Audit fields
    @CreationTimestamp
    @Column(updatable = false, name = "created_at")
    private Date createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private Date updatedAt;

    // Relationships (example)



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

    public String getPhoto() {
        return photo;
    }

    public String getProfile() {
        return profile;
    }

    public void setProfile(String profile) {
        this.profile = profile;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getGit() {
        return git;
    }

    public void setGit(String git) {
        this.git = git;
    }

    public String getLinkedin() {
        return linkedin;
    }

    public void setLinkedin(String linkedIn) {
        this.linkedin = linkedIn;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getLocalisation() {
        return localisation;
    }

    public void setLocalisation(String localisation) {
        this.localisation = localisation;
    }

    public String getNiveau() {
        return niveau;
    }

    public void setNiveau(String niveau) {
        this.niveau = niveau;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }
    @SuppressFBWarnings(
            value = "EI_EXPOSE_REP",
            justification = "Relation JPA : référence mutable nécessaire pour Hibernate"
    )


    public Date getCreatedAt() {
        return createdAt;
    }
    @SuppressFBWarnings(
            value = "EI_EXPOSE_REP2",
            justification = "Relation JPA : référence mutable nécessaire pour Hibernate"
    )


    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }
    @SuppressFBWarnings(
            value = "EI_EXPOSE_REP",
            justification = "Relation JPA : référence mutable nécessaire pour Hibernate"
    )


    public Date getUpdatedAt() {
        return updatedAt;
    }
    @SuppressFBWarnings(
            value = "EI_EXPOSE_REP2",
            justification = "Relation JPA : référence mutable nécessaire pour Hibernate"
    )


    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }
    @SuppressFBWarnings(
            value = "EI_EXPOSE_REP",
            justification = "Relation JPA : référence mutable nécessaire pour Hibernate"
    )

    public Section getSection() {
        return section ;
    }
    @SuppressFBWarnings(
            value = "EI_EXPOSE_REP2",
            justification = "Relation JPA : référence mutable nécessaire pour Hibernate"
    )

    public void setSection(Section section) {
        this.section =section;
    }

}

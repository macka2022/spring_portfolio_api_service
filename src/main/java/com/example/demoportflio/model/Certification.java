package com.example.demoportflio.model;


import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;


import java.sql.Date;
import java.time.LocalDate;

@Entity
public class Certification {
    @Id
    @SequenceGenerator(
            name = "certificat_seq",               // logique Hibernate
            sequenceName = "certificat_sequence",  // objet séquence dans PostgreSQL
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "certificat_seq"
    )
    private Long id;

    @Size(max = 200, message = "url pdf maximum 80")
    private String pdf;
    @Size(max = 200, message = "url logo maximum 80")
    private String logo;
    @NotBlank(message = "Titre est requis")
   private String titre;
    @NotBlank(message = "Organisation est requise")
    @Size(min  = 2, max=100, message = "Organisme est compris 2/100")
   private String organisme;


    private String groupId;

   private Date date;

   @NotBlank(message = "Description requise")
   @Size(min = 100, max = 500, message = "Description est comprise 100/500")
   @Column(columnDefinition = "TEXT")
   private String description;
    @Size(max = 200, message = "url maximum 200")
   private String url;

   private LocalDate dateDexpiration;

    @NotBlank(message = "Niveau est requis")
   private String niveau;
    @Enumerated(EnumType.STRING)
    private  Type types;
   public enum Type{
        CV, PORTFOLIO, BOTH
    }

    @ManyToOne
   @JoinColumn(name = "id_user")
   private  Section section;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPdf() {
        return pdf;
    }

    public void setPdf(String pdf) {
        this.pdf = pdf;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
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

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }
    @SuppressFBWarnings(
            value = "EI_EXPOSE_REP",
            justification = "Relation JPA : référence mutable nécessaire pour Hibernate"
    )
    public Date getDate() {
        return date;
    }

    @SuppressFBWarnings(
            value = "EI_EXPOSE_REP2",
            justification = "Relation JPA : référence mutable nécessaire pour Hibernate"
    )
    public void setDate(Date date) {
        this.date = date;
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

    public LocalDate getDateDexpiration() {
        return dateDexpiration;
    }

    public void setDateDexpiration(LocalDate dateDexpiration) {
        this.dateDexpiration = dateDexpiration;
    }

    public String getNiveau() {
        return niveau;
    }

    public void setNiveau(String niveau) {
        this.niveau = niveau;
    }

    public Type getTypes() {
        return types;
    }

    public void setTypes(Type types) {
        this.types = types;
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
}






/*package com.example.demoportflio.controller;

import com.example.demoportflio.model.Certification;
import com.example.demoportflio.model.CertificationType;
import com.example.demoportflio.service.CertificationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

        import java.util.List;

@RestController
@RequestMapping("/api/certifications")
public class CertificationController {

    private final CertificationService service;

    public CertificationController(CertificationService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Certification> create(@RequestBody Certification certification) {
        return ResponseEntity.ok(service.save(certification));
    }

    @GetMapping
    public ResponseEntity<List<Certification>> getAll() {
        return ResponseEntity.ok(service.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Certification> getById(@PathVariable int id) {
        return service.getById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Certification> update(@PathVariable int id, @RequestBody Certification certification) {
        return ResponseEntity.ok(service.update(id, certification));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable int id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    // Récupérer par type : CV / PORTFOLIO / BOTH
    @GetMapping("/type/{type}")
    public ResponseEntity<List<Certification>> getByType(@PathVariable CertificationType type) {
        return ResponseEntity.ok(service.getByType(type));
    }
}*/

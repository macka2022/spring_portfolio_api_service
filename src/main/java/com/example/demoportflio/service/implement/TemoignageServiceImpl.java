package com.example.demoportflio.service.implement;

import com.example.demoportflio.exception.user.ApiExecptionHandler;
import com.example.demoportflio.exception.user.ApiRequestException;
import com.example.demoportflio.model.Competence;
import com.example.demoportflio.model.Section;
import com.example.demoportflio.model.Temoignage;
import com.example.demoportflio.model.User;
import com.example.demoportflio.repository.SectionRepository;
import com.example.demoportflio.repository.TemoignageRepository;
import com.example.demoportflio.service.TemoignageService;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class TemoignageServiceImpl implements TemoignageService {
    private final TemoignageRepository temoignageRepository;
    private final SectionRepository sectionRepository;

    public TemoignageServiceImpl(TemoignageRepository temoignageRepository, SectionRepository sectionRepository) {
        this.temoignageRepository = temoignageRepository;
        this.sectionRepository = sectionRepository;
    }

    @Override
    public List<Temoignage> listTemoignage() {

        return temoignageRepository.findAll();
    }

    @Override
    public Temoignage getTemoignageById(Long id) {
        Temoignage temoignage = temoignageRepository.findById(id)
                .orElseThrow(() -> new ApiRequestException("La section avec id " + id + " est introuvable.") );
        temoignageRepository.delete(temoignage);
        return  temoignage;

    }

    @Override
    public Temoignage saveTemoignage(Temoignage temoignage) {
        Long sectionId = temoignage.getSection().getId();

        // Charger le user complet depuis la base de données
        Section userFromDb = sectionRepository.findById(sectionId)
                .orElseThrow(() -> new ApiExecptionHandler.UserNotFoundException("Utilisateur introuvable avec l'ID : " + sectionId));

        // Réassocier le user complet à la section
        temoignage.setSection(userFromDb);
        return temoignageRepository.save(temoignage);
    }

    @Override
    public Temoignage deleteTemoignage(Long id) {
        Temoignage temoignage = temoignageRepository.findById(id)
                .orElseThrow(() -> new ApiExecptionHandler.UserNotFoundException("La section avec id " + id + " est introuvable.") );
        temoignageRepository.delete(temoignage);
        return  temoignage;
    }

    @Override
    public Temoignage updateTemoignage(Temoignage temoignage) {
       return  temoignageRepository.save(temoignage);
    }
}

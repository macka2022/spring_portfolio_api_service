package com.example.demoportflio.service.implement;

import com.example.demoportflio.exception.user.ApiExecptionHandler;
import com.example.demoportflio.model.Partenaire;
import com.example.demoportflio.repository.PartenaireRepository;
import com.example.demoportflio.service.PartenaireService;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class PartenaireServiceImplement implements PartenaireService {

    private final PartenaireRepository connaissanceRepository ;

    public PartenaireServiceImplement(PartenaireRepository connaissanceRepository) {
        this.connaissanceRepository = connaissanceRepository;
    }

    @Override
    public List<Partenaire> getConnaissances() {
        return  connaissanceRepository.findAll();
    }

    @Override
    public Partenaire getConnaissanceById(Long id) {
        return connaissanceRepository.findById(id)
                .orElseThrow(() -> new ApiExecptionHandler.UserNotFoundException("Partenaire non trouvée avec l'id : " + id)) ;

    }

    @Override
    public Partenaire saveConnaissance(Partenaire c) {
        if (!connaissanceRepository.existsById(   c.getId())) {
            throw new ApiExecptionHandler.UserNotFoundException("Section avec id " + c.getId() + " n'existe pas");
        }
        return connaissanceRepository.save(c);

    }

    @Override
    public Partenaire deleteConnaissance(Long id) {

        Partenaire connaissance = connaissanceRepository.findById(id)
                .orElseThrow(() -> new ApiExecptionHandler.UserNotFoundException("La section avec id " + id + " est introuvable.") );
        connaissanceRepository.delete(connaissance);
        return  connaissance;

    }

    @Override
    public Partenaire updateConnaissance(Partenaire c) {
        return connaissanceRepository.save(c);
    }
}

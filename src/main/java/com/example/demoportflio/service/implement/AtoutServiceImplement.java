package com.example.demoportflio.service.implement;

import com.example.demoportflio.exception.user.ApiExecptionHandler;

import com.example.demoportflio.model.Atout;

import com.example.demoportflio.repository.AtoutRepository;
import com.example.demoportflio.service.AtoutService;

import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class AtoutServiceImplement  implements AtoutService {

    private final AtoutRepository autoutRepository;

    public AtoutServiceImplement(AtoutRepository autoutRepository) {
        this.autoutRepository = autoutRepository;
    }

    @Override
    public List<Atout> getAllSkills() {
        return autoutRepository.findAll();
    }

    @Override
    public Atout getAtoutById(Long id) {
        return autoutRepository.findById(id)
                .orElseThrow(() -> new ApiExecptionHandler.UserNotFoundException("Atout avec id " + id + " non trouvée"));
    }

    @Override
    public Atout updateAtout(Atout atout) {
        if (!autoutRepository.existsById(atout.getId())) {
            throw new ApiExecptionHandler.UserNotFoundException(
                    "Atout avec id " + atout.getId() + " n'existe pas");
        }
        return autoutRepository.save(atout);
    }

    @Override
    public Atout deleteAtout(Long id) {
        Atout atout = autoutRepository.findById(id)
                .orElseThrow(() -> new ApiExecptionHandler.UserNotFoundException(
                        "Atout avec id " + id + " est introuvable."
                ));

        autoutRepository.delete(atout);
        return atout;

    }



    @Override
    public Atout addAtout(Atout to) {
        return autoutRepository.save(to);
    }
}

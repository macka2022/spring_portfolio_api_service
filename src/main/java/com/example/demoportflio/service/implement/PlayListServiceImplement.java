package com.example.demoportflio.service.implement;

import com.example.demoportflio.model.PlayList;
import com.example.demoportflio.model.Section;
import com.example.demoportflio.repository.PlayListRpository;
import com.example.demoportflio.repository.SectionRepository;
import com.example.demoportflio.service.PlayListService;
import org.springframework.stereotype.Service;


import java.util.List;
@Service
public class PlayListServiceImplement implements PlayListService {
     PlayListRpository playListRpository;
     SectionRepository sectionRepository;

    public PlayListServiceImplement(PlayListRpository playListRpository, SectionRepository sectionRepository) {
        this.playListRpository = playListRpository;
        this.sectionRepository = sectionRepository;
    }

    @Override
    public List<PlayList> getAllPlayLists() {
        return playListRpository.findAll();
    }

    @Override
    public PlayList getPlayListById(Long id) {
         return playListRpository.findById(id)
                .orElseThrow(() -> new RuntimeException("Playlist avec id " + id + " non trouvée"));
    }

    @Override
    public PlayList savePlayList(PlayList playList) {
        Long sectionId = playList.getSection().getId(); // ID de la section dans le JSON

        Section sectionFromDb = sectionRepository.findById(sectionId)
                .orElseThrow(() -> new RuntimeException("Section introuvable avec l'ID : " + sectionId));

        playList.setSection(sectionFromDb); // attacher la vraie section
        return playListRpository.save(playList);

    }

    @Override
    public PlayList deletePlayList(PlayList playList) {


        if (!playListRpository.existsById( playList.getId())) {
            throw new RuntimeException("Play Liste avec id " + playList.getId() + " n'existe pas");
        }
        playListRpository.delete(playList);
        return playList;


    }

    @Override
    public PlayList updatePlayList(PlayList playList) {
        Long sectionId = playList.getSection().getId(); // ID de la section dans le JSON

        Section sectionFromDb = sectionRepository.findById(sectionId)
                .orElseThrow(() -> new RuntimeException("Section introuvable avec l'ID : " + sectionId));

        playList.setSection(sectionFromDb); // attacher la vraie section
        return playListRpository.save(playList);
    }
}

package com.example.demoportflio.service;

import com.example.demoportflio.model.Atout;

import java.util.List;

public interface AtoutService {

     List<Atout> getAllSkills();
     Atout getAtoutById(Long id);
     Atout updateAtout(Atout to);
     Atout deleteAtout(Long id);
     Atout addAtout(Atout to);
}

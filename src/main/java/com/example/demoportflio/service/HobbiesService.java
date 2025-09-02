package com.example.demoportflio.service;

import com.example.demoportflio.model.Hobbies;

import java.util.List;

public interface HobbiesService {
 List<Hobbies> getAllHobbies();
 Hobbies getHobbies(Long id);
 Hobbies addHobbies(Hobbies hobbies);
 Hobbies updateHobbies(Hobbies hobbies);
 Hobbies deleteHobbies(Long id);
}

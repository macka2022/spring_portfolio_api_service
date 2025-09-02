package com.example.demoportflio.service;

import com.example.demoportflio.model.Section;

import java.util.List;

public interface SectionService {
    Section createSection(Section section);
    List<Section> getSections();
    Section getSectionById(Long id);
    Section updateSection(Section section);
    Section deleteSection(Long id);
}

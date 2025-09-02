package com.example.demoportflio.repository;

import com.example.demoportflio.model.PlayList;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlayListRpository  extends JpaRepository<PlayList, Long> {
}

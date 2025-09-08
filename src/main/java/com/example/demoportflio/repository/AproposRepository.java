package com.example.demoportflio.repository;

import com.example.demoportflio.model.Apropos;
import org.springframework.data.jpa.repository.JpaRepository;




public interface AproposRepository  extends JpaRepository<Apropos, Long> {
   // boolean existsByEmail(String email);
    boolean existsByTelephone(String phone);
    Apropos findBySectionUserId(Long userId);

}

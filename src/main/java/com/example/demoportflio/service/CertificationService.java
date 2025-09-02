package com.example.demoportflio.service;

import com.example.demoportflio.model.Certification;

import java.util.List;

public interface CertificationService {
    Certification findCertificationById(Long id);
     List<Certification> findAllCertification();
    List<Certification> saveCertification(Certification certification);
     List<Certification>  deleteCertification(String id);
     List<Certification> findByType(String type);
     Certification updateCertification(Certification certification);


}

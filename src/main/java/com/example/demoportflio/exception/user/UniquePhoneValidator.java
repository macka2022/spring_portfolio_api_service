package com.example.demoportflio.exception.user;


import com.example.demoportflio.repository.AproposRepository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.stereotype.Component;


@Component
public class UniquePhoneValidator implements ConstraintValidator<UniquePhone, String> {

    private final AproposRepository aproposRepository;

    public UniquePhoneValidator(AproposRepository aproposRepository) {
        this.aproposRepository = aproposRepository;
    }

    @Override
    public boolean isValid(String phone, ConstraintValidatorContext context) {
        if (phone == null) return true; // @NotBlank gère déjà ça
        return !aproposRepository.existsByTelephone(phone) ;

    }
}
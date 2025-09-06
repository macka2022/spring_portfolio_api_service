package com.example.demoportflio.service.implement;


import com.example.demoportflio.exception.user.ApiExecptionHandler;
import com.example.demoportflio.model.User;
import com.example.demoportflio.repository.UserRepository;
import com.example.demoportflio.service.UserService;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class UserServiceImplement implements UserService {
 private final    UserRepository userRepository;

    public UserServiceImplement(UserRepository userRepository) {
        this.userRepository =  userRepository;
    }


    @Override
    public User CreateUser(User user) {
        boolean existsByEmail = userRepository.existsByEmail(user.getEmail());
        if (existsByEmail) {
            throw new ApiExecptionHandler.UserAlreadyExistsException("Un utilisateur avec cet email existe déjà.");
        }
       return userRepository.save(user);

    }

    @Override
    public List<User> listUser() {
        return  userRepository.findAll();
    }

    @Override
    public User activeDesactive(User user) {
        User existingUser = userRepository.findById(user.getId())
                .orElseThrow(() -> new ApiExecptionHandler.UserNotFoundException("Utilisateur non trouvé"));
        existingUser.setActive(!existingUser.isActive());
        return userRepository.save(existingUser);
    }



}



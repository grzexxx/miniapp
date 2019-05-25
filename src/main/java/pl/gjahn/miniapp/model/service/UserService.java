package pl.gjahn.miniapp.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.gjahn.miniapp.model.entity.UserEntity;
import pl.gjahn.miniapp.model.form.RegisterForm;
import pl.gjahn.miniapp.model.repository.UserRepository;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public boolean registerUser(RegisterForm registerForm){
        UserEntity newUser = new UserEntity(registerForm);
        userRepository.save(newUser);

        return true;
    }
}

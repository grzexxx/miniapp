package pl.gjahn.miniapp.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import pl.gjahn.miniapp.model.entity.UserEntity;
import pl.gjahn.miniapp.model.form.LoginForm;
import pl.gjahn.miniapp.model.form.RegisterForm;
import pl.gjahn.miniapp.model.repository.UserRepository;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public boolean registerUser(RegisterForm registerForm) {

        if (userRepository.existsByEmail(registerForm.getEmail())) {
            return false;
        } else {
            String passwordHash = getBCrypt().encode(registerForm.getPassword());
            registerForm.setPassword(passwordHash);

            UserEntity newUser = new UserEntity(registerForm);
            userRepository.save(newUser);
            return true;
        }
    }

    @Bean
    public BCryptPasswordEncoder getBCrypt() {
        return new BCryptPasswordEncoder();
    }

    public boolean loginUser(LoginForm loginForm) {
        if (userRepository.existsByEmail(loginForm.getEmail())) {
            Optional<UserEntity> userToCheck = userRepository.findByEmail(loginForm.getEmail());

            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            if (passwordEncoder.matches(loginForm.getPassword(), userToCheck.get().getPassword())) {

                return true;
            }
            return false;

        }
        return false;
    }

    public boolean passwordMatches (RegisterForm registerForm){
        if (registerForm.getPassword().equals(registerForm.getRepeatedPassword())){
            return true;
        }
        return false;
    }

}

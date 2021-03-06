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

    @Autowired
    SessionService sessionService;

    public boolean registerUser(RegisterForm registerForm) {

        if (userRepository.existsByEmail(registerForm.getEmail()) || !passwordMatches(registerForm.getPassword(), registerForm.getRepeatedPassword())) {
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
            boolean passwordCorrect = passwordEncoder.matches(loginForm.getPassword(), userToCheck.get().getPassword());
            if (passwordCorrect) {
                sessionService.setLogin(true);
                sessionService.setNickname(userToCheck.get().getNickname());
                sessionService.setUserId(userToCheck.get().getId());
                sessionService.setAccountType(userToCheck.get().getAccountType());

            }
            return passwordCorrect;

        }
        return false;
    }

    public boolean logoutUser (){
        sessionService.setLogin(false);
        sessionService.setNickname("");
        sessionService.setUserId(-1);

        return true;
    }

    public boolean passwordMatches(String password, String repeatPassword) {
        if (password.equals(repeatPassword)) {
            return true;
        }
        return false;
    }

    public boolean emailExist(String emailToCheck) {
        return userRepository.existsByEmail(emailToCheck);
    }

    public boolean idExist (int idToCheck){
        return userRepository.existsById(idToCheck);
    }

    public boolean emailChecker(String emailToCheck) {
        return false;
    }
}

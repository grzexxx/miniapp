package pl.gjahn.miniapp.model.service;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import pl.gjahn.miniapp.model.entity.UserEntity;
import pl.gjahn.miniapp.model.form.LoginForm;
import pl.gjahn.miniapp.model.repository.UserRepository;

import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SessionServiceTest {

    @Autowired
    UserService userService;

    @Autowired
    SessionService sessionService;

    @MockBean
    UserRepository userRepository;

    @Test
    public void isSessionMakingAfterCorrectLogin() {
//given
        LoginForm loginForm = new LoginForm();
        loginForm.setEmail("test@test.test");
        loginForm.setPassword("test");

        UserEntity userEntity = new UserEntity();
        userEntity.setEmail(loginForm.getEmail());
        userEntity.setNickname("testN");
        userEntity.setPassword(userService.getBCrypt().encode(loginForm.getPassword()));

        Optional<UserEntity> responseFromDatabase = Optional.of(userEntity);

//when
        when(userRepository.existsByEmail(loginForm.getEmail())).thenReturn(true);
        when(userRepository.findByEmail(loginForm.getEmail())).thenReturn(responseFromDatabase);
        userService.loginUser(loginForm);
//then

        Assertions.assertThat(userService.loginUser(loginForm)).isTrue();
        Assertions.assertThat(sessionService.isLogin()).isTrue();
        Assertions.assertThat(sessionService.getNickname()).isEqualTo(userEntity.getNickname());
    }
}
package pl.gjahn.miniapp.model.service;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureWebMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import pl.gjahn.miniapp.model.entity.UserEntity;
import pl.gjahn.miniapp.model.form.LoginForm;
import pl.gjahn.miniapp.model.form.RegisterForm;
import pl.gjahn.miniapp.model.repository.UserRepository;

import javax.persistence.Table;
import java.util.Optional;


import static org.junit.Assert.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class UserServiceTest {

    @Autowired
    UserService userService;

    @MockBean
    UserRepository userRepository;

    @Autowired
    MockMvc mockMvc;

    @Test
    public void shouldLoginForCorrectData() {
//given
        LoginForm loginForm = new LoginForm();
        loginForm.setEmail("test@test.test");
        loginForm.setPassword("test");

        UserEntity userEntity = new UserEntity();
        userEntity.setEmail(loginForm.getEmail());
        userEntity.setPassword(userService.getBCrypt().encode(loginForm.getPassword()));

        Optional<UserEntity> responseFromDatabase = Optional.of(userEntity);

//when
        when(userRepository.findByEmail(loginForm.getEmail())).thenReturn(responseFromDatabase);
//then

        Assertions.assertThat(!userService.loginUser(loginForm)).isTrue();
    }

    @Test
    public void shouldRegisterWhenUniqLogin() {
        //given
        RegisterForm registerForm = new RegisterForm();
        registerForm.setEmail("test@pl.pl");
        registerForm.setPassword("test");
        registerForm.setRepeatedPassword("test");

        //when
        when(userRepository.existsByEmail(registerForm.getEmail())).thenReturn(false);
        //then
        Assertions.assertThat(userService.registerUser(registerForm)).isTrue();

    }

    @Test
    public void shouldNotRegisterWhenPasswordsDontMatch() {
        //given
        RegisterForm registerForm = new RegisterForm();
        registerForm.setEmail("test@pl.pl");
        registerForm.setPassword("test");
        registerForm.setRepeatedPassword("tesst");

        //when
        when(userRepository.existsByEmail(registerForm.getEmail())).thenReturn(false);
        //then
        Assertions.assertThat(userService.registerUser(registerForm)).isFalse();

    }

    @Test
    public void shouldNotRegisterWhenNotUniqLogin() {
        //given
        RegisterForm registerForm = new RegisterForm();
        registerForm.setEmail("test@pl.pl");
        registerForm.setPassword("test");
        registerForm.setRepeatedPassword("test");

        //when
        when(userRepository.existsByEmail(registerForm.getEmail())).thenReturn(true);
        //then
        Assertions.assertThat(userService.registerUser(registerForm)).isFalse();

    }

    @Test
    public void isSessionMakingAfterCorrectLogin(){



    }

   /* public void shouldControllerLoginUser() throws Exception {
        //given
        String login = "login";
        String password = "password";

        UserEntity userEntity = new UserEntity();
        userEntity.setEmail(login);
        userEntity.setPassword(userService.getBCrypt().encode(password));

        Optional<UserEntity> responseFromDB = Optional.of(userEntity);


        //when

        when(userRepository.findByEmail(login)).thenReturn(responseFromDB);

        //then
        mockMvc.perform(post("/user/login").
                content("{\"login1\": \"login1\", \"password1\": \"password1\" }")
                .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().is3xxRedirection());

    }*/
}
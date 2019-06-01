package pl.gjahn.miniapp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import pl.gjahn.miniapp.model.form.LoginForm;
import pl.gjahn.miniapp.model.form.RegisterForm;
import pl.gjahn.miniapp.model.service.SessionService;
import pl.gjahn.miniapp.model.service.UserService;

@Controller
public class AuthController {

    @Autowired
    UserService userService;

    @Autowired
    SessionService sessionService;

    @GetMapping("/user/register")
    public String register(Model model) {
        model.addAttribute("registerForm", new RegisterForm());
        return "user/register";
    }

    @PostMapping("/user/register")
    public String register(@ModelAttribute("registerForm") RegisterForm registerForm,
                           Model model) {

        System.out.println(registerForm);
        boolean isRegistered = userService.registerUser(registerForm);
        boolean isEmailExist = userService.emailExist(registerForm.getEmail());
        boolean isPasswordMatches = !(userService.passwordMatches(registerForm.getPassword(), registerForm.getRepeatedPassword()));

        if (isRegistered) {
            return "redirect:/user/login";
        }

        model.addAttribute("isRegistered", isRegistered);
        model.addAttribute("isEmailExist", isEmailExist);
        model.addAttribute("isPasswordMatches", isPasswordMatches);
        registerForm.setPassword("");
        registerForm.setRepeatedPassword("");
        return "user/register";
    }

    @GetMapping("/user/login")
    public String login(Model model) {
        model.addAttribute("loginForm", new LoginForm());
        return "user/login";
    }

    @PostMapping("/user/login")
    public String login(@ModelAttribute("loginForm") LoginForm loginForm, Model model) {

        boolean isLogged = userService.loginUser(loginForm);
        if (userService.loginUser(loginForm)) {
            return "redirect:/user/dashboard";
        }
        model.addAttribute("isLogged", isLogged);
        return "user/login";

    }

    @GetMapping("/user/logged")
    public String logged(Model model) {
        return "user/logged";
    }

    @GetMapping("user/logout")
    public String logout(Model model) {
        userService.logoutUser();
        return "redirect:/user/login";
    }


}

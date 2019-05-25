package pl.gjahn.miniapp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import pl.gjahn.miniapp.model.form.RegisterForm;
import pl.gjahn.miniapp.model.service.UserService;

@Controller
public class AuthController {

    @Autowired
    UserService userService;

    @GetMapping("/user/register")
    public String register(Model model) {
        model.addAttribute("registerForm", new RegisterForm());
        return "user/register";

    }

    @PostMapping("/user/register")
    public String register(@ModelAttribute("registerForm") RegisterForm registerForm,
                           Model model) {
        userService.registerUser(registerForm);
        System.out.println(registerForm);
        return "user/register";
    }

    @GetMapping("/user/login")
    public String login(Model model) {
        model.addAttribute("loginForm", new RegisterForm());
        return "user/login";
    }

}

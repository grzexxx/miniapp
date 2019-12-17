package pl.gjahn.miniapp.model.form;

import lombok.Data;

import javax.validation.constraints.Pattern;

@Data
public class RegisterForm {

    @Pattern( regexp = ".(5,30)", message = "Login length must be between 5 - 30 chars")
    private String nickname;
    @Pattern( regexp = ".(8,30)", message = "Password must be longer than 8 chars")
    private String password;
    private String repeatedPassword;
    @Pattern( regexp = "^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$" , message = "Wrong email")
    private String email;

    private String aboutMe;

}

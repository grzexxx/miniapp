package pl.gjahn.miniapp.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import pl.gjahn.miniapp.model.form.RegisterForm;

import javax.persistence.*;

@Entity
@Table (name = "user")
@Data
public class UserEntity {

    public enum AccountType{
        ADMIN,
        MOD,
        USER;
    }


    @Id
    @GeneratedValue
    private int id;
    private String nickname;
    @JsonIgnore
    private String email;
    @JsonIgnore
    private String password;
    private String aboutMe;

    @Column (name = "account_type")
    @Enumerated (EnumType.STRING)
    private AccountType accountType;



    public UserEntity(){ }

    public UserEntity (RegisterForm registerForm){
        this.email = registerForm.getEmail();
        this.nickname = registerForm.getNickname();
        this.password = registerForm.getPassword();
        this.accountType = AccountType.USER;
        this.aboutMe = registerForm.getAboutMe();

    }
}

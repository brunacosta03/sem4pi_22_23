package domain.model;

import eapli.framework.general.domain.model.EmailAddress;
import eapli.framework.infrastructure.authz.domain.model.Password;
import eapli.framework.infrastructure.authz.domain.model.Role;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDateTime;
import java.util.Arrays;

public class User {

    private ShortName shortName;
    private FullName fullName;
    private Password password;
    private EmailAddress email;
    private Role role;
    private MecanographicNumber number;
    private BirthDate date;


    private boolean userState;
    private LocalDateTime createdOn;
    private LocalDateTime deactivatedOn;

    protected User(){
    }

    User(ShortName shortName, FullName fullName, Password password, EmailAddress email, Role role){
        this.shortName = shortName;
        this.fullName = fullName;
        this.password = password;
        this.email = email;
        this.role = role;
        this.userState = true;
        this.createdOn = LocalDateTime.now();
    }

    public void deactivate(){
        if(this.userState=false){
            throw new IllegalStateException("Cannot deactivate an already deactivated user.");
        }
        this.deactivatedOn = LocalDateTime.now();
        this.userState = false;
    }

    public void activate(){
        if(this.userState=true){
            throw new IllegalArgumentException("Cannot activate an already active user.");
        }
        this.deactivatedOn = null;
        this.userState = true;
    }

   /* public boolean passwordMatches(String rawPassword, PasswordEncoder encoder) {
        return encoder.matches(rawPassword, password.value());
    }
*/
    public boolean isActive(){
        return userState;
    }

    public boolean hasAnyOf(Role[] requiredRoles) {
        return Arrays.asList(requiredRoles).contains(this.role);
    }

    public void changePassword(final Password newPassword) {
        password = newPassword;
    }

    public EmailAddress identity(){
        return email;
    }
}

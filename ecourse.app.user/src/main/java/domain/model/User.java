package domain.model;

import eapli.framework.general.domain.model.EmailAddress;
import eapli.framework.infrastructure.authz.domain.model.Password;
import eapli.framework.infrastructure.authz.domain.model.Role;

import java.time.LocalDateTime;
import java.util.Arrays;

public class User {
    /**
     * Short name of user.
     */
    private ShortName shortName;
    /**
     * Full name of user.
     */
    private FullName fullName;
    /**
     * Password of user.
     */
    private Password password;
    /**
     * Email of user.
     */
    private EmailAddress email;
    /**
     * Role of user.
     */
    private Role role;
    /**
     * Number Mecanographic only for students.
     */
    private MecanographicNumber number;

    /**
     * Birthdate of user.
     */
    private BirthDate date;

    /**
     * Active or Desactive user.
     */
    private boolean userState;

    /**
     * Date when user got created in app.
     */
    private LocalDateTime createdOn;

    /**
     * Date when user got deactivated in app.
     */
    private LocalDateTime deactivatedOn;

    protected User() {

    }

    User(final ShortName shortNamep,
         final FullName fullNamep,
         final Password passwordp,
         final EmailAddress emailp,
         final Role rolep) {
        this.shortName = shortNamep;
        this.fullName = fullNamep;
        this.password = passwordp;
        this.email = emailp;
        this.role = rolep;
        this.userState = true;
        this.createdOn = LocalDateTime.now();
    }

    /**
     * Deactivates the user.
     * @throws IllegalStateException user is already deactivated.
     */
    public void deactivate() {
        if (!this.userState) {
            throw new IllegalStateException("Cannot deactivate "
                    + "an already deactivated user.");
        }
        this.deactivatedOn = LocalDateTime.now();
        this.userState = false;
    }

    /**
     * Activates the user.
     * @throws IllegalArgumentException user is already active.
     */
    public void activate() {
        if (this.userState) {
            throw new IllegalArgumentException("Cannot activate "
                    + "an already active user.");
        }
        this.deactivatedOn = null;
        this.userState = true;
    }
    /*
    public boolean passwordMatches(String rawPassword,
                                     PasswordEncoder encoder) {
        return encoder.matches(rawPassword, password.value());
    }
*/

    /**
     * Get if user is Active.
     * @return userState
     */
    public boolean isActive() {
        return userState;
    }

    /**
     * Check if user has any of Roles.
     * @param requiredRoles array of roles
     * @return have role return true
     */
    public boolean hasAnyOf(final Role[] requiredRoles) {
        return Arrays.asList(requiredRoles).contains(this.role);
    }

    /**
     * Change user password.
     * @param newPasswordp new user password
     */
    public void changePassword(final Password newPasswordp) {
        password = newPasswordp;
    }

    /**
     * User indentity is email.
     * @return user identity
     */
    public EmailAddress identity() {
        return email;
    }
}

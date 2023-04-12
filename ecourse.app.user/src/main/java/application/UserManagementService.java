package application;

import domain.model.User;
import domain.repositories.UserRepository;
import eapli.framework.general.domain.model.EmailAddress;
import eapli.framework.infrastructure.authz.application.PasswordPolicy;
import eapli.framework.infrastructure.authz.domain.model.*;
import eapli.framework.time.util.CurrentTimeCalendars;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Optional;
import java.util.Set;


public class UserManagementService {
    private final UserRepository userRepository;
    private final PasswordEncoder encoder;
    private final PasswordPolicy policy;

    public UserManagementService(final UserRepository userRepo, final PasswordPolicy policy, final PasswordEncoder encoder) {
        this.userRepository = userRepo;
        this.policy = policy;
        this.encoder = encoder;
    }
/*
    public User registerNewUser(final String username, final String rawPassword, final String firstName, final String lastName, final String email, final Set<Role> roles, final Calendar createdOn) {
        SystemUserBuilder userBuilder = new SystemUserBuilder(this.policy, this.encoder);
        userBuilder.with(username, rawPassword, firstName, lastName, email).createdOn(createdOn).withRoles(roles);
        User newUser = userBuilder.build();
        return (User)this.userRepository.save(newUser);
    }


    public User registerNewUser(final String username, final String rawPassword, final String firstName, final String lastName, final String email, final Set<Role> roles) {
        return this.registerNewUser(username, rawPassword, firstName, lastName, email, roles, CurrentTimeCalendars.now());
    }

    public User registerUser(final Username username, final Password password, final Name name, final EmailAddress email, final Set<Role> roles) {
        SystemUserBuilder userBuilder = new SystemUserBuilder(this.policy, this.encoder);
        userBuilder.with(username, password, name, email).withRoles(roles);
        User newUser = userBuilder.build();
        return (User)this.userRepository.save(newUser);
    }


 */

    public Iterable<User> activeUsers() {
        return this.userRepository.findByActive(true);
    }

    public Iterable<User> deactivatedUsers() {
        return this.userRepository.findByActive(false);
    }

    public Iterable<User> allUsers() {
        return this.userRepository.findAll();
    }

    public Optional<User> userOfIdentity(final Username id) {
        return this.userRepository.ofIdentity(id);
    }

    public User deactivateUser(final User user) {
        user.deactivate();
        return (User)this.userRepository.save(user);
    }
}

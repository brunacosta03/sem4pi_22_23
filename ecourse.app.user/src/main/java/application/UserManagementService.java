package application;

import domain.repositories.UserRepository;
import eapli.framework.general.domain.model.EmailAddress;
import eapli.framework.infrastructure.authz.application.PasswordPolicy;
import eapli.framework.infrastructure.authz.domain.model.*;
import eapli.framework.time.util.CurrentTimeCalendars;
import org.springframework.security.crypto.password.PasswordEncoder;

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

    public SystemUser registerNewUser(final String username, final String rawPassword, final String firstName, final String lastName, final String email, final Set<Role> roles, final Calendar createdOn) {
        SystemUserBuilder userBuilder = new SystemUserBuilder(this.policy, this.encoder);
        userBuilder.with(username, rawPassword, firstName, lastName, email).createdOn(createdOn).withRoles(roles);
        SystemUser newUser = userBuilder.build();
        return (SystemUser)this.userRepository.save(newUser);
    }

    public SystemUser registerNewUser(final String username, final String rawPassword, final String firstName, final String lastName, final String email, final Set<Role> roles) {
        return this.registerNewUser(username, rawPassword, firstName, lastName, email, roles, CurrentTimeCalendars.now());
    }

    public SystemUser registerUser(final Username username, final Password password, final Name name, final EmailAddress email, final Set<Role> roles) {
        SystemUserBuilder userBuilder = new SystemUserBuilder(this.policy, this.encoder);
        userBuilder.with(username, password, name, email).withRoles(roles);
        SystemUser newUser = userBuilder.build();
        return (SystemUser)this.userRepository.save(newUser);
    }

    public Iterable<SystemUser> activeUsers() {
        return this.userRepository.findByActive(true);
    }

    public Iterable<SystemUser> deactivatedUsers() {
        return this.userRepository.findByActive(false);
    }

    public Iterable<SystemUser> allUsers() {
        return this.userRepository.findAll();
    }

    public Optional<SystemUser> userOfIdentity(final Username id) {
        return this.userRepository.ofIdentity(id);
    }

    public SystemUser deactivateUser(final SystemUser user) {
        user.deactivate(CurrentTimeCalendars.now());
        return (SystemUser)this.userRepository.save(user);
    }
}

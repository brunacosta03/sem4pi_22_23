package application;

import domain.model.User;
import domain.model.UserSession;
import domain.repositories.UserRepository;
import eapli.framework.general.domain.model.EmailAddress;
import eapli.framework.infrastructure.authz.application.PasswordPolicy;
import eapli.framework.infrastructure.authz.application.exceptions.UnauthenticatedException;
import eapli.framework.infrastructure.authz.domain.model.Role;
import eapli.framework.time.util.CurrentTimeCalendars;
import domain.model.UserBuilder;
import exception.UnauthorizedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Calendar;
import java.util.Optional;

@Service
public class UserManagementService {
    private final UserRepository userRepository;
    private final PasswordEncoder encoder;
    private final PasswordPolicy policy;

    /**
     *
     * @param userRepo
     * @param encoder
     * @param policy
     */
    @Autowired
    public UserManagementService(final UserRepository userRepo, final PasswordPolicy policy,
                                 final PasswordEncoder encoder) {
        userRepository = userRepo;
        this.policy = policy;
        this.encoder = encoder;
    }

    /**
     * Registers a new user in the system allowing to specify when the user account
     * was created.
     *
     * @param username
     * @param rawPassword
     * @param firstName
     * @param email
     * @param role
     * @param createdOn
     * @return the new user
     */
    public User registerNewUser(final String username, final String rawPassword, final String firstName,
                                final String email, final String role, final String birthDate,
                                final String mecNumber, final String taxPayerNumber,
                                final String acronym, final Calendar createdOn) {

        final var userBuilder = new UserBuilder(policy, encoder);

        userBuilder.with(username, rawPassword, firstName, email, birthDate, role, taxPayerNumber)
                .createdOn(createdOn).withMecanographicNumber(mecNumber).withAcronym(acronym);
        final var newUser = userBuilder.build();

        return userRepository.save(newUser);
    }

    /**
     * Registers a new user in the system.
     *
     * @param username
     * @param rawPassword
     * @param firstName
     * @param email
     * @param role
     * @return the new user
     */
    public User registerNewUser(final String username, final String rawPassword, final String firstName,
                                final String email, final String role, final String birthDate,
                                final String mecNumber, final String taxPayerNumber, final String acronym) {
        return registerNewUser(username, rawPassword, firstName, email,
                role, birthDate, mecNumber, taxPayerNumber, acronym, CurrentTimeCalendars.now());
    }

    /**
     *
     * @return all active users
     */
    public Iterable<User> activeUsers() {
        return userRepository.findByActive(true);
    }

    /**
     *
     * @return all deactivated users
     */
    public Iterable<User> deactivatedUsers() {
        return userRepository.findByActive(false);
    }

    /**
     *
     * @return all users no matter their status
     */
    public Iterable<User> allUsers() {
        return userRepository.findAll();
    }

    /**
     * Looks up a user by its username.
     *
     * @param id
     * @return an Optional which value is the user with the desired identify. an
     *         empty Optional if there is no user with that username
     */
    public Optional<User> userOfIdentity(final EmailAddress id) {
        return userRepository.ofIdentity(id);
    }

    /**
     * Deactivates a user. Client code must not reference the input parameter after
     * calling this method and must use the returned object instead.
     *
     * @param user
     * @return the updated user.
     */
    @Transactional
    public User deactivateUser(final User user) {
        user.deactivate(CurrentTimeCalendars.now());
        return userRepository.save(user);
    }
}

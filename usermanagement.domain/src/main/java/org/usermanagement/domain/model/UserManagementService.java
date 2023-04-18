package org.usermanagement.domain.model;

import eapli.framework.general.domain.model.EmailAddress;
import eapli.framework.infrastructure.authz.application.PasswordPolicy;
import eapli.framework.time.util.CurrentTimeCalendars;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.usermanagement.domain.repositories.UserRepository;

import java.util.Calendar;
import java.util.Optional;

@Service
public class UserManagementService {
    /**
     * UserRepository.
     */
    private final UserRepository userRepository;
    /**
     * PasswordEncoder.
     */
    private final PasswordEncoder encoder;
    /**
     * PasswordPolicy with rules.
     */
    private final PasswordPolicy policy;

    /**
     *
     * @param userRepo
     * @param encoderp
     * @param policyp
     */
    @Autowired
    public UserManagementService(final UserRepository userRepo,
                                 final PasswordPolicy policyp,
                                 final PasswordEncoder encoderp) {
        userRepository = userRepo;
        this.policy = policyp;
        this.encoder = encoderp;
    }

    /**
     * Registers a new user in the system allowing to
     * specify when the user account was created.
     * @param username
     * @param rawPassword
     * @param firstName
     * @param email
     * @param role
     * @param birthDate
     * @param mecNumber
     * @param taxPayerNumber
     * @param acronym
     * @param createdOn
     * @return User
     */
    public User registerNewUser(final String username, final String rawPassword,
                                final String firstName, final String email,
                                final String role, final String birthDate,
                                final String mecNumber,
                                final String taxPayerNumber,
                                final String acronym,
                                final Calendar createdOn) {

        final var userBuilder = new UserBuilder(policy, encoder);

        userBuilder.with(username, rawPassword, firstName,
                        email, birthDate, role, taxPayerNumber)
                .createdOn(createdOn)
                .withMecanographicNumber(mecNumber)
                .withAcronym(acronym);
        final var newUser = userBuilder.build();

        return userRepository.save(newUser);
    }

    /**
     * Registers a new user in the system.
     * @param username
     * @param rawPassword
     * @param firstName
     * @param email
     * @param role
     * @param birthDate
     * @param mecNumber
     * @param taxPayerNumber
     * @param acronym
     * @return User
     */
    public User registerNewUser(final String username, final String rawPassword,
                                final String firstName, final String email,
                                final String role, final String birthDate,
                                final String mecNumber,
                                final String taxPayerNumber,
                                final String acronym) {
        return registerNewUser(username, rawPassword, firstName, email,
                role, birthDate, mecNumber, taxPayerNumber,
                acronym, CurrentTimeCalendars.now());
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
     * Deactivates a user. Client code must not
     * reference the input parameter after.
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

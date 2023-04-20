package org.usermanagement.domain.model;

import eapli.framework.general.domain.model.EmailAddress;
import eapli.framework.infrastructure.authz.application.PasswordPolicy;
import eapli.framework.time.util.CurrentTimeCalendars;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.user.management.CourseRoles;
import org.usermanagement.domain.repositories.UserRepository;

import java.time.LocalDateTime;
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
     * @param shortName
     * @param rawPassword
     * @param fullName
     * @param email
     * @param role
     * @param birthDate
     * @param taxPayerNumber
     * @param acronym
     * @param createdOn
     * @return User
     */
    public User registerNewUser(final String shortName, final String rawPassword,
                                final String fullName, final String email,
                                final String role, final String birthDate,
                                final String taxPayerNumber,
                                final String acronym,
                                final Calendar createdOn) {
        final var userBuilder = new UserBuilder(policy, encoder);

        userBuilder.with(shortName, rawPassword, fullName,
                        email, birthDate, role, taxPayerNumber)
                .createdOn(createdOn)
                .withAcronym(acronym);

        if(String.valueOf(CourseRoles.STUDENT)
                .compareTo(role) == 0){

            userBuilder.withMecanographicNumber(generateMecNumber());
        }

        final var newUser = userBuilder.build();

        return userRepository.save(newUser);
    }

    /**
     * Generate MecanographicNumber for users with role Student
     * @return String for builder create MecanographicNumber
     */
    private String generateMecNumber(){
        MecanographicNumber mecanographicNumber = userRepository.findMaxYearMecanographicNumber();

        if(mecanographicNumber == null){
            return String.valueOf(LocalDateTime.now().getYear() * 100000 + 1);
        }

        mecanographicNumber.nextNumber();

        return mecanographicNumber.value();
    }

    /**
     * Registers a new user in the system.
     * @param shortName
     * @param rawPassword
     * @param fullName
     * @param email
     * @param role
     * @param birthDate
     * @param taxPayerNumber
     * @param acronym
     * @return User
     */
    public User registerNewUser(final String shortName, final String rawPassword,
                                final String fullName, final String email,
                                final String role, final String birthDate,
                                final String taxPayerNumber,
                                final String acronym) {
        return registerNewUser(shortName, rawPassword, fullName, email,
                role, birthDate, taxPayerNumber,
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

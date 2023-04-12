package org.bootstrappers;

import application.AuthenticationService;
import application.AuthorizationService;
import application.AuthzRegistry;
import domain.model.User;
import domain.model.UserBuilder;
import domain.repositories.UserRepository;
import eapli.framework.actions.Action;
import eapli.framework.domain.repositories.ConcurrencyException;
import eapli.framework.domain.repositories.IntegrityViolationException;
import eapli.framework.validations.Invariants;
import org.bootstrappers.demo.UsersBootstrapper;
import org.user.management.CourseRoles;
import domain.model.UserBuilderHelper;
import persistence.PersistenceContext;

public class ECourseBootstrapper implements Action {
    /**
     * The repository for managing user entities.
     */
    private final UserRepository userRepository = PersistenceContext.repositories().users();
    /**
     * The authorization service for managing user authorization.
     */
    private final AuthorizationService authz = AuthzRegistry.authorizationService();
    /**
     * The authentication service for managing user authentication.
     */
    private final AuthenticationService authenticationService = AuthzRegistry.authenticationService();

    /**
     * The short name for the manager user used during bootstrapping.
     */
    private static final String MANAGERBOOTSTRAP_SHORT_NAME = "manager";

    /**
     * The full name for the manager user used during bootstrapping.
     */
    private static final String MANAGERBOOTSTRAP_FULL_NAME = "manager bootstrap";

    /**
     * The password for the manager user used during bootstrapping.
     */
    private static final String MANAGERBOOTSTRAP_EMAIL = "managerbootstrap@email.com";

    /**
     * The password for the manager user used during bootstrapping.
     */
    private static final String MANAGERBOOTSTRAP_PASSWORD = "ManagerPassword1";

    /**
     * Executes the bootstrapping action by registering the manager user.
     * Invoke the users bootstrapper.
     * @return true if the bootstrapping action is successful, false otherwise.
     */
    @Override
    public boolean execute() {
        registerManagerUser(userRepository);
        authenticateForBootstrapping();

        UsersBootstrapper usersBootstrapper = new UsersBootstrapper();
        System.out.println("Bootstrapper --> "
                            + usersBootstrapper.getClass().getSimpleName());

        return usersBootstrapper.execute();
    }

    /**
     * Registers the manager user in the system during bootstrapping.
     * @param userRepository the repository for managing user entities.
     * @return true if the registration is successful, false otherwise.
     */
    public static boolean registerManagerUser(
                            final UserRepository userRepository) {
        final UserBuilder userBuilder = UserBuilderHelper.builder();

        userBuilder
                .withShortName(MANAGERBOOTSTRAP_SHORT_NAME)
                .withFullName(MANAGERBOOTSTRAP_FULL_NAME)
                .withPassword(MANAGERBOOTSTRAP_PASSWORD)
                .withEmail(MANAGERBOOTSTRAP_EMAIL)
                .withRole(CourseRoles.MANAGER)
                .withTaxPayerNumber("999999999")
                .withBirthDate("16/11/2002");
        final User newUser = userBuilder.build();

        try {
            final User managerUser = userRepository.save(newUser);
            assert managerUser != null;
            return true;
        } catch (ConcurrencyException | IntegrityViolationException e) {
            return false;
        }
    }

    /**
     * Authenticates the manager user.
     */
    protected void authenticateForBootstrapping() {
        authenticationService.authenticate(MANAGERBOOTSTRAP_EMAIL,
                MANAGERBOOTSTRAP_PASSWORD);
        Invariants.ensure(authz.hasSession());
    }
}

package org.bootstrappers;

import eapli.framework.actions.Action;
import eapli.framework.domain.repositories.ConcurrencyException;
import eapli.framework.domain.repositories.IntegrityViolationException;
import eapli.framework.infrastructure.authz.application.AuthenticationService;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;
import eapli.framework.infrastructure.authz.domain.model.SystemUser;
import eapli.framework.infrastructure.authz.domain.model.SystemUserBuilder;
import eapli.framework.infrastructure.authz.domain.repositories.UserRepository;
import eapli.framework.validations.Invariants;
import org.bootstrappers.demo.UsersBootstrapper;
import org.user.management.CourseRoles;
import org.user.management.UserBuilderHelper;
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
     * The username for the manager user used during bootstrapping.
     */
    private static final String MANAGERBOOTSTRAP_USERNAME = "managerbootstrap";
    /**
     * The password for the manager user used during bootstrapping.
     */
    private static final String MANAGERBOOTSTRAP_PASSWORD = "managerpassword";

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
        final SystemUserBuilder userBuilder = UserBuilderHelper.builder();

        userBuilder
                .withUsername(MANAGERBOOTSTRAP_USERNAME)
                .withPassword(MANAGERBOOTSTRAP_PASSWORD)
                .withName("manager", "bootstrap")
                .withEmail("managerbootstrap@email.com")
                .withRoles(CourseRoles.MANAGER);
        final SystemUser newUser = userBuilder.build();

        try {
            final SystemUser managerUser = userRepository.save(newUser);
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
        authenticationService.authenticate(MANAGERBOOTSTRAP_USERNAME,
                MANAGERBOOTSTRAP_PASSWORD);
        Invariants.ensure(authz.hasSession());
    }
}

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
    private final UserRepository userRepository = PersistenceContext.repositories().users();
    private final AuthorizationService authz = AuthzRegistry.authorizationService();
    private final AuthenticationService authenticationService = AuthzRegistry.authenticationService();
    private static final String MANAGERBOOTSTRAP_USERNAME = "managerbootstrap";
    private static final String MANAGERBOOTSTRAP_PASSWORD = "managerpassword";

    @Override
    public boolean execute() {
        registerManagerUser(userRepository);
        authenticateForBootstrapping();

        UsersBootstrapper usersBootstrapper = new UsersBootstrapper();
        System.out.println("Bootstrapper --> " + usersBootstrapper.getClass().getSimpleName());

        return usersBootstrapper.execute();
    }

    public static boolean registerManagerUser(final UserRepository userRepository) {
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

    protected void authenticateForBootstrapping() {
        authenticationService.authenticate(MANAGERBOOTSTRAP_USERNAME, MANAGERBOOTSTRAP_PASSWORD);
        Invariants.ensure(authz.hasSession());
    }
}

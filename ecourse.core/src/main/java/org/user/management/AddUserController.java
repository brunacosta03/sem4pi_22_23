package org.user.management;

import eapli.framework.application.UseCaseController;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;
import eapli.framework.infrastructure.authz.application.UserManagementService;
import eapli.framework.infrastructure.authz.domain.model.Role;
import eapli.framework.infrastructure.authz.domain.model.SystemUser;
import eapli.framework.time.util.CurrentTimeCalendars;

import java.util.Calendar;
import java.util.Set;

/**
 * Controller class for adding a new user to the system.
 */
@UseCaseController
public class AddUserController {
    /**
     * Authorization service instance.
     */
    private final AuthorizationService authz = AuthzRegistry.authorizationService();
    /**
     * User management service instance.
     */
    private final UserManagementService userSvc = AuthzRegistry.userService();

    /**
     * Get all available role types for a user.
     * @return An array of all available role types.
     */
    public Role[] getRoleTypes() {
        return CourseRoles.allRoles();
    }

    /**
     * Add a new user to the system with the provided details.
     * @param username  username of the new user.
     * @param password  password of the new user.
     * @param firstName first name of the new user.
     * @param lastName  last name of the new user.
     * @param email     email of the new user.
     * @param roles     roles of the new user.
     * @param createdOn creation date of the new user.
     * @return SystemUser object.
     */
    public SystemUser addUser(final String username,
                              final String password,
                              final String firstName,
                              final String lastName,
                              final String email,
                              final Set<Role> roles,
                              final Calendar createdOn) {
        authz.ensureAuthenticatedUserHasAnyOf(CourseRoles.MANAGER);

        return userSvc.registerNewUser(username, password, firstName,
                lastName, email, roles, createdOn);
    }

    /**
     * Add a new user to the system with the provided details.
     * @param username  username of the new user.
     * @param password  password of the new user.
     * @param firstName first name of the new user.
     * @param lastName  last name of the new user.
     * @param email     email of the new user.
     * @param roles     roles of the new user.
     * @return SystemUser.
     */
    public SystemUser addUser(final String username,
                              final String password,
                              final String firstName,
                              final String lastName,
                              final String email,
                              final Set<Role> roles) {

        return addUser(username, password, firstName,
                lastName, email, roles, CurrentTimeCalendars.now());
    }
}

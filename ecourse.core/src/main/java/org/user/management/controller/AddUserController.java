package org.user.management.controller;

import application.AuthorizationService;
import application.AuthzRegistry;
import application.UserManagementService;
import domain.model.User;
import eapli.framework.application.UseCaseController;
import eapli.framework.time.util.CurrentTimeCalendars;
import org.user.management.CourseRoles;

import java.util.Calendar;

/**
 * Controller class for adding a new user to the system.
 */
@UseCaseController
public class AddUserController {
    /**
     * Authorization service instance.
     */
    private final AuthorizationService authz = AuthzRegistry
                                            .authorizationService();
    /**
     * User management service instance.
     */
    private final UserManagementService userSvc = AuthzRegistry.userService();


    /**
     * Add a new user to the system with the provided details.
     * @param shortName  short name of the new user.
     * @param password  password of the new user.
     * @param fullName full name of the new user.
     * @param email     email of the new user.
     * @param role     roles of the new user.
     * @param birthDate     birthdate of the new user.
     * @param mecNumber     mec number of the new user.
     * @param taxPayerNumber     taxPayerNumber of the new user.
     * @param acronym     acronym of the new user.
     * @param createdOn creation date of the new user.
     * @return SystemUser object.
     */
    public User addUser(final String shortName, final String password,
                        final String fullName, final String email,
                        final String role, final String birthDate,
                        final String mecNumber, final String taxPayerNumber,
                        final String acronym, final Calendar createdOn) {
        authz.ensureAuthenticatedUserHasAnyOf(CourseRoles.MANAGER);

        return userSvc.registerNewUser(shortName, password, fullName,
                                    email, role, birthDate, mecNumber,
                                    taxPayerNumber, acronym, createdOn);
    }

    /**
     * Add a new user to the system with the provided details.
     * @param shortName  short name of the new user.
     * @param password  password of the new user.
     * @param fullName full name of the new user.
     * @param email     email of the new user.
     * @param role     roles of the new user.
     * @param birthDate     birthdate of the new user.
     * @param mecNumber     mec number of the new user.
     * @param taxPayerNumber     taxPayerNumber of the new user.
     * @param acronym     acronym of the new user.
     * @return User
     */
    public User addUser(final String shortName, final String password,
                        final String fullName, final String email,
                        final String role, final String birthDate,
                        final String mecNumber,
                        final String taxPayerNumber, final String acronym) {

        return addUser(shortName, password, fullName,
                        email, role, birthDate, mecNumber,
                        taxPayerNumber, acronym, CurrentTimeCalendars.now());
    }
}

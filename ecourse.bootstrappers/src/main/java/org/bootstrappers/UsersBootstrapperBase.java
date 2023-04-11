package org.bootstrappers;

import eapli.framework.domain.repositories.ConcurrencyException;
import eapli.framework.domain.repositories.IntegrityViolationException;
import eapli.framework.infrastructure.authz.domain.model.Role;
import eapli.framework.infrastructure.authz.domain.model.SystemUser;
import org.user.management.AddUserController;

import java.util.Set;

public class UsersBootstrapperBase {
    /**
     * Instance Add User Controller.
     */
    private final AddUserController userController = new AddUserController();

    /**
     * User Bootstrapper Base Constructor.
     */
    public UsersBootstrapperBase() {
        super();
    }

    /**
     * Register user for boostrap.
     * @param username
     * @param pwd
     * @param firstName
     * @param lastName
     * @param email
     * @param roles
     * @return SystemUser
     */
    protected SystemUser registerUser(final String username,
                                      final String pwd,
                                      final String firstName,
                                      final String lastName,
                                      final String email,
                                      final Set<Role> roles) {
        SystemUser u = null;

        try {
            u = userController.addUser(username, pwd, firstName,
                    lastName, email, roles);
        } catch (final IntegrityViolationException | ConcurrencyException e) {
            System.out.println(e.getMessage());
        }

        return u;
    }
}

package org.bootstrappers;

import domain.model.User;
import eapli.framework.domain.repositories.ConcurrencyException;
import eapli.framework.domain.repositories.IntegrityViolationException;
import org.user.management.controller.AddUserController;

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
     * @param shortName
     * @param password
     * @param fullName
     * @param email
     * @param role
     * @param birthDate
     * @return User
     */
    protected User registerUser(final String shortName, final String password,
                                final String fullName, final String email,
                                final String role, final String birthDate, final String mecNumber,
                                final String taxPayerNumber, final String acronym) {
        User u = null;

        try {
            u = userController.addUser(shortName, password, fullName,
                     email, role, birthDate, mecNumber, taxPayerNumber, acronym);

            System.out.println("[+] " + email);
        } catch (final IntegrityViolationException | ConcurrencyException e) {
            System.out.println(e.getMessage());
        }

        return u;
    }
}

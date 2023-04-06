package org.bootstrappers;

import eapli.framework.domain.repositories.ConcurrencyException;
import eapli.framework.domain.repositories.IntegrityViolationException;
import eapli.framework.infrastructure.authz.domain.model.Role;
import eapli.framework.infrastructure.authz.domain.model.SystemUser;
import eapli.framework.infrastructure.authz.domain.model.Username;
import org.user.management.AddUserController;

import java.util.Set;

public class UsersBootstrapperBase {
    private final AddUserController userController = new AddUserController();

    public UsersBootstrapperBase() {
        super();
    }

    protected SystemUser registerUser(final String username, final String password, final String firstName,
                                      final String lastName, final String email, final Set<Role> roles) {
        SystemUser u = null;

        try {
            u = userController.addUser(username, password, firstName, lastName, email, roles);
        } catch (final IntegrityViolationException | ConcurrencyException e) {
            System.out.println(e.getMessage());
        }

        return u;
    }
}

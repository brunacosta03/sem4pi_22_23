package application;

import domain.model.User;
import domain.model.UserSession;
import eapli.framework.infrastructure.authz.application.exceptions.UnauthenticatedException;
import eapli.framework.infrastructure.authz.domain.model.Role;
import exception.UnauthorizedException;

import java.util.Optional;

public class AuthorizationService {
    /**
     * Current user session.
     */
    private UserSession session = null;

    /**
     * Create session for user.
     * @param user session is being created.
     * @return user session, or empty if the session creation failed.
     */
    public Optional<UserSession> createUserSession(final User user) {
        if(user != null) {
            this.session = new UserSession(user);
        }else{
            clearSession();
        }
        return session();
    }

    /**
     * Clear the current session
     */
    private void clearSession() {
        this.session = null;
    }

    /**
     * Get the current user session.
     * @return current user session, or empty if no session exists
     */
    public Optional<UserSession> session() {
        return Optional.ofNullable(session);
    }

    /**
     * checks if there is an authenticated user session
     *
     * @return {@code true} if there is an authenticated user session
     */
    public boolean hasSession() {
        return session != null;
    }

    public void ensureAuthenticatedUserHasAnyOf(final Role... actions) {
        final UserSession us = session().orElseThrow(() -> {
            System.out.println("Unauthenticated access attempt");
            return new UnauthenticatedException();
        });
        if (!us.authenticatedUser().hasAnyOf(actions)) {
            System.out.println("Unauthorized access attempt by user" + us.authenticatedUser().emailAddress());
            throw new UnauthorizedException(us.authenticatedUser(), actions);
        }
    }
}
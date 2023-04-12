package application;

import domain.exceptions.UserUnauthorizedException;
import domain.model.User;
import domain.model.UserSession;
import eapli.framework.infrastructure.authz.application.exceptions.UnauthenticatedException;
import eapli.framework.infrastructure.authz.application.exceptions.UnauthorizedException;
import eapli.framework.infrastructure.authz.domain.model.Role;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Optional;

public class AuthorizationService {
    private static final Logger LOGGER = LogManager.getLogger(eapli.framework.infrastructure.authz.application.AuthorizationService.class);
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
     * Make sure the user has a role within the system
     * @param roles, list of possible roles.
     */

    public void ensureUserHasOneOfRoles(Role... roles){
        final UserSession us = session().orElseThrow(() -> {
            LOGGER.info("Unauthenticated access attempt");
            return new UnauthenticatedException();
        });
        if (!us.user().hasAnyOf(roles)) {
            LOGGER.info("Unauthorized access attempt by user {}", us.user().identity());
            throw new UserUnauthorizedException(us.user(), "", roles);
        }
    }
}

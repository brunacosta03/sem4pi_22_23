package application;

import domain.model.User;
import domain.model.UserSession;

import java.util.Optional;

public class AuthorizationService {
    private UserSession session = null;

    /**
     * Create session for user.
     * @param newSession session is being created.
     * @return user session, or empty if the session creation failed.
     */
    public Optional<UserSession> createSessionForUser(final User newSession) {
        Optional<UserSession> user = null;
        return user;
    }

    /**
     * Get the current user session.
     * @return current user session, or empty if no session exists
     */
    public Optional<UserSession> session() {
        return Optional.ofNullable(session);
    }
}

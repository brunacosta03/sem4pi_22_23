package application;

import domain.model.User;
import domain.model.UserSession;

import java.util.Optional;

public class AuthorizationService {

    private UserSession session = null;
    public Optional<UserSession> createSessionForUser(User newSession) {
        Optional<UserSession> user = null;
        return user;
    }

    public Optional<UserSession> session() {
        return Optional.ofNullable(session);
    }
}

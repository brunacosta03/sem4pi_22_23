package domain.model;

import eapli.framework.validations.Preconditions;

import java.time.LocalDateTime;
import java.util.UUID;

public class UserSession {
    private static final long serialVersionUID = 1L;

    private final User authenticatedUser;
    private final UUID token;
    private final LocalDateTime startedOn;

    public UserSession(final User user){
        Preconditions.nonNull(user, "User can't be null");

        authenticatedUser = user;
        token = UUID.randomUUID();
        startedOn = LocalDateTime.now();
    }

    @Override
    public String toString() {
        return authenticatedUser.identity() + "@" + token;
    }

    public User user(){
        return authenticatedUser;
    }
}

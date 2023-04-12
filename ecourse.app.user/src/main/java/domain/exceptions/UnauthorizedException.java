package domain.exceptions;

import domain.model.User;
import eapli.framework.infrastructure.authz.domain.model.Role;

public class UnauthorizedException extends RuntimeException {

    private final User user;
    private final Role[] roles;


    public UnauthorizedException(final User user, final String message, final Role... roles) {
        super(buildMessage(message, user, roles));
        this.roles = roles;
        this.user = user;
    }

    private static String buildMessage(final String message, final User user, final Role... roles) {
        return "User " + user.identity() + " is not authorized to perform one of these actions: " + roles + "\n"
                + message;
    }
}

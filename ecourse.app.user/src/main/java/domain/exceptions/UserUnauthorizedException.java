package domain.exceptions;

import domain.model.User;
import eapli.framework.infrastructure.authz.application.exceptions.UnauthenticatedException;
import eapli.framework.infrastructure.authz.domain.model.Role;
import eapli.framework.infrastructure.authz.domain.model.SystemUser;

public class UserUnauthorizedException extends RuntimeException {

    private final User user;
    private final Role[] roles;


    public UserUnauthorizedException(final User user, final String message, final Role... roles) {
        super(buildMessage(message, user, roles));
        this.roles = roles;
        this.user = user;
    }

    private static String buildMessage(final String message, final User user, final Role... roles) {
        return "User " + user.identity() + " is not authorized to perform one of these actions: " + roles + "\n"
                + message;
    }
}

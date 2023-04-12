package exception;

import domain.model.User;
import eapli.framework.infrastructure.authz.domain.model.Role;


public class UnauthorizedException extends RuntimeException {
    private final User user;
    private final Role[] roles;
    public UnauthorizedException(final User userp, final Role... rolesp) {
        super(buildMessage("", userp, rolesp));
        this.roles = rolesp;
        this.user = userp;
    }

    private static String buildMessage(final String original, final User user, final Role... roles) {
        return "User " + user.identity() + " is not authorized "
                + "to perform one of these actions: " + roles + "\n" + original;
    }
}

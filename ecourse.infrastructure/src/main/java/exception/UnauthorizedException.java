package exception;

import domain.model.User;
import eapli.framework.infrastructure.authz.domain.model.Role;


public class UnauthorizedException extends RuntimeException {
    /**
     * User doesnt have permission to do what is trying.
     */
    private final User user;

    /**
     * Roles that have permission to do this action.
     */
    private final Role[] roles;

    /**
     * UnauthorizedException constructor.
     * @param userp
     * @param rolesp
     */
    public UnauthorizedException(final User userp, final Role... rolesp) {
        super(buildMessage("", userp, rolesp));
        this.roles = rolesp;
        this.user = userp;
    }

    /**
     * Build message to screen with user and roles.
     * @param original
     * @param user
     * @param roles
     * @return String
     */
    private static String buildMessage(final String original,
                                       final User user,
                                       final Role... roles) {
        return "User " + user.identity() + " is not authorized "
                + "to perform one of these actions: " + roles + "\n" + original;
    }
}

package org.shared.board.server;

import org.authz.application.AuthenticationService;
import org.authz.application.AuthzRegistry;
import org.shared.board.common.MessageCodes;
import org.user.management.CourseRoles;
import org.usermanagement.domain.model.UserSession;

import java.util.Optional;

/**
 * The type Shared board server service.
 */
public class SharedBoardServerService {
    /**
     * Get AuthenticationService.
     */
    private final AuthenticationService authService = AuthzRegistry
            .authenticationService();

    /**
     * Authenticate user int.
     * @param userData the user data
     * @return the int
     * @throws IllegalArgumentException the illegal argument exception
     */
    public int authenticateUser(final String userData)
            throws IllegalArgumentException {
        String email = userData.substring(0, userData.indexOf("\0"));
        String password = userData.substring(
                userData.indexOf("\0") + 1, userData.length() - 1);

        Optional<UserSession> session = authService
                    .authenticate(email, password,
                            CourseRoles.allRoles());

        if (session.isPresent()) {
            return MessageCodes.ACK;
        }

        return MessageCodes.ERR;
    }
}

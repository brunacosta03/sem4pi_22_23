package org.shared.board.server;

import org.apache.commons.httpclient.auth.InvalidCredentialsException;
import org.authz.application.AuthenticationService;
import org.authz.application.AuthzRegistry;
import org.hibernate.Session;
import org.shared.board.common.MessageCodes;
import org.shared.board.server.session.SessionManager;
import org.user.management.CourseRoles;
import org.usermanagement.domain.model.UserSession;

import java.util.Optional;
import java.util.UUID;

/**
 * The type Shared board server service.
 */
public class SharedBoardServerService {
    /**
     * Get AuthenticationService.
     */
    private SessionManager sessionManager = SessionManager.getInstance();

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

        try{
            sessionManager.login(email, password);

            return MessageCodes.ACK;
        } catch (InvalidCredentialsException e){
            return MessageCodes.ERR;
        }
    }
}

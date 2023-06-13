package org.shared.board.server;

import org.authz.application.AuthorizationService;
import org.shared.board.common.Message;

import java.nio.charset.StandardCharsets;

/**
 * The type Shared board server controller.
 */
public class SharedBoardServerController {
    /**
     * SharedBoardServerService.
     */
    private SharedBoardServerService sbSvc;

    /**
     * AU
     */
    private AuthorizationService authz;

    /**
     * Instantiates a new Shared board server controller.
     * @param sbSvcp the sb svcp
     */
    public SharedBoardServerController(final SharedBoardServerService sbSvcp,
                                       final AuthorizationService authzp) {
        this.sbSvc = sbSvcp;
        this.authz = authzp;
    }

    /**
     * Authenticate int.
     * @param data the data
     * @return the int
     */
    public int authenticate(final Message data) {
        String result = new String(data.data(), StandardCharsets.US_ASCII);

        return sbSvc.authenticateUser(result);
    }

    public int createBoard(final Message data) {
        String result = new String(data.data(), StandardCharsets.US_ASCII);

        return sbSvc.createBoard(result, authz.session().get().authenticatedUser());
    }
}

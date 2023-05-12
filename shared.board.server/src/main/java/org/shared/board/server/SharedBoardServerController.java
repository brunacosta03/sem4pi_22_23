package org.shared.board.server;

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
     * Instantiates a new Shared board server controller.
     * @param sbSvcp the sb svcp
     */
    public SharedBoardServerController(final SharedBoardServerService sbSvcp) {
        this.sbSvc = sbSvcp;
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
}

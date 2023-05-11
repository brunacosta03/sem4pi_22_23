package org.shared.board.server;

import org.shared.board.common.Message;

import java.nio.charset.StandardCharsets;

public class SharedBoardServerController {
    private SharedBoardServerService sbSvc;

    public SharedBoardServerController(SharedBoardServerService sbSvcp) {
        this.sbSvc = sbSvcp;
    }

    public int authenticate(Message data){
        String result = new String(data.data(), StandardCharsets.US_ASCII);

        return sbSvc.authenticateUser(result);
    }
}

package org.shared.board.app;

import org.shared.board.common.Message;
import org.shared.board.common.MessageCodes;
import org.shared.board.common.MessageFormat;

import java.net.Socket;

public class SharedBoardAppController {
    private Socket sock;
    private MessageFormat mf;

    public static final int VERSION = 1;

    public SharedBoardAppController(Socket sock) {
        this.sock = sock;
        this.mf = new MessageFormat(sock);
    }

    public int sendCommunicationTest(){
        mf.sendMessage(VERSION, MessageCodes.COMMTEST, "");

        Message result = mf.readMessage();

        return result.code();
    }

    public int sendEndOfSession(){
        mf.sendMessage(VERSION, MessageCodes.DISCONN, "");

        Message result = mf.readMessage();

        return result.code();
    }

    public Message authenticate(String data){
        mf.sendMessage(VERSION, MessageCodes.AUTH, data);

        Message result = mf.readMessage();

        return result;
    }
}

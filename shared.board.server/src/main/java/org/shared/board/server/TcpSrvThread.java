package org.shared.board.server;

import org.shared.board.common.Message;
import org.shared.board.common.MessageCodes;
import org.shared.board.common.MessageFormat;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;

public class TcpSrvThread implements Runnable {
    private Socket sock;

    private SharedBoardServerController theController;

    public TcpSrvThread(Socket cli_s) {
        sock = cli_s;
        theController = new SharedBoardServerController(new SharedBoardServerService());
    }

    public void run() {
        Message message;
        InetAddress clientIP;

        clientIP = sock.getInetAddress();

        System.out.println("New client connection from " + clientIP.getHostAddress() +
                    ", port number " + sock.getPort());
        try {
            MessageFormat mf = new MessageFormat(sock);

            do {
                message = mf.readMessage();

                if (message.code() == MessageCodes.COMMTEST) {
                    mf.sendMessage(1, MessageCodes.ACK, "");
                }

                if(message.code() == MessageCodes.AUTH){
                    try{
                        int result = theController.authenticate(message);

                        mf.sendMessage(1, result, "");
                    } catch (IllegalArgumentException e){
                        mf.sendMessage(1, MessageCodes.ERR, e.getMessage());
                    }
                }
            } while(message.code() != MessageCodes.DISCONN);

            mf.sendMessage(1, MessageCodes.ACK, "");
            System.out.println("Client " + clientIP.getHostAddress() + ", port number: " + sock.getPort() +
                        " disconnected");

            sock.close();
        } catch(IOException ex) {
            System.out.println("IOException");
        }
    }
}

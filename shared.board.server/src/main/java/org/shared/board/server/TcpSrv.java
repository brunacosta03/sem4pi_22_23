package org.shared.board.server;

import eapli.framework.infrastructure.authz.domain.model.PlainTextEncoder;
import org.authz.application.AuthzRegistry;
import org.persistence.PersistenceContext;
import org.usermanagement.domain.model.ECoursePasswordPolicy;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * The type Tcp srv.
 */
class TcpSrv {
    /**
     * The tcp Sock.
     */
    private static ServerSocket tcpSock;

    /**
     * The tcp PORT.
     */
    private static final int TCP_PORT = 9999;

    /**
     * Main.
     *
     * @param args the args
     * @throws Exception the exception
     */
    public static void main(final String args[]) throws Exception {
        Socket tpcCliSock;

        try {
            tcpSock = new ServerSocket(TCP_PORT);
            System.out.println("Server is listening");
        } catch (IOException ex) {
            System.out.println("Failed to open server socket");
            System.exit(1);
        }

        int httpPort = 8000;

        while (true) {
            tpcCliSock = tcpSock.accept();

            AuthzRegistry.configure(
                    PersistenceContext.repositories().users(),
                    new PlainTextEncoder(),
                    new ECoursePasswordPolicy()
            );

            new Thread(new TcpSrvThread(tpcCliSock, httpPort)).start();

            httpPort++;
        }
    }
}

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
     * The Sock.
     */
    private static ServerSocket sock;

    /**
     * The PORT.
     */
    private static final int PORT = 9999;

    /**
     * Main.
     *
     * @param args the args
     * @throws Exception the exception
     */
    public static void main(final String args[]) throws Exception {
        Socket cliSock;

        try {
            sock = new ServerSocket(PORT);
            System.out.println("Server is listening");
        } catch (IOException ex) {
            System.out.println("Failed to open server socket");
            System.exit(1);
        }

        AuthzRegistry.configure(
                PersistenceContext.repositories().users(),
                new PlainTextEncoder(),
                new ECoursePasswordPolicy()
        );

        while (true) {
            cliSock = sock.accept();
            new Thread(new TcpSrvThread(cliSock)).start();
        }
    }
}

package org.shared.board.server;

import eapli.framework.infrastructure.authz.domain.model.PlainTextEncoder;
import org.authz.application.AuthorizationService;
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
     * The http Sock.
     */
    private static ServerSocket httpSock;

    /**
     * The http PORT.
     */
    private static final int HTTP_PORT = 8000;

    /**
     * Base folder with myboards.html
     */
    private static final String BASE_FOLDER = "shared.board.server/src/main/java/org/shared/board/server/www";

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
            httpSock = new ServerSocket(HTTP_PORT);
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

        HttpServerAjax httpServerAjax = new HttpServerAjax();

        new Thread(() -> {
            while (true) {
                try {
                    Socket httpCliSock = httpSock.accept();

                    new Thread(new HttpRequestThread(httpCliSock,
                            BASE_FOLDER, httpServerAjax)).start();
                } catch (IOException e) {
                    System.out.println("Failed to accept connection");
                }
            }
        }).start();

        while (true) {
            tpcCliSock = tcpSock.accept();

            new Thread(new TcpSrvThread(tpcCliSock)).start();
        }
    }
}

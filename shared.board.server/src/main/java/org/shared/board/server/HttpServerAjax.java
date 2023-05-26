package org.shared.board.server;

import eapli.framework.domain.repositories.IntegrityViolationException;
import org.apache.commons.httpclient.auth.InvalidCredentialsException;
import org.authz.application.AuthenticationService;
import org.authz.application.AuthorizationService;
import org.authz.application.AuthzRegistry;
import org.boards.controller.CreateBoardController;
import org.domain.model.BoardEntry;
import org.shared.board.common.MessageCodes;
import org.shared.board.server.request_bodys.BoardBody;
import org.shared.board.server.request_bodys.LoginBody;
import org.user.management.CourseRoles;
import org.usermanagement.domain.model.UserSession;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

public class HttpServerAjax {
    private AuthorizationService authz;

    /**
     * Get AuthenticationService.
     */
    private AuthenticationService authService = AuthzRegistry
            .authenticationService();

    /**
     * The constant MIN_ROWS_COLUMNS.
     */
    private static final String MIN_ROWS_COLS = "1";

    public HttpServerAjax(AuthorizationService authzp) {
        this.authz = authzp;
    }

    public synchronized String getAuthenticatedUser()
            throws NoSuchElementException {
        String textHtml = String.valueOf(authz.session().get().authenticatedUser().identity());

        return textHtml;
    }

    public String createBoard(BoardBody requestBody)
            throws IntegrityViolationException, NumberFormatException {
        CreateBoardController theController = new CreateBoardController(authz);
        List<BoardEntry> allBoardEntrys = new ArrayList<>();
        List<String> boardEntrys = requestBody.boardEntrys();
        int boardNColumn = Integer.parseInt(requestBody.boardNColumn());
        int boardNRow = Integer.parseInt(requestBody.boardNRow());

        for(int i = 1; i <= boardNColumn; i++) {
            BoardEntry boardEntry = theController.createBoardEntry(
                    String.valueOf(i),
                    MIN_ROWS_COLS,
                    String.valueOf(i),
                    boardEntrys.get(i - 1),
                    requestBody.boardNRow(),
                    requestBody.boardNColumn()
            );

            allBoardEntrys.add(boardEntry);
        }

        int j = boardNColumn;
        for(int i = 2; i <= boardNRow; i++){
            BoardEntry boardEntry = theController.createBoardEntry(
                    String.valueOf(i),
                    String.valueOf(i),
                    MIN_ROWS_COLS,
                    boardEntrys.get(j),
                    requestBody.boardNRow(),
                    requestBody.boardNColumn()
            );

            j++;
            allBoardEntrys.add(boardEntry);
        }

        theController.createBoard(
                requestBody.boardTitle(),
                requestBody.boardNRow(),
                requestBody.boardNColumn(),
                allBoardEntrys);

        return "Board created successfully!";
    }

    public String login(LoginBody body)
            throws InvalidCredentialsException {
        Optional<UserSession> session = authService
                .authenticate(body.email(), body.password(),
                        CourseRoles.allRoles());

        if (!session.isPresent()) {
            throw new InvalidCredentialsException("Invalid Credentials!");
        }

        return "User logged in successfully";
    }
}

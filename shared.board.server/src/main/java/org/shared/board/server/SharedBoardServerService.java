package org.shared.board.server;

import eapli.framework.io.util.Console;
import org.apache.commons.httpclient.auth.InvalidCredentialsException;
import org.authz.application.AuthenticationService;
import org.authz.application.AuthzRegistry;
import org.boards.controller.CreateBoardController;
import org.domain.model.BoardEntry;
import org.hibernate.Session;
import org.shared.board.common.MessageCodes;
import org.shared.board.server.session.SessionManager;
import org.user.management.CourseRoles;
import org.usermanagement.domain.model.User;
import org.usermanagement.domain.model.UserSession;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * The type Shared board server service.
 */
public class SharedBoardServerService {
    /**
     * Get AuthenticationService.
     */
    private AuthenticationService authService = AuthzRegistry
            .authenticationService();

    /**
     * The constant MIN_ROWS_COLUMNS.
     */
    private static final String MIN_ROWS_COLS = "1";

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

        Optional<UserSession> session = authService
                .authenticate(email, password,
                        CourseRoles.allRoles());

        if (session.isPresent()) {
            return MessageCodes.ACK;
        }

        return MessageCodes.ERR;
    }

    public int createBoard(final String boardData, User authUser) {
        CreateBoardController boardController = new CreateBoardController();
        final String boardTitle = getStringByIndex(0, boardData);
        final String boardNCol = getStringByIndex(1, boardData);
        final String boardNRow = getStringByIndex(2, boardData);
        List<BoardEntry> allBoardEntrys = new ArrayList<>();

        int j = 3;

        for(int i = 1; i <= Integer.parseInt(boardNCol); i++){
            final String entryTitle = getStringByIndex(j, boardData);

            BoardEntry boardEntry = boardController.createBoardEntry(
                    String.valueOf(i),
                    MIN_ROWS_COLS,
                    String.valueOf(i),
                    entryTitle,
                    boardNRow,
                    boardNCol,
                    authUser
            );

            allBoardEntrys.add(boardEntry);
            j++;
        }

        for(int i = 2; i <= Integer.parseInt(boardNRow); i++){
            final String entryTitle = getStringByIndex(j, boardData);

            BoardEntry boardEntry = boardController.createBoardEntry(
                    String.valueOf(i),
                    String.valueOf(i),
                    MIN_ROWS_COLS,
                    entryTitle,
                    boardNRow,
                    boardNCol,
                    authUser
            );

            allBoardEntrys.add(boardEntry);
            j++;
        }

        boardController.createBoard(
                boardTitle,
                boardNRow,
                boardNCol,
                allBoardEntrys,
                authUser);

        return MessageCodes.ACK;
    }

    private static String getStringByIndex(int index, String input) {
        String[] substrings = input.split("\0");

        if (index < 0 || index >= substrings.length) {
            return "";
        }

        return substrings[index];
    }
}

package org.boards.controller;

import eapli.framework.application.UseCaseController;
import org.authz.application.AuthorizationService;
import org.authz.application.AuthzRegistry;
import org.boards.service.BoardService;
import org.domain.model.Board;
import org.domain.model.BoardEntry;
import org.domain.model.BoardEntryFactory;
import org.persistence.PersistenceContext;
import org.user.management.CourseRoles;

import java.util.List;

/**
 * Controller class for adding a new user to the system.
 */
@UseCaseController
public class CreateBoardController {
    /**
     * Authorization service instance.
     */
    private final AuthorizationService authz = AuthzRegistry
                                            .authorizationService();

    private final BoardService boardSvc = new BoardService(
            PersistenceContext.repositories().boards());

    public Board createBoard(final String boardTitlep,
                             final String boardNRowp,
                             final String boardNColp,
                             final List<BoardEntry> allBoardEntrys) {
        authz.ensureAuthenticatedUserHasAnyOf(CourseRoles.allRoles());

        return boardSvc.createBoard(boardTitlep, boardNRowp, boardNColp,
                allBoardEntrys, authz.session().get().authenticatedUser());
    }

    public BoardEntry createBoardEntry(final String entryNumberp,
                                       final String boardRowp,
                                       final String boardColp,
                                       final String entryTitlep,
                                       final String boardNRowp,
                                       final String boardNColps) {
        authz.ensureAuthenticatedUserHasAnyOf(CourseRoles.allRoles());

        return new BoardEntryFactory().create(
                entryNumberp,
                boardRowp,
                boardColp,
                entryTitlep,
                boardNRowp,
                boardNColps
        );
    }
}

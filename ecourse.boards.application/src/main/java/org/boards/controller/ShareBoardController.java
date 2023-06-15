package org.boards.controller;

import eapli.framework.application.UseCaseController;
import eapli.framework.validations.Preconditions;
import org.boards.service.BoardService;
import org.domain.model.AccessLevel;
import org.domain.model.Board;
import org.domain.model.BoardPermission;
import org.domain.repositories.BoardRepository;
import org.usermanagement.domain.model.User;
import org.usermanagement.domain.repositories.UserRepository;

@UseCaseController
public class ShareBoardController {

    private final UserRepository userRepo;
    private final BoardRepository boardRepo;

    private final BoardService service;
    public ShareBoardController(UserRepository userRepo,
                                BoardRepository boardRepo){
        this.userRepo = userRepo;
        this.boardRepo = boardRepo;
        service = new BoardService(boardRepo);
    }

    public BoardPermission shareBoard(final long boardId,
                                      final User user,
                                      final User boardOwner,
                                      final String accessLevel){
        Board board = boardRepo.ofIdentity(boardId).orElse(null);

        Preconditions.nonNull(board, "The board is not on the system");

        Preconditions.ensure(board.boardOwner().emailAddress().toString().equals(boardOwner.emailAddress().toString()),
                "The user is not the owner of this board");

        AccessLevel access = service.confirmLevel(accessLevel);

        return service.addToBoard(board, user, access);
    }
}
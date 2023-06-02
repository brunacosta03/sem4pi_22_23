package org.boards.controller;

import eapli.framework.validations.Preconditions;
import org.domain.model.Board;
import org.domain.repositories.BoardRepository;
import org.usermanagement.domain.model.User;

public class GetBoardsController {
    /**
     * Create a board repository.
     */
    private final BoardRepository boardRepository;

    public GetBoardsController(BoardRepository repository) {
        boardRepository = repository;
    }

    public Iterable<Board> getBoardsByUser(final User authUser) {
        Preconditions.ensure(authUser != null,
                "You need to authenticate first");

        return boardRepository.getBoardsByUser(authUser);
    }
}

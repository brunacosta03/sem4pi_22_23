package org.postit.controller;

import eapli.framework.validations.Preconditions;
import org.domain.model.Board;
import org.domain.model.postit.PostIt;
import org.domain.repositories.BoardRepository;
import org.usermanagement.domain.model.User;
import repositories.PostItRepository;

public class ViewBoardHistoryController {

    private final BoardRepository boardRepository;

    private final PostItRepository postItRepository;


    public ViewBoardHistoryController(BoardRepository boardRepository,
                                      PostItRepository postItRepository) {
        this.boardRepository = boardRepository;
        this.postItRepository = postItRepository;
    }


    public Iterable<PostIt> viewBoardHistory(Long boardId,
                                            User authUser) {

        Board board = boardRepository.ofIdentity(boardId).get();

        Preconditions.ensure(board.userHasAnyPermission(authUser),
                "User does not have permission to view this board");

        return postItRepository.getPostItsHistory(board);
    }
}

package org.boards.service;

import org.domain.model.*;
import org.domain.repositories.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.usermanagement.domain.model.User;

import java.util.List;

@Service
public class BoardService {
    /**
     * UserRepository.
     */
    private final BoardRepository boardRepository;

    /**
     * @param boardRepo
     */
    @Autowired
    public BoardService(final BoardRepository boardRepo) {
        boardRepository = boardRepo;
    }

    /**
     * Create board.
     * @param boardTitlep
     * @param boardNRowp
     * @param boardNColp
     * @param allBoardEntrys
     * @param boardOwner
     * @return Board
     */
    public Board createBoard(final String boardTitlep,
                            final String boardNRowp,
                            final String boardNColp,
                            final List<BoardEntry> allBoardEntrys,
                            final User boardOwner) {
        BoardFactory boardFactory = new BoardFactory();
        BoardPermissionFactory boardPerFactory = new BoardPermissionFactory();

        Board newBoard = boardFactory.create(boardTitlep, boardNRowp,
                boardNColp, allBoardEntrys, boardOwner);

        BoardPermission boardPermission = boardPerFactory.create(
                boardOwner, AccessLevelType.WRITE);

        newBoard.addPermission(boardPermission);

        return boardRepository.save(newBoard);
    }
}

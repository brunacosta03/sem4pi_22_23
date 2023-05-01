package org.domain.repositories;

import eapli.framework.domain.repositories.DomainRepository;
import org.domain.model.Board;

import java.util.Optional;

public interface BoardRepository extends DomainRepository<Long, Board> {
    /**
     * Persist board.
     * @param board
     * @return Board
     */
    Board save(Board board);

    /**
     * Get Board with Identity (board id).
     * @param id Board id
     * @return Board
     */
    Optional<Board> ofIdentity(Long id);
}

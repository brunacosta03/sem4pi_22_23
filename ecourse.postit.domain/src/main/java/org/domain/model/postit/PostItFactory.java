package org.domain.model.postit;

import org.domain.model.*;
import org.usermanagement.domain.model.User;

import java.util.List;

/**
 * The type Post it factory.
 */
public class PostItFactory {
    /**
     * PostItFactory constructor.
     */
    public PostItFactory() {

    }

    /**
     * Create post it.
     * @param postItContent the post-it content
     * @param postItRow     the post-it row
     * @param postItColumn  the post-it column
     * @param postItOwner   the post-it owner
     * @param board         the board
     * @return the post it
     */
    public PostIt create(final String postItContent,
                                final String postItRow,
                                final String postItColumn,
                                final User postItOwner,
                                final Board board) {
        return new PostIt(
                PostItContent.of(postItContent),
                PostItRow.of(postItRow, board.boardNRow()),
                PostItColumn.of(postItColumn, board.boardNCol()),
                postItOwner,
                board
        );
    }
}

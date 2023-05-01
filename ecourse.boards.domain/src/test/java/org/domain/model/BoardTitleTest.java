package org.domain.model;

import org.junit.jupiter.api.Test;

import static org.junit.Assert.*;

class BoardTitleTest {
    @Test
    public void testCreateBoardTitleWithValidValue() {
        BoardTitle boardTitle = BoardTitle.of("My Board Title");

        assertEquals("My Board Title", boardTitle.value());
    }

    @Test
    public void testCreateBoardTitleWithNullValue() {
        assertThrows(IllegalArgumentException.class,
                () -> BoardTitle.of(null));
    }

    @Test
    public void testCreateBoardTitleWithEmptyValue() {
        assertThrows(IllegalArgumentException.class,
                () -> BoardTitle.of(""));
    }

    @Test
    public void testCreateBoardTitleWithTooShortValue() {
        assertThrows(IllegalArgumentException.class,
                () -> BoardTitle.of("abc"));
    }

    @Test
    public void testCreateBoardTitleWithTooLongValue() {
        String longTitle = "This is a really long board title that exceeds"
                + "the maximum allowed length of 50 characters";

        assertThrows(IllegalArgumentException.class,
                () -> BoardTitle.of(longTitle));
    }
}
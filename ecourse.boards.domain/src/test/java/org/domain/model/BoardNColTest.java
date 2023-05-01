package org.domain.model;

import org.ecourse.Application;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BoardNColTest {
    @Test
    public void testCreateValidBoardNCol() {
        String validValue = "5";

        BoardNCol boardNCol = BoardNCol.of(validValue);

        assertNotNull(boardNCol);
        assertEquals(Integer.parseInt(validValue), boardNCol.value());
    }

    @Test
    public void testCreateInvalidBoardNColWithNegativeValue() {
        String invalidValue = "-1";

        assertThrows(IllegalArgumentException.class,
                () -> BoardNCol.of(invalidValue));
    }

    @Test
    public void testCreateInvalidBoardNColWithZeroValue() {
        String invalidValue = "0";

        assertThrows(IllegalArgumentException.class,
                () -> BoardNCol.of(invalidValue));
    }

    @Test
    public void testCreateInvalidBoardNColWithValueGreaterThanMax() {
        String invalidValue = Application.settings().maxColumns() + 1;

        assertThrows(IllegalArgumentException.class,
                () -> BoardNCol.of(invalidValue));
    }
}
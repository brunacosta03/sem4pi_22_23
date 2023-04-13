package domain.model;

import domain.model.ShortName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ShortNameTest {

    @Test
    void ensureShortNameIsNotNull() {
        Throwable thr = assertThrows(IllegalArgumentException.class, () -> {
            ShortName.of(null);
        });

        assertEquals("Short Name can't be null.", thr.getMessage());
    }

    @Test
    void ensureShortNameIsNotEmpty() {
        Throwable thr = assertThrows(IllegalArgumentException.class, () -> {
            ShortName.of("");
        });

        assertEquals("Short Name can't be empty.", thr.getMessage());
    }

    @Test
    void ensureShortNameHasMoreThanThreeCharacters() {
        Throwable thr = assertThrows(IllegalArgumentException.class, () -> {
            ShortName.of("abc");
        });

        assertEquals("Short name must have 3 characters or more", thr.getMessage());
    }

    @Test
    void ensureCreationOfValid() {
        ShortName.of("example");
    }

}
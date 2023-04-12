package domain.model;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class BirthDateTest {

    @Test
    void ensureBirthDateIsNotInTheFuture() {
        Throwable thr = assertThrows(
                IllegalArgumentException.class,
                () -> BirthDate.of(
                        LocalDate.
                                now().
                                plusDays(1)
                )
        );

        assertEquals("Birthdate cannot be in the future", thr.getMessage());
    }

    @Test
    void ensureBirthDateIsNotMoreThan120YearsAgo() {
        Throwable thr = assertThrows(
                IllegalArgumentException.class,
                () -> BirthDate.of(
                        LocalDate.
                                now().
                                minusYears(121)
                )
        );

        assertEquals("Birthdate cannot be more than 120 years ago", thr.getMessage());
    }

    @Test
    void ensureBirthDateCanBeCreatedWhenValueIsInThePast() {
        LocalDate expected = LocalDate.now().minusYears(16);
        BirthDate date = BirthDate.of(
                LocalDate.now().minusYears(16)
        );
        assertEquals(expected, date.value());
    }
}
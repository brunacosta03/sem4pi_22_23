package domain.model;

import domain.model.MecanographicNumber;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class MecanographicNumberTest {

    @Test
    void ensureDoesNotAcceptNegativeValueOrZero() {
        Throwable thr = assertThrows(
                IllegalArgumentException.class,
                () -> MecanographicNumber.of(-11)
        );

        Throwable thrz = assertThrows(
                IllegalArgumentException.class,
                () -> MecanographicNumber.of(0)
        );

        assertEquals("Value cannot be negative or zero", thr.getMessage());
        assertEquals("Value cannot be negative or zero", thrz.getMessage());

    }

    @Test
    void ensureDoesNotAcceptValueGreaterThan99999() {
        Throwable thr = assertThrows(
                IllegalArgumentException.class,
                () -> MecanographicNumber.of(100000)
        );

        assertEquals("Value cannot be greater than 99999 (have more than 5 digits)", thr.getMessage());
    }

    @Test
    void ensureDoesNotAcceptYearGreaterThanCurrentYear() {
        Throwable thr = assertThrows(
                IllegalArgumentException.class,
                () -> MecanographicNumber.of(LocalDate.now().getYear() + 1, 1)
        );

        assertEquals("Year cannot be greater than current year", thr.getMessage());
    }

    @Test
    void canCreateValidBothWays() {
        MecanographicNumber.of(1);
        MecanographicNumber.of(LocalDate.now().getYear(), 1);
    }

    @Test
    void ensureEqualsIsWorking() {
        MecanographicNumber m1 = MecanographicNumber.of(1);
        MecanographicNumber m2 = MecanographicNumber.of(
                LocalDate.now().getYear(),
                1
        );
        MecanographicNumber m3 = MecanographicNumber.of(2);

        assertEquals(m1, m2);
        assertNotEquals(m1, m3);
    }

    @Test
    void ensureStringIsBuildingCorrectly() {
        MecanographicNumber m = MecanographicNumber.of(1);
        String expected = LocalDate.now().getYear() + "00001";
        assertEquals(expected, m.value());

        m = MecanographicNumber.of("201900001");
        expected = "201900001";
        assertEquals(expected, m.value());
    }

}
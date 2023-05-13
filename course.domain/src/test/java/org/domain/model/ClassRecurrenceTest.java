package org.domain.model;

import org.ecourse.Application;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ClassRecurrenceTest {

    @Test
    public void testCreateValidClassRecurrence() {
        String validValue = "MONDAY";

        ClassRecurrence classRecurrence = ClassRecurrence.of(validValue);

        assertNotNull(classRecurrence);
        assertEquals(validValue, classRecurrence.dayOfWeek().toString());
    }

    @Test
    public void testCreateInvalidClassRecurrenceWithNullValue() {
        String invalidValue = null;

        assertThrows(NullPointerException.class,
                () -> ClassRecurrence.of(invalidValue));
    }

    @Test
    public void testCreateInvalidClassRecurrenceWithEmptyValue() {
        String invalidValue = "";

        assertThrows(IllegalArgumentException.class,
                () -> ClassRecurrence.of(invalidValue));
    }

    @Test
    void ClassRecurrenceEquals() {
        ClassRecurrence classRecurrence = ClassRecurrence.of("MONDAY");
        ClassRecurrence classRecurrence2 = ClassRecurrence.of("MONDAY");
        ClassRecurrence classRecurrence3 = ClassRecurrence.of("TUESDAY");

        assertEquals(classRecurrence, classRecurrence2);
        assertNotEquals(classRecurrence, classRecurrence3);
        assertNotEquals(classRecurrence, null);
        assertNotEquals(classRecurrence, new Object());

    }

}
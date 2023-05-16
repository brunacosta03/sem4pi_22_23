package org.domain.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ExamDescriptionTest {

    @Test
    void testCreateValidExamDescription() {
        String validValue = "Exam Description";

        ExamDescription examDescription = ExamDescription.of(validValue);

        assertNotNull(examDescription);
        assertEquals(validValue, examDescription.value());
    }

    @Test
    void testCreateInvalidExamDescriptionWithNullValue() {
        String invalidValue = null;

        assertThrows(NullPointerException.class,
                () -> ExamDescription.of(invalidValue));
    }

    @Test
    void testCreateInvalidExamDescriptionWithEmptyValue() {
        String invalidValue = "";

        assertThrows(IllegalArgumentException.class,
                () -> ExamDescription.of(invalidValue));
    }

    @Test
    void testCreateSmallExamDescription() {
        String invalidValue = "Ex";

        assertThrows(IllegalArgumentException.class,
                () -> ExamDescription.of(invalidValue));
    }

    @Test
    void ExamDescriptionEquals() {
        ExamDescription examDescription = ExamDescription.of("Exam Description");
        ExamDescription examDescription2 = ExamDescription.of("Exam Description");
        ExamDescription examDescription3 = ExamDescription.of("Exam Description 2");

        assertEquals(examDescription, examDescription2);
        assertNotEquals(examDescription, examDescription3);
        assertNotEquals(examDescription, null);
        assertNotEquals(examDescription, new Object());
    }


}
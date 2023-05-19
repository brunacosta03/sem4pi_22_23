package org.domain.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ExamHeaderTest {

    @Test
    void testCreateValidExamDescription() {
        String validValue = "Exam Description";

        ExamHeader examDescription = ExamHeader.of(validValue);

        assertNotNull(examDescription);
        assertEquals(validValue, examDescription.value());
    }

    @Test
    void testCreateInvalidExamDescriptionWithNullValue() {
        String invalidValue = null;

        assertThrows(NullPointerException.class,
                () -> ExamHeader.of(invalidValue));
    }

    @Test
    void testCreateInvalidExamDescriptionWithEmptyValue() {
        String invalidValue = "";

        assertThrows(IllegalArgumentException.class,
                () -> ExamHeader.of(invalidValue));
    }

    @Test
    void testCreateSmallExamDescription() {
        String invalidValue = "Ex";

        assertThrows(IllegalArgumentException.class,
                () -> ExamHeader.of(invalidValue));
    }

    @Test
    void ExamDescriptionEquals() {
        ExamHeader examDescription = ExamHeader.of("Exam Description");
        ExamHeader examDescription2 = ExamHeader.of("Exam Description");
        ExamHeader examDescription3 = ExamHeader.of("Exam Description 2");

        assertEquals(examDescription, examDescription2);
        assertNotEquals(examDescription, examDescription3);
        assertNotEquals(examDescription, null);
        assertNotEquals(examDescription, new Object());
    }


}
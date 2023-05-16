package org.domain.model;

import org.domain.model.ExamTitle;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ExamTitleTest {

    @Test
    public void testCreateValidExamTitle() {
        String validValue = "Exam Title";

        ExamTitle examTitle = ExamTitle.of(validValue);

        assertNotNull(examTitle);
        assertEquals(validValue, examTitle.value());
    }

    @Test
    public void testCreateInvalidExamTitleWithNullValue() {
        String invalidValue = null;

        assertThrows(NullPointerException.class,
                () -> ExamTitle.of(invalidValue));
    }

    @Test
    public void testCreateInvalidExamTitleWithEmptyValue() {
        String invalidValue = "";

        assertThrows(IllegalArgumentException.class,
                () -> ExamTitle.of(invalidValue));
    }

    @Test
    public void testCreateSmallExamTitle() {
        String invalidValue = "Ex";

        assertThrows(IllegalArgumentException.class,
                () -> ExamTitle.of(invalidValue));
    }

    @Test
    public void testCreateBigExamTitle() {
        String invalidValue = "abcdefghijklmnopqrstuvwxyz";

        assertThrows(IllegalArgumentException.class,
                () -> ExamTitle.of(invalidValue));
    }

    @Test
    void ExamTitleEquals() {
        ExamTitle examTitle = ExamTitle.of("Exam Title");
        ExamTitle examTitle2 = ExamTitle.of("Exam Title");
        ExamTitle examTitle3 = ExamTitle.of("Exam Title 2");

        assertEquals(examTitle, examTitle2);
        assertNotEquals(examTitle, examTitle3);
        assertNotEquals(examTitle, null);
        assertNotEquals(examTitle, new Object());
    }
}
package org.domain.model;

import org.junit.jupiter.api.Test;

import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import static org.junit.jupiter.api.Assertions.*;

class ExamDateTest {

    @Test
    void testStartDate() {
        ExamDate examDate = ExamDate.ofStart("01-01-2024 07:00");
        assertEquals("2024-01-01T07:00", examDate.startDate().toString());
    }

    @Test
    void testEndDate() {
        ExamDate examDate = ExamDate.ofEnd("01-01-2024 10:00");
        assertEquals("2024-01-01T10:00", examDate.endDate().toString());
    }

    @Test
    void testFullTimeExam() {
        ExamDate examDate = ExamDate.of("01-01-2024 10:00", "01-01-2024 11:00");
        assertEquals("2024-01-01T10:00", examDate.startDate().toString());
        assertEquals("2024-01-01T11:00", examDate.endDate().toString());
    }

    @Test
    void testStartDateWithNull() {
        assertThrows(NullPointerException.class, () -> ExamDate.ofStart(null));
    }

    @Test
    void testEndDateWithNull() {
        assertThrows(NullPointerException.class, () -> ExamDate.ofEnd(null));
    }

    @Test
    void testFullTimeExamWithNull() {
        assertThrows(NullPointerException.class, () -> ExamDate.of(null, null));
    }

    @Test
    void testStartDateWithInvalidTime() {
        assertThrows(IllegalArgumentException.class, () -> ExamDate.ofStart("01-01-2020 10:00"));
    }

    @Test
    void testEndDateWithInvalidTime() {
        assertThrows(IllegalArgumentException.class, () -> ExamDate.ofEnd("01-01-2020 19:00"));
    }

    @Test
    void testFullTimeExamWithInvalidTime() {
        assertThrows(IllegalArgumentException.class, () -> ExamDate.of("01-08-2022 10:00", "01-08-2023 10:00"));
    }

    @Test
    void testFullTimeExamWithSameStartAndEndTime() {
        assertThrows(IllegalArgumentException.class, () -> ExamDate.of("01-10-2023 10:00", "01-10-2023 10:00"));
    }

    @Test
    void testFullTimeExamWithStartDateAfterEndDate() {
        assertThrows(IllegalArgumentException.class, () -> ExamDate.of("01-03-2025 10:00", "01-01-2025 12:00"));
    }

    @Test
    void ExamDateEquals() {
        ExamDate examDate1 = ExamDate.of("01-01-2025 10:00", "01-01-2025 11:00");
        ExamDate examDate2 = ExamDate.of("01-01-2025 10:00", "01-01-2025 11:00");
        ExamDate examDate3 = ExamDate.of("01-03-2024 11:00", "01-04-2024 15:00");
        assertEquals(examDate1, examDate2);
        assertNotEquals(examDate1, examDate3);
        assertNotEquals(examDate1, null);
        assertNotEquals(examDate1, new Object());
    }
}
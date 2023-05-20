package org.domain.model;

import eapli.framework.validations.Preconditions;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Embeddable
public class ExamDate {

    @Column
    private final LocalDateTime startDate;

    @Column
    private final LocalDateTime endDate;

    protected ExamDate() {
        startDate = null;
        endDate = null;
    }

    public ExamDate(final LocalDateTime startDate, final LocalDateTime endDate) {

        Preconditions.nonNull(startDate, "Start date cannot be null.");
        Preconditions.nonNull(endDate, "End date cannot be null.");
        Preconditions.ensure(startDate.isBefore(endDate), "Start date must be before end date.");
        Preconditions.ensure(startDate.isAfter(LocalDateTime.now()), "Start date must be after current date.");

        this.startDate = startDate;
        this.endDate = endDate;
    }

    public ExamDate(final LocalDateTime time) {
        Preconditions.nonNull(time, "Time cannot be null.");
        Preconditions.ensure(time.isAfter(LocalDateTime.now()), "Time must be after current date.");

        this.startDate = time;
        this.endDate = time;
    }

    private static boolean isValidFormat(String date) {
        String regex = "^(\\d{2})-(\\d{2})-(\\d{4}) (\\d{2}):(\\d{2})$";
        return date.matches(regex);
    }

    public static ExamDate ofStart(final String startDate) {
        Preconditions.ensure(isValidFormat(startDate), "Invalid date format.");

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");

        LocalDateTime start = LocalDateTime.parse(startDate, formatter);

        return new ExamDate(start);
    }

    public static ExamDate ofEnd(final String endDate) {
        Preconditions.ensure(isValidFormat(endDate), "Invalid date format.");

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");

        LocalDateTime end = LocalDateTime.parse(endDate, formatter);

        return new ExamDate(end);
    }

    public static ExamDate of(final String startDate, final String endDate) {
        Preconditions.ensure(isValidFormat(startDate), "Invalid date format.");
        Preconditions.ensure(isValidFormat(endDate), "Invalid date format.");

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");

        LocalDateTime start = LocalDateTime.parse(startDate, formatter);
        LocalDateTime end = LocalDateTime.parse(endDate, formatter);

        return new ExamDate(start, end);
    }

    public LocalDateTime startDate() {
        return startDate;
    }

    public LocalDateTime endDate() {
        return endDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ExamDate)) return false;
        ExamDate examDate = (ExamDate) o;
        if (!startDate.equals(examDate.startDate)) return false;
        return endDate.equals(examDate.endDate);
    }

    public String valueStartDate() {
        return startDate.toString();
    }

    public String valueEndDate() {
        return endDate.toString();
    }

    @Override
    public String toString() {
        return "start date: " + startDate +
                " || end date: " + endDate;
    }

    public boolean overlaps(final ExamDate other) {
        return this.startDate.isBefore(other.endDate) && this.endDate.isAfter(other.startDate);
    }

}

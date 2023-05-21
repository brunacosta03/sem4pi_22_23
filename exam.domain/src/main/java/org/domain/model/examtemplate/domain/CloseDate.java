package org.domain.model.examtemplate.domain;

import eapli.framework.validations.Preconditions;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Embeddable
public class CloseDate {

    @Column(name = "close_date", nullable = false)
    private LocalDateTime value;

    private CloseDate(String date){
        Preconditions.ensure(isValidFormat(date), "Invalid date format");

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");

        LocalDateTime valueDate = LocalDateTime.parse(date, formatter);

        this.value = valueDate;
    }

    protected CloseDate() {
        // for ORM
    }

    private static boolean isValidFormat(String date) {
        String regex = "^(\\d{2})-(\\d{2})-(\\d{4}) (\\d{2}):(\\d{2})$";
        return date.matches(regex);
    }

    public static CloseDate of(String date){
        return new CloseDate(date);
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        return value.format(formatter);
    }
}

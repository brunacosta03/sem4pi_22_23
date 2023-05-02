package org.springframework.domain;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.time.LocalDate;

@Embeddable
public class AnsweredDate {
    @Column(name = "answered_date")
    private LocalDate value;

    public AnsweredDate() {
        // for ORM
    }

    private AnsweredDate(LocalDate value) {
        this.value = value;
    }

    public static AnsweredDate init() {
        return new AnsweredDate(LocalDate.now());
    }

    public static AnsweredDate init(LocalDate date) {
        return new AnsweredDate(date);
    }

    public LocalDate value() {
        return this.value;
    }

}

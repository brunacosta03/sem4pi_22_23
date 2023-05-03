package org.enrollment.request.domain;

import eapli.framework.validations.Preconditions;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.time.LocalDate;

@Embeddable
public class RequestedDate {
    @Column(name = "requested_date", nullable = false)
    private LocalDate value;

    protected RequestedDate() {
        // for ORM
    }

    private RequestedDate(LocalDate value) {
        Preconditions.ensure(value != null, "A request should have a date it was made");
        this.value = value;
    }

    public static RequestedDate init() {
        return new RequestedDate(LocalDate.now());
    }

    public static RequestedDate init(LocalDate date) {
        return new RequestedDate(date);
    }
}

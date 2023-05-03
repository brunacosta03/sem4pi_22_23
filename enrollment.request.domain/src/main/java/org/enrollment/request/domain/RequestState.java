package org.enrollment.request.domain;

public enum RequestState {
    PENDING,
    ACCEPTED,
    REJECTED;

    String value;

    RequestState() {
        this.value = this.name();
    }

    String value() {
        return this.value;
    }
}

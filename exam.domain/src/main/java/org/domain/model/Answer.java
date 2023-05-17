package org.domain.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class Answer {

    @Column(name = "answer", nullable = false)

    private String value;

    public Answer(String value) {
        this.value = value;
    }

    public Answer() {
        // for ORM
        this.value = null;
    }

    public String value() {
        return value;
    }
}

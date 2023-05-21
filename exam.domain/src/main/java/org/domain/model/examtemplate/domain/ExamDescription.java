package org.domain.model.examtemplate.domain;


import eapli.framework.validations.Preconditions;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class ExamDescription {

    @Column(name = "exam_description", nullable = false)
    private String value;

    private ExamDescription(String value){
        Preconditions.ensure(value.length() >= 10, "Exam description must be at least 10 characters");

        this.value = value;
    }

    protected ExamDescription() {
        // for ORM
    }

    public static ExamDescription of(String value){
        return new ExamDescription(value);
    }
}

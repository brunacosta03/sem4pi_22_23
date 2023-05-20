package org.domain.model;

import eapli.framework.domain.model.ValueObject;
import eapli.framework.validations.Preconditions;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
@EqualsAndHashCode
public class ExamHeader implements ValueObject, Comparable<ExamHeader> {

    /**
     * The description of the exam.
     */
    private final String description;

    /**
     * Constructor for ORM.
     */
    protected ExamHeader() {
        description = "";
    }

    private ExamHeader(final String description) {
        Preconditions.noneNull("Description cannot be null.");
        Preconditions.ensure(!description.isEmpty(), "Description cannot be empty.");
        Preconditions.ensure(description.length() >= 10, "Description cannot be smaller than 10 characters.");
        this.description = description;
    }

    /**
     * Creates a new ExamDescription.
     * @param description - the description of the exam.
     * @return - the new ExamDescription.
     */
    public static ExamHeader of(final String description) {
        return new ExamHeader(description);
    }

    /**
     * Returns the description of the exam.
     * @return - the description of the exam.
     */
    public String toString() {
        return description;
    }

    /**
     * Compares two ExamDescription objects.
     * @param o the object to be compared.
     * @return - the comparison between the two objects.
     */
    @Override
    public int compareTo(ExamHeader o) {
        return description.compareTo(String.valueOf(o));
    }

    /**
     * Value of the description of the exam.
     * @return - the description of the exam.
     */
    public String value() {
        return description;
    }
}

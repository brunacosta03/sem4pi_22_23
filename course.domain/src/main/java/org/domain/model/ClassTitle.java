package org.domain.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import eapli.framework.domain.model.ValueObject;
import eapli.framework.strings.StringMixin;
import eapli.framework.validations.Preconditions;
import lombok.EqualsAndHashCode;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@EqualsAndHashCode
public class ClassTitle implements ValueObject, Serializable, Comparable<ClassTitle>, StringMixin {

    @JsonProperty
    private final String title;

    protected ClassTitle() {
        title = "";
    }

    private ClassTitle(final String title) {
        Preconditions.noneNull("Title cannot be null.");
        Preconditions.ensure(!title.isEmpty(), "Title cannot be empty.");
        this.title = title;
    }

    public static ClassTitle of(final String title) {
        return new ClassTitle(title);
    }

    public String toString() {
        return title;
    }

    @Override
    public int compareTo(final ClassTitle o) {
        return title.compareTo(o.title);
    }

    public String value() {
        return title;
    }
}

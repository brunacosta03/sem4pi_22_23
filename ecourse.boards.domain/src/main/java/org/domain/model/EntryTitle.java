package org.domain.model;

import eapli.framework.domain.model.ValueObject;
import eapli.framework.validations.Preconditions;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * The type Acronym.
 */
@Embeddable
public class EntryTitle implements ValueObject {
    /**
     * Board Entry Title.
     */
    @Column(name = "ENTRY_TITLE")
    private final String value;

    /**
     * The constant MAX_LENGTH.
     */
    private static final Integer MAX_LENGTH = 50;


    /**
     * The constant MIN_LENGTH.
     */
    private static final Integer MIN_LENGTH = 5;

    /**
     * Instantiates a new EntryTitle.
     */
    protected EntryTitle() {
        this.value = null;
    }

    private EntryTitle(final String valuep) {
        Preconditions.nonEmpty(
                valuep,
                "Board Entry Title should neither be null nor empty"
        );
        Preconditions.nonNull(
                valuep,
                "Board Entry Title should neither be null nor empty"
        );
        Preconditions.ensure(
                valuep.length() <= MAX_LENGTH && valuep.length() >= MIN_LENGTH,
                "Board Entry Title should have between "
                        + MIN_LENGTH + " and "
                        + MAX_LENGTH + " characters"
        );

        this.value = valuep;
    }

    /**
     * Factory method to create a EntryTitle instance.
     * @param valuep The value of the board entry title.
     * @return EntryTitle instance.
     */
    public static EntryTitle of(final String valuep) {
        return new EntryTitle(valuep);
    }

    /**
     * Value string.
     * @return the string
     */
    public String value() {
        return value;
    }
}

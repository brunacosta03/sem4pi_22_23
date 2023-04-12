package domain.model;

import eapli.framework.domain.model.ValueObject;
import eapli.framework.validations.Preconditions;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class FullName implements ValueObject, Serializable {
    /**
     * Complete name of entity.
     */
    @Column(name = "FULL_NAME", nullable = false)
    private String value;

    /**
     * Minimum number of characters for a full name.
     */
    private static final int MIN_NUMBER_OF_CHARACTERS = 3;

    /**
     * Constructor for FullName.
     *
     * @param valuep The value of the full name.
     */
    private FullName(final String valuep) {
        this.value = valuep;
    }

    protected FullName() {
        // for ORM
    }

    /**
     * Factory method to create a FullName instance.
     *
     * @param valuep The value of the full name.
     * @return FullName instance.
     */
    public static FullName of(final String valuep) {
        Preconditions.nonEmpty(valuep, "Full Name can't be empty.");
        Preconditions.ensure(valuep.length() > MIN_NUMBER_OF_CHARACTERS,
                    "Full name must have "
                        + MIN_NUMBER_OF_CHARACTERS + " characters or more");
        return new FullName(valuep);
    }

    String value() {
        return this.value;
    }
}

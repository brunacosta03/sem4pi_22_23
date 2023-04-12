package domain.model;


import eapli.framework.domain.model.ValueObject;
import eapli.framework.validations.Preconditions;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.io.Serializable;
import java.time.LocalDate;

/**
 * The type Birth date.
 */
@Embeddable
public final class BirthDate implements ValueObject, Serializable {

    /**
     * Birthdate of user.
     */
    @Column(name = "BIRTH_DATE")
    @Temporal(TemporalType.DATE)
    private LocalDate value;

    private BirthDate(final LocalDate valuep) {

        Preconditions.ensure(
                valuep.isBefore(LocalDate.now()),
                "Birthdate cannot be in the future"
        );

        Preconditions.ensure(
                valuep.isAfter(LocalDate.now().minusYears(120)),
                "Birthdate cannot be more than 120 years ago"
        );

        this.value = valuep;
    }

    /**
     * Factory method for creating a new BirthDate.
     * Of birth date.
     *
     * @param valuep the value parameter
     * @return the birth date
     */
    public static BirthDate of(final LocalDate valuep) {
        return new BirthDate(valuep);
    }

    /**
     * Instantiates a new Birth date.
     */
    protected BirthDate() {
        // for ORM
    }

    LocalDate value() {
        return this.value;
    }
}

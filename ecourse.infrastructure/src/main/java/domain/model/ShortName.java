package domain.model;

import eapli.framework.domain.model.ValueObject;
import eapli.framework.validations.Preconditions;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class ShortName implements ValueObject {
    /**
     * Short name of Entity.
     */
    @Column(name = "shortName")
    private final String value;

    protected ShortName() {
        value = null;
    }

    /**
     * Minimum number of characters for a short name.
     */
    private static final int MIN_NUMBER_OF_CHARACTERS = 3;
    private ShortName(final String valuep) {
        this.value = valuep;
    }

    /**
     * Factory method to create a short name instance.
     *
     * @param valuep The value of the short name.
     * @return ShortName instance.
     */
    public static ShortName valueOf(final String valuep) {
        Preconditions.nonNull(valuep, "Short Name can't be null.");
        Preconditions.nonEmpty(valuep, "Short Name can't be empty.");
        Preconditions.ensure(valuep.length() > MIN_NUMBER_OF_CHARACTERS,
                    "Short name must have "
                        + MIN_NUMBER_OF_CHARACTERS + " characters or more");
        return new ShortName(valuep);
    }

    String value() {
        return this.value;
    }
}

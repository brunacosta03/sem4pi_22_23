package domain.model;

import eapli.framework.domain.model.ValueObject;
import eapli.framework.validations.Preconditions;

import javax.persistence.Version;

public class ShortName implements ValueObject {
    /**
     * Version to resolve conflicts
     */
    @Version
    private Long version;
    /**
     * Short name of Entity.
     */
    private String value;

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
        Preconditions.nonEmpty(valuep, "Short Name can't be empty.");
        Preconditions.ensure(valuep.length() > MIN_NUMBER_OF_CHARACTERS,
                    "Short name must have "
                        + MIN_NUMBER_OF_CHARACTERS + " characters or more");
        return new ShortName(valuep);
    }
}

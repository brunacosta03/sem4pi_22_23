package domain.model;

import eapli.framework.domain.model.ValueObject;
import eapli.framework.validations.Preconditions;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class TaxPayerNumber implements ValueObject {
    /**
     * Short name of Entity.
     */
    @Column(name = "taxPayerNumber")
    private final String value;

    protected TaxPayerNumber() {
        value = null;
    }

    /**
     * Minimum number of characters for a Tax Payer Number.
     */
    private static final int MIN_NUMBER_OF_CHARACTERS = 9;
    private TaxPayerNumber(final String valuep) {
        this.value = valuep;
    }

    /**
     * Factory method to create a short name instance.
     *
     * @param valuep The value of the short name.
     * @return ShortName instance.
     */
    public static TaxPayerNumber valueOf(final String valuep) {
        Preconditions.nonEmpty(valuep, "Tax payer number can't be empty.");
        Preconditions.ensure(valuep.length() >= MIN_NUMBER_OF_CHARACTERS,
                    "Tax payer number must have "
                        + MIN_NUMBER_OF_CHARACTERS + " characters or more");
        return new TaxPayerNumber(valuep);
    }
}

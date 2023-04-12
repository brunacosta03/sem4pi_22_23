package domain.model;

import eapli.framework.domain.model.ValueObject;
import eapli.framework.validations.Preconditions;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class Acronym implements ValueObject {
    /**
     * Short name of Entity.
     */
    @Column(name = "acronym")
    private final String value;

    protected Acronym() {
        value = null;
    }

    private Acronym(final String valuep) {
        this.value = valuep;
    }

    /**
     * Factory method to create a short name instance.
     *
     * @param valuep The value of the short name.
     * @return ShortName instance.
     */
    public static Acronym valueOf(final String valuep) {
        return new Acronym(valuep);
    }
}

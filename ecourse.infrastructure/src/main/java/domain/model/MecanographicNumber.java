package domain.model;

import eapli.framework.domain.model.ValueObject;

import javax.persistence.Embeddable;

@Embeddable
public class MecanographicNumber implements ValueObject, Comparable<MecanographicNumber> {
    private String number;

    protected MecanographicNumber(final String mecanographicNumber) {
        this.number = mecanographicNumber;
    }

    protected MecanographicNumber() {
        // for ORM
    }

    public static MecanographicNumber valueOf(final String mecanographicNumber) {
        return new MecanographicNumber(mecanographicNumber);
    }

    @Override
    public String toString() {
        return this.number;
    }

    @Override
    public int compareTo(final MecanographicNumber arg0) {
        return number.compareTo(arg0.number);
    }
}

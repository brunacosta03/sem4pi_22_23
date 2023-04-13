package domain.model;

import eapli.framework.domain.model.ValueObject;
import eapli.framework.validations.Preconditions;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Transient;
import java.time.LocalDate;
import java.util.Objects;

@Embeddable
public class MecanographicNumber implements ValueObject, Comparable<MecanographicNumber> {
    @Column(name = "number_mec", unique = true)
    private String number;

    @Transient
    private Integer year;
    @Transient
    private Integer value;

    protected MecanographicNumber(final String mecanographicNumber) {
        int year = Integer.parseInt(mecanographicNumber.substring(0, 4));
        int value = Integer.parseInt(mecanographicNumber.substring(4));
        ensureValid(year, value, mecanographicNumber);
        this.number = mecanographicNumber;

    }

    private MecanographicNumber(final Integer year, final Integer value) {
        String number = buildNumber(year, value);
        ensureValid(year, value, number);
        this.year = year;
        this.value = value;
        this.number = number;
    }

    private void ensureValid(Integer year, Integer value, String number){
        Preconditions.ensure(
                year <= LocalDate.now().getYear(),
                "Year cannot be greater than current year"
        );
        Preconditions.ensure(
                value > 0,
                "Value cannot be negative or zero"
        );
        Preconditions.ensure(
                value < 100000,
                "Value cannot be greater than 99999 (have more than 5 digits)"
        );
    }

    private String buildNumber(final Integer year, final Integer value) {
        StringBuilder sb = new StringBuilder();
        int len_val = value.toString().length();

        for(int i = len_val; i < 5; i++) {
            sb.append("0");
        }

        sb.append(value);

        return year.toString() + sb;
    }

    protected MecanographicNumber() {
        // for ORM
    }

    public static MecanographicNumber of(final String mecanographicNumber) {
        return new MecanographicNumber(mecanographicNumber);
    }

    public static MecanographicNumber of(final Integer year, final Integer value) {
        return new MecanographicNumber(year, value);
    }

    public static MecanographicNumber of(final Integer value) {
        return new MecanographicNumber(LocalDate.now().getYear(), value);
    }


    @Override
    public String toString() {
        return this.number;
    }

    @Override
    public int compareTo(final MecanographicNumber arg0) {
        return number.compareTo(arg0.number);
    }

    String value(){
        return this.number;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MecanographicNumber that = (MecanographicNumber) o;

        if (!Objects.equals(number, that.number)) return false;
        if (!Objects.equals(year, that.year)) return false;
        return Objects.equals(value, that.value);
    }

    @Override
    public int hashCode() {
        int result = number != null ? number.hashCode() : 0;
        result = 31 * result + (year != null ? year.hashCode() : 0);
        result = 31 * result + (value != null ? value.hashCode() : 0);
        return result;
    }
}

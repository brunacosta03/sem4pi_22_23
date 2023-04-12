package domain.model;

import eapli.framework.domain.model.ValueObject;
import eapli.framework.validations.Preconditions;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

@Embeddable
public class MecanographicNumber implements ValueObject, Serializable {

    public static final String GENERATE_QUERY = "SELECT COALESCE(MAX(value)+1, 1) FROM USER WHERE YEAR_OF_ENROLLMENT:=" + LocalDateTime.now().getYear() + ";";

    /**
     * Year of the generated number
     */
    @Column(name = "YEAR_OF_ENROLLMENT")
    private Integer year;
    /**
     * Value to be added to the string of the number, generated automatically
     */
    @Column(name = "MECANOGRAPHIC_NUMBER_VALUE")
    private Integer value;

    private MecanographicNumber(final Integer value){
        checkValue(value);
        this.year = LocalDateTime.now().getYear();
        this.value = value;
    }

    private MecanographicNumber(final Integer year, final Integer value){
        Preconditions.ensure(
                year <= LocalDateTime.now().getYear(),
                "Year cannot be greater than current year"
        );

        checkValue(value);

        this.year = year;
        this.value = value;
    }

    private void checkValue(Integer value){
        Preconditions.ensure(value > 0, "Value cannot be negative or zero");
        Preconditions.ensure(
                value < 100000,
                "Value cannot be greater than 99999 (have more than 5 digits)"
        );
    }

    protected MecanographicNumber() {
        // for ORM
    }

    public static MecanographicNumber of(Integer year, Integer value){
        return new MecanographicNumber(year, value);
    }

    public static MecanographicNumber of(Integer value){
        return new MecanographicNumber(value);
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MecanographicNumber that = (MecanographicNumber) o;
        return Objects.equals(year, that.year) && Objects.equals(value, that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(year, value);
    }

    public String value(){
        return year.toString() + buildValueString();
    }

    private String buildValueString(){
        StringBuilder sb = new StringBuilder();
        sb.append(this.value);

        while(sb.length() < 5){
            sb.insert(0, "0");
        }

        return sb.toString();
    }
}

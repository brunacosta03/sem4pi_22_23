package domain.model;

import eapli.framework.domain.model.ValueObject;
import eapli.framework.validations.Preconditions;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Embeddable
public final class BirthDate implements ValueObject {
    /**
     * Date when someone born.
     */
    @Column(name = "birth_date")
    private LocalDate value;

    protected BirthDate() {
        value = null;
    }
    private BirthDate(final LocalDate valuep) {
        this.value = valuep;
    }

    private BirthDate(final String valuep) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate date = LocalDate.parse(valuep, formatter);
        Preconditions.ensure(
                date.isBefore(LocalDate.now()),
                "Birthdate cannot be in the future"
        );
        Preconditions.ensure(
                date.isAfter(LocalDate.now().minusYears(120)),
                "Birthdate cannot be more than 120 years ago"
        );


        this.value = date;
    }


    public static BirthDate of(final String date) {
        return new BirthDate(date);
    }

    public LocalDate value() {
        return this.value;
    }
}

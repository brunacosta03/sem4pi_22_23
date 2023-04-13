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

    /**
     * Max years for birthdate.
     */
    private static final int MAX_YEARS = 120;

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
                date.isAfter(LocalDate.now().minusYears(MAX_YEARS)),
                "Birthdate cannot be more than 120 years ago"
        );


        this.value = date;
    }

    /**
     * Transform string in BirthDate object.
     * @param date birthdate string
     * @return BirthDate
     */
    public static BirthDate of(final String date) {
        return new BirthDate(date);
    }

    /**
     * Get birthdate value.
     * @return LocalDate
     */
    public LocalDate value() {
        return this.value;
    }
}

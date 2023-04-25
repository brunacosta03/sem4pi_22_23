package org.usermanagement.domain.model;

import eapli.framework.domain.model.ValueObject;
import eapli.framework.validations.Preconditions;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * The type Birth date.
 */
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

    /**
     * Instantiates a new Birth date.
     */
    protected BirthDate() {
        value = null;
    }
    private BirthDate(final LocalDate valuep) {
        Preconditions.ensure(
                valuep.isBefore(LocalDate.now()),
                "Birthdate cannot be in the future"
        );
        Preconditions.ensure(
                valuep.isAfter(LocalDate.now().minusYears(MAX_YEARS)),
                "Birthdate cannot be more than 120 years ago"
        );
        this.value = valuep;
    }

    private BirthDate(final String valuep) {
        Preconditions.ensure(
                isValidFormat(valuep),
                "Invalid date format. Expected format: dd/MM/yyyy"
        );

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
     * Regex for validate date format.
     * @param valuep
     * @return true/false
     */
    private boolean isValidFormat(final String valuep) {
        String regex = "^\\d{2}/\\d{2}/\\d{4}$";
        return valuep.matches(regex);
    }

    /**
     * Factory method for birthdate creation.
     *
     * @param date birthdate string
     * @return BirthDate birthdate
     */
    public static BirthDate of(final String date) {
        return new BirthDate(date);
    }


    /**
     * Factory method for birthdate creation.
     *
     * @param date the date
     * @return the birthdate
     */
    public static BirthDate of(final LocalDate date) {
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

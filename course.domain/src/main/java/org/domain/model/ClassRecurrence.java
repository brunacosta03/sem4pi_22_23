package org.domain.model;

import eapli.framework.domain.model.ValueObject;
import eapli.framework.validations.Preconditions;

import javax.persistence.Column;
public class ClassRecurrence implements ValueObject {

    /**
     * The day of the week the class occurs.
     */
    @Column(name = "recurrence")
    private final ClassDayOfWeek dayOfWeek;

    /**
     * Constructor for ORM.
     */
    protected ClassRecurrence() {
        dayOfWeek = null;
    }

    /**
     * Constructor for ClassRecurrence.
     * @param dayOfWeek - the day of the week the class occurs.
     */
    public ClassRecurrence(final ClassDayOfWeek dayOfWeek) {
        Preconditions.noneNull(dayOfWeek);

        validateDayOfWeek(dayOfWeek.toString());

        this.dayOfWeek = dayOfWeek;
    }

    /**
     * Creates a new ClassRecurrence.
     * @param dayOfWeek - the day of the week the class occurs.
     * @return - the new ClassRecurrence.
     */
    public static ClassRecurrence of(final String dayOfWeek) {
        return new ClassRecurrence(ClassDayOfWeek.valueOf(dayOfWeek));
    }

    /**
     * Validates the day of the week.
     * @param dayOfWeek - the day of the week the class occurs.
     */
    public void validateDayOfWeek(final String dayOfWeek) {
        Preconditions.ensure(dayOfWeek.equalsIgnoreCase("MONDAY") ||
                dayOfWeek.equalsIgnoreCase("TUESDAY") ||
                dayOfWeek.equalsIgnoreCase("WEDNESDAY") ||
                dayOfWeek.equalsIgnoreCase("THURSDAY") ||
                dayOfWeek.equalsIgnoreCase("FRIDAY"),
                "Day of week must be a valid day of the week.");
    }

    /**
     * Returns the day of the week the class occurs.
     * @return - the day of the week the class occurs.
     */
    public ClassDayOfWeek dayOfWeek() {
        return dayOfWeek;
    }

    /**
     * Compares two ClassRecurrence objects.
     * @param o the object to be compared.
     * @return - true if the objects are equal, false otherwise.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ClassRecurrence)) return false;

        ClassRecurrence that = (ClassRecurrence) o;

        return dayOfWeek.equals(that.dayOfWeek);
    }

    /**
     * Returns the hash code of the ClassRecurrence.
     * @return - the hash code of the ClassRecurrence.
     */
    @Override
    public String toString() {
        return dayOfWeek.toString();
    }
}

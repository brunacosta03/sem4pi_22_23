package org.domain.model;

import eapli.framework.validations.Preconditions;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "T_CLASS")
public class Class implements Serializable {

    /**
     * The title of the class.
     */
    @EmbeddedId
    @Column (unique = true)
    private final ClassTitle title;

    /**
     * The day of the week the class occurs.
     */
    private final ClassRecurrence recurrence;

    /**
     * The time the class starts.
     */
    @Column (name = "start_time")
    private final ClassTime startTime;

    /**
     * The time the class ends.
     */
    @Column (name = "end_time")
    private final ClassTime endTime;

    /**
     * The course the class is part of.
     */
    @ManyToOne
    @JoinColumn(name = "course_code")
    private Course course;

    /**
     * The duration of the class.
     */
    @Column (name = "duration")
    private final int duration;

    /**
     * Empty constructor for ORM.
     */
    protected Class() {
        title = null;
        recurrence = null;
        startTime = null;
        endTime = null;
        duration = 0;
    }

    /**
     * Creates a new Class.
     * @param courseCode - the course code of the class.
     * @param title - the title of the class.
     * @param recurrence - the day of the week the class occurs.
     * @param startTime - the time the class starts.
     * @param duration - the duration of the class.
     * @param endTime - the time the class ends.
     */
    protected Class(final CourseCode courseCode,
                    final ClassTitle title,
                    final ClassRecurrence recurrence,
                    final ClassTime startTime,
                    int duration,
                    final ClassTime endTime) {

        Preconditions.noneNull(courseCode, title, recurrence, startTime, duration, endTime);
        Preconditions.ensure(startTime.startTime().isBefore(endTime.endTime()), "Start time must be before end time.");

        this.title = title;
        this.recurrence = recurrence;
        this.startTime = startTime;
        this.duration = duration;
        this.endTime = endTime;
    }

    /**
     * Creates a new Class.
     * @param courseCode - the course code of the class.
     * @param title - the title of the class.
     * @param recurrence - the day of the week the class occurs.
     * @param startTime - the time the class starts.
     * @param endTime - the time the class ends.
     */

    public static Class of(final CourseCode courseCode,
                           final ClassTitle title,
                           final ClassRecurrence recurrence,
                           final ClassTime startTime,
                           final int duration,
                           final ClassTime endTime) {
        return new Class(courseCode, title, recurrence, startTime, duration, endTime);
    }

    /**
     * Returns the title of the class.
     * @return - the title of the class.
     */

    public ClassTitle title() {
        return title;
    }

    /**
     * Returns the course the class is part of.
     * @return - the course the class is part of.
     */
    public ClassRecurrence recurrence() {
        return recurrence;
    }

    /**
     * Returns the time the class starts.
     * @return - the time the class starts.
     */
    public ClassTime startTime() {
        return startTime;
    }

    /**
     * Returns the time the class ends.
     * @return - the time the class ends.
     */
    public ClassTime endTime() {
        return endTime;
    }

    /**
     * Returns the course the class is part of.
     * @return - the course the class is part of.
     */
    public Integer duration() {
        return duration;
    }

    /**
     * Returns the course the class is part of.
     * @return - the course the class is part of.
     */
    @Override
    public String toString() {
        return "Course: " + course + "\n" +
                "Title: " + title + "\n" +
                "Recurrence: " + recurrence + "\n" +
                "Start Time: " + startTime + "\n" +
                "End Time: " + endTime + "\n" +
                "Duration: " + duration + "\n";
    }

    /**
     * Compares two classes.
     * @return - true if the classes are equal, false otherwise.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Class)) return false;

        Class aClass = (Class) o;

        if (duration != aClass.duration) return false;
        if (!title.equals(aClass.title)) return false;
        if (!recurrence.equals(aClass.recurrence)) return false;
        if (!startTime.equals(aClass.startTime)) return false;
        return endTime.equals(aClass.endTime);
    }
}

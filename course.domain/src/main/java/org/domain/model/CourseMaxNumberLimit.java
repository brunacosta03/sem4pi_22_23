package org.domain.model;


import eapli.framework.domain.model.ValueObject;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * The type CourseMaxNumberLimit.
 */
@Embeddable
public class CourseMaxNumberLimit implements ValueObject {
    /**
     * Maximum number of students than can be enrolled in a course
     */
    @Column(name = "maximum_students")
    private Integer value;

    /**
     * Instantiates a new Course max number limit.
     */
    protected CourseMaxNumberLimit(){ value = 1000;}

    private CourseMaxNumberLimit(final Integer value){
        // Preconditions ensurrance
        this.value = value;
    }

    /**
     * Factory method for coursemaxnumberlimit creation
     *
     * @param maxValue the maximum number of students
     * @return CourseMaxNumberLimit
     */
    public static CourseMaxNumberLimit of(final Integer  maxValue){ return new CourseMaxNumberLimit(maxValue);}

    /**
     * Get maximum number of students' value.
     * @return Integer
     */
    public Integer value(){return this.value;}
}

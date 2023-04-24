package org.domain.model;

import eapli.framework.domain.model.ValueObject;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * The type CourseState.
 */
@Embeddable
public class CourseState implements ValueObject {
    /**
     * The state of the current course.
     * Can be open, closed, enroll and in progress.
     */
    @Column(name = "state")
    private String value;

    /**
     * Instatiates a new State for the course.
     */
    protected CourseState(){value = "Open";}

    private CourseState(final String value){
        // Precondition ensurrance
        this.value = value;
    }

    /**
     * Factory method for coursestate creation.
     *
     * @param state, the state of the course
     * @return CourseState
     */

    public static CourseState of(final String state){ return new CourseState(state);}

    /**
     * Get the state's value.
     * @return String
     */
    public String value(){ return this.value;}

}


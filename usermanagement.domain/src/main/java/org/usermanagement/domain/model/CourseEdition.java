package org.usermanagement.domain.model;

import eapli.framework.domain.model.ValueObject;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * The type CourseEdition
 */
@Embeddable
public class CourseEdition implements ValueObject {
    /**
     * The edition of a course
     */
    @Column(name = "edition")
    private String value;

    /**
     * Instantiates a new edition for the course.
     */
    protected CourseEdition(){ value = null;}

    private CourseEdition(final String value){
        //Preconditions enssurance
        this.value = value;
    }

    /**
     * Factory method for courseedition creation
     *
     * @param edition the course's edition
     * @return CourseEdition
     */
    public static CourseEdition of(final String edition){ return new CourseEdition(edition);}

    /**
     * Get the edition's value
     * @return String
     */

    public String value(){ return this.value;}

}

package org.domain.model;

import eapli.framework.domain.model.ValueObject;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * The type CourseName.
 */
@Embeddable
public class CourseName implements ValueObject {
    /**
     * Name for the course.
     */
    @Column(name = "name")
    private String value;

    /**
     * Instantiates a new Course's name.
     */
    protected CourseName(){ value = null;}

    private CourseName(final String value){
        this.value = value;
    }

    /**
     * Factory method for coursename creation
     *
     * @param name, the name of the course
     * @return CourseName
     */
    public static CourseName of(final String name){return new CourseName(name);}
}

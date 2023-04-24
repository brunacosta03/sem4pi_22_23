package org.domain.model;


import eapli.framework.domain.model.ValueObject;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * The type CourseCode
 */
@Embeddable
public class CourseCode implements ValueObject, Comparable<CourseCode> {
    /**
     * Unique code for the course
     */
    @Column(name = "code")
    private String value;

    /**
     * Instantiates a new Code for the course.
     */
    protected CourseCode(){ value = null;}

    private CourseCode(final String value){
        //Preconditions enssurance

        this.value = value;
    }

    /**
     * Factory method for the course code.
     * @param code the unique code of students
     * @return CourseCode
     */
    public static CourseCode of(final String code){ return new CourseCode(code);}

    /**
     * Get code value.
     * @return String
     */
    public String value(){return this.value;}

    @Override
    public int compareTo(CourseCode o) {
        if(this.value.equals(o.value)){
            return 1;
        }
        return 0;
    }
}

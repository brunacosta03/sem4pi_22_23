package org.usermanagement.domain.model;



public class Course {
    /**
     * Version of course;
     */
    private Long version;

    /**
     * Name of the course.
     */
    private CourseName name;

    /**
     * Code of the course.
     */
    private CourseCode code;

    /**
     * Edition of the course.
     */
    private CourseEdition edition;

    /**
     * Description of the course.
     */
    private CourseDescription description;

    /**
     * State of the course(Open, closed, enroll or in progress)
     */
    private CourseState state;

    /**
     * Maximum number of people who can enroll in the course.
     */
    private CourseMaxNumberLimit max;

    /**
     * Minimum number of people who have to enroll in the course.
     */
    private CourseMinNumberLimit min;

    protected Course(){
    }

    Course(final CourseName name,
           final CourseCode code,
           final CourseEdition edition,
           final CourseDescription description,
           final CourseState state,
           final CourseMaxNumberLimit max,
           final CourseMinNumberLimit min){

        this.name = name;
        this.code = code;
        this.edition = edition;
        this.description = description;
        this.state = state;
        this.max = max;
        this.min = min;
    }

    private void necessaryParameters(
            final CourseName name,
            final CourseCode code,
            final CourseEdition edition,

    )

}

package org.domain.model;

import org.usermanagement.domain.model.User;

public class CourseFactory {

    public CourseFactory(){

    }
    public Course createCourse(final String name,
                               final String code,
                               final String edition,
                               final String description,
                               final Integer max,
                               final Integer min,
                               final User headTeacher){
        return new Course(CourseName.of(name),
                CourseCode.of(code),
                CourseEdition.of(edition),
                CourseDescription.of(description),
                CourseStateConstants.CLOSED,
                CourseMaxNumberLimit.of(max),
                CourseMinNumberLimit.of(min),
                headTeacher
                );
    }
}

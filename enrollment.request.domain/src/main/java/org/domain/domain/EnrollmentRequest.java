package org.domain.domain;


import eapli.framework.validations.Preconditions;
import org.domain.model.Course;
import org.user.management.CourseRoles;
import org.usermanagement.domain.model.User;

public class EnrollmentRequest {
    private User student;
    private Course course;

    protected EnrollmentRequest() {
        // for ORM
    }

    private EnrollmentRequest(User student, Course course) {
        this.student = student;
        this.course = course;
    }

    public static EnrollmentRequest create(User student, Course course) {
        Preconditions.noneNull(student, course);
        Preconditions.ensure(student.role().equals(CourseRoles.STUDENT.toString()), "Only students can be enrolled");
        return new EnrollmentRequest(student, course);
    }
}

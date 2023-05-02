package org.domain.domain;


import eapli.framework.domain.model.AggregateRoot;
import eapli.framework.validations.Preconditions;
import org.domain.model.Course;
import org.domain.model.CourseStateConstants;
import org.user.management.CourseRoles;
import org.usermanagement.domain.model.User;

import javax.persistence.*;

import java.util.Objects;

/**
 * The type Enrollment request.
 */
@Entity
@Table(
    name = "t_enrollment_request",
    uniqueConstraints = @UniqueConstraint(
            columnNames = {
                    "student_email",
                    "course_code"
            }
    )
)
public class EnrollmentRequest implements AggregateRoot<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "student_email")
    private User student;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "course_code")
    private Course course;

    private RequestedDate requestedDate;
    private AnsweredDate answeredDate;

    @Enumerated(EnumType.STRING)
    private RequestState state;


    /**
     * Instantiates a new Enrollment request.
     */
    protected EnrollmentRequest() {
        // for ORM
    }

    private EnrollmentRequest(User student, Course course) {
        this.student = student;
        this.course = course;
        this.requestedDate = RequestedDate.init();
        this.state = RequestState.PENDING;
        this.answeredDate = null;
    }

    /**
     * Create enrollment request.
     *
     * @param student the student
     * @param course  the course
     * @return the enrollment request
     */
    public static EnrollmentRequest create(User student, Course course) {
        Preconditions.noneNull(student, course);
        Preconditions.ensure(
                student.role().equals(CourseRoles.STUDENT.toString()),
                "Only students can be enrolled"
        );
        Preconditions.ensure(
                course.state().equals(CourseStateConstants.ENROLL),
                "This course is not available for enrollment"
        );
        return new EnrollmentRequest(student, course);
    }

    /**
     * equals method.
     * @param o
     * @return
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        EnrollmentRequest that = (EnrollmentRequest) o;

        if (!Objects.equals(student, that.student)) return false;
        return Objects.equals(course, that.course);
    }

    @Override
    public boolean sameAs(Object other) {
        return false;
    }

    /**
     * Accept request.
     */
    public void accept() {
        Preconditions.ensure(
                !this.state.equals(RequestState.ACCEPTED),
                "Already accepted requests cannot be accepted again"
        );
        this.state = RequestState.ACCEPTED;
        this.answeredDate = AnsweredDate.init();
    }

    /**
     * Reject request.
     */
    public void reject() {
        Preconditions.ensure(
                !this.state.equals(RequestState.REJECTED),
                "Already rejected requests cannot be rejected again"
        );
        this.state = RequestState.REJECTED;
        this.answeredDate = AnsweredDate.init();
    }

    @Override
    public Long identity() {
        return id;
    }
}

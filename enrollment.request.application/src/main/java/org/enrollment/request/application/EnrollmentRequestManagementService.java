package org.enrollment.request.application;

import eapli.framework.domain.repositories.TransactionalContext;
import eapli.framework.general.domain.model.EmailAddress;
import eapli.framework.validations.Preconditions;
import org.domain.model.Course;
import org.domain.model.CourseCode;
import org.domain.repositories.CourseRepository;
import org.enrollment.request.repositories.EnrollmentRequestRepository;
import org.enrollment.request.domain.EnrollmentRequest;
import org.springframework.stereotype.Service;
import org.usermanagement.domain.model.User;
import org.usermanagement.domain.repositories.UserRepository;

@Service
public class EnrollmentRequestManagementService {
    final CourseRepository courseRepo;

    final EnrollmentRequestRepository enrollmentRequestRepo;

    final UserRepository userRepo;

    private final TransactionalContext txt;

    public EnrollmentRequestManagementService(CourseRepository courseRepo, EnrollmentRequestRepository enrollmentRequestRepo, UserRepository userRepo, TransactionalContext txt) {
        // dependency injection
        this.courseRepo = courseRepo;
        this.enrollmentRequestRepo = enrollmentRequestRepo;
        this.userRepo = userRepo;
        this.txt = txt;
    }

    public EnrollmentRequest createRequest(CourseCode courseCode, User student){

        Course course = this.courseRepo
                .findByCode(courseCode)
                .orElseThrow(
                        () -> new IllegalArgumentException(
                                "Course with code " +
                                        courseCode.value() +
                                        " does not exist"
                        )
                );

        Preconditions.ensure(
                this
                        .enrollmentRequestRepo
                        .findByCourseAndStudent(course, student) == null,
                "Student " +
                        student.identity() +
                        " already requested to enroll in " +
                        courseCode.value() +
                        " course."
        ); ;

        EnrollmentRequest request = EnrollmentRequest.create(student, course);

        return this.enrollmentRequestRepo.save(request);
    }

    public EnrollmentRequest acceptRequest(CourseCode courseCode, EmailAddress studentEmail){


        txt.beginTransaction();

        User student = userRepo
                .findUserByEmail(studentEmail)
                .orElseThrow(
                        () ->
                                new IllegalArgumentException(
                                        "User with email " +
                                                studentEmail.toString() +
                                                " not found."
                                )
                );

        Course course = this.courseRepo
                .findByCode(courseCode)
                .orElseThrow(
                        () -> new IllegalArgumentException(
                                "Course with code " +
                                        courseCode.value() +
                                        " does not exist"
                        )
                );

        EnrollmentRequest request = this.enrollmentRequestRepo.findByCourseAndStudent(course, student);

        Preconditions.nonNull(
                request,
                "Student " +
                        studentEmail +
                        " did not request to enroll in " +
                        courseCode.value() +
                        " course."
        );

        request.accept();

        course.addStudent(student);

        txt.commit();

        this.courseRepo.save(course);

        return this.enrollmentRequestRepo.save(request);
    }

    public EnrollmentRequest rejectRequest(CourseCode courseCode, EmailAddress studentEmail){


        txt.beginTransaction();

        User student = userRepo
                .findUserByEmail(studentEmail)
                .orElseThrow(
                        () ->
                                new IllegalArgumentException(
                                        "User with email " +
                                                studentEmail.toString() +
                                                " not found."
                                )
                );

        Course course = this.courseRepo
                .findByCode(courseCode)
                .orElseThrow(
                        () -> new IllegalArgumentException(
                                "Course with code " +
                                        courseCode.value() +
                                        " does not exist"
                        )
                );

        EnrollmentRequest request = this.enrollmentRequestRepo.findByCourseAndStudent(course, student);

        Preconditions.nonNull(
                request,
                "Student " +
                        studentEmail +
                        " did not request to enroll in " +
                        courseCode.value() +
                        " course."
        );

        request.reject();

        txt.commit();

        return this.enrollmentRequestRepo.save(request);
    }

}

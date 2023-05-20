package org.exam.application;

import eapli.framework.validations.Preconditions;
import org.domain.model.Course;
import org.domain.model.CourseCode;
import org.domain.model.ExamBuilder;
import org.domain.model.ExamTemplate;
import org.domain.repositories.CourseRepository;
import org.usermanagement.domain.model.User;
import repositories.ExamRepository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.StreamSupport;

public class ExamManagementService {

    private final ExamRepository examRepo;

    private final CourseRepository courseRepo;

    public ExamManagementService(ExamRepository examRepo, CourseRepository courseRepo) {
        this.examRepo = examRepo;
        this.courseRepo = courseRepo;
    }

    public ExamTemplate createExam(String courseCode,
                                   String examTitle,
                                   String examHeader,
                                   String examStartDate,
                                   String examEndDate,
                                   User teacher) {

        Course course = courseRepo.findByCode(CourseCode.of(courseCode))
                .orElseThrow(() -> new IllegalArgumentException("Course with code " + courseCode + " does not exist"));

        Preconditions.nonNull(course, "Course must not be null");

        ExamTemplate newExam = new ExamBuilder()
                .withCourse(course)
                .withTitle(examTitle)
                .withHeader(examHeader)
                .withStartDate(examStartDate)
                .withEndDate(examEndDate)
                .withTeacher(teacher)
                .withStudents(course.students())
                .build();

        Preconditions.ensure(newExam != null, "Exam must not be null");

        return examRepo.save(newExam);
    }

    public Iterable<ExamTemplate> listCourseExams(String courseCode, User teacher) {

        Course course = courseRepo.findByCode(CourseCode.of(courseCode))
                .orElseThrow(() -> new IllegalArgumentException("Course with code " + courseCode + " does not exist"));

        Preconditions.nonNull(course, "Course must not be null");

        Preconditions.ensure(teacher != null, "Teacher must not be null");

        return examRepo.findByCourse(course);
    }


    public Iterable<ExamTemplate> listFutureExams(User student) {

            List<ExamTemplate> listFutureExams = new ArrayList<>();

            Preconditions.nonNull(student, "Student must not be null");

            for (Course course : courseRepo.findCoursesTakenByStudent(student)) {
                listFutureExams.addAll((Collection<? extends ExamTemplate>) examRepo.findFutureExams(course));
            }

            return listFutureExams;
    }
}

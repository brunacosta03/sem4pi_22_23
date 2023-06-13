package org.exam.application;


import eapli.framework.application.UseCaseController;
import eapli.framework.validations.Preconditions;
import org.domain.model.Course;
import org.domain.model.CourseCode;
import org.domain.model.exam.Exam;
import org.domain.repositories.CourseRepository;
import org.user.management.CourseRoles;
import org.usermanagement.domain.model.User;
import repositories.ExamRepository;
import repositories.ExamTemplateRepository;

import java.lang.reflect.Array;
import java.util.*;

@UseCaseController
public class ListCourseExamGradesController {

    private final CourseRepository courseRepo;

    private final ExamRepository examRepo;

    private final ExamTemplateRepository examTemplateRepo;

    private final ExamService service;

    public ListCourseExamGradesController(final CourseRepository courseRepo,
                                          final ExamRepository examRepo,
                                          final ExamTemplateRepository examTemplateRepo){
        this.courseRepo = courseRepo;
        this.examRepo = examRepo;
        this.examTemplateRepo = examTemplateRepo;
        service = new ExamService(examRepo, examTemplateRepo, courseRepo);
    }

    public Iterable<Course> getTeacherCourses(User teacher){
        Preconditions.ensure(teacher.role().equals(CourseRoles.TEACHER.toString()), "The logged in user must be a teacher");
        return service.getTeacherCourses(teacher);
    }

    public Iterable<Exam> getExams(User teacher,
                                   String courseCode){

        Set<Course> listCourses = new HashSet<>((Collection<? extends Course>) getTeacherCourses(teacher));

        Course course = courseRepo.findByCode(CourseCode.of(courseCode)).get();

        Preconditions.ensure(listCourses.contains(course), "The course was not on the shown list");

        return service.listTeacherGrades(course);
    }
}

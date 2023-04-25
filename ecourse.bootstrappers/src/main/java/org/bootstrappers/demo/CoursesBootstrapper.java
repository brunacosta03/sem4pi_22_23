package org.bootstrappers.demo;

import org.bootstrappers.CoursesBootstrapperBase;
import org.course.controller.CourseManagementService;
import org.domain.model.Course;
import org.domain.repositories.CourseRepository;
import org.persistence.PersistenceContext;

public class CoursesBootstrapper extends CoursesBootstrapperBase {
    public boolean execute(){

        Course c1 = addCourse("Matemática", "MAT-1", "INTRO-MAT-SEM01", "Mathematics from the begining of time",
                100, 10, "teacher@email.com");

        Course c2 = addCourse("Português", "PT-1", "INTRO-PT-01", "Portuguese for every ERASMUS student",
                200, 20, "teacher2@email.com");

        Course c3 = addCourse("Algoritmia e Programação", "APROG-3", "APROG-SEM01", "Basics and fundamentals for java",
                400, 30, "teacher@email.com");

        Course c4 = addCourse("Algoritmia e Programação", "APROG-5", "APROG-SEM03", "Basics and fundamentals for java",
                400, 30, "teacher2@email.com");

        CourseManagementService serv = new CourseManagementService();

        serv.addTeacher("teacher@email.com", "MAT-1");

        serv.addTeacher("teacher2@email.com", "PT-1");

        serv.addTeacher("teacher@email.com", "APROG-5");

        serv.addTeacher("teacher2@email.com", "APROG-5");

        serv.addStudent("student1@email.com", "MAT-1");

        serv.addStudent("student2@email.com", "MAT-1");

        serv.addStudent("student1@email.com", "PT-1");

        serv.addStudent("student2@email.com", "APROG-3");

        return true;
    }
}

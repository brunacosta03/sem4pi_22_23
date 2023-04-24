package org.bootstrappers.demo;

import org.bootstrappers.CoursesBootstrapperBase;

public class CoursesBootstrapper extends CoursesBootstrapperBase {
    public boolean execute(){

        addCourse("Matemática", "MAT-1", "INTRO-MAT-SEM01", "Mathematics from the begining of time",
                100, 10, "teacher@email.com");

        addCourse("Português", "PT-1", "INTRO-PT-01", "Portuguese for every ERASMUS student",
                200, 20, "teacher2@email.com");

        addCourse("Algoritmia e Programação", "APROG-3", "APROG-SEM01", "Basics and fundamentals for java",
                400, 30, "teacher@email.com");

        addCourse("Algoritmia e Programação", "APROG-5", "APROG-SEM03", "Basics and fundamentals for java",
                400, 30, "teacher2@email.com");

        return true;
    }
}

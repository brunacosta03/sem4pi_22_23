package org.bootstrappers.demo;

import org.bootstrappers.ExamTemplatesBootstrapperBase;

import org.usermanagement.domain.model.User;

public class ExamTemplatesBootstrapper extends ExamTemplatesBootstrapperBase {

    public boolean execute() {

        User teacher1 = findUserByEmail("teacher@email.com");
        User teacher2 = findUserByEmail("teacher2@email.com");

        createExam("antlr_text_files/exams/examtemplate1.txt", "MAT-1", teacher1);
        createExam("antlr_text_files/exams/examtemplate2.txt", "PT-1", teacher2);
        createExam("antlr_text_files/exams/examtemplate3.txt", "MAT-1", teacher1);

        return true;
        }
    }

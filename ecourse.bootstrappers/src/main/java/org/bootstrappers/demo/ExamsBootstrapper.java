package org.bootstrappers.demo;

import eapli.framework.general.domain.model.EmailAddress;
import org.bootstrappers.ExamsBootstrapperBase;
import org.domain.model.examtemplate.domain.ExamTitle;

public class ExamsBootstrapper extends ExamsBootstrapperBase {
    public boolean execute(){
        EmailAddress email = EmailAddress.valueOf("student1@email.com");

        addExam(ExamTitle.of("Mathematics"),email, null, Double.valueOf(10));
        addExam(ExamTitle.of("Portuguese"),email, null, Double.valueOf(12.3));
        addExam(ExamTitle.of("Math 101 Final Exam"),email, null, Double.valueOf(14.5));

        return true;
    }
}

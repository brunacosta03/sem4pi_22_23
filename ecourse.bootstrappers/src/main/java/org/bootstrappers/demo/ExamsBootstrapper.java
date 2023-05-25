package org.bootstrappers.demo;

import eapli.framework.general.domain.model.EmailAddress;
import org.bootstrappers.ExamsBootstrapperBase;

public class ExamsBootstrapper extends ExamsBootstrapperBase {
    public boolean execute(){
        EmailAddress email = EmailAddress.valueOf("student1@email.com");

        addExam(null,email, null, Double.valueOf(10));
        addExam(null,email, null, Double.valueOf(12.3));
        addExam(null,email, null, Double.valueOf(14.5));
        addExam(null,email, null, Double.valueOf(16.7));

        return true;
    }
}

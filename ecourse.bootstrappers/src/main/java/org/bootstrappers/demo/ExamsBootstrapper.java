package org.bootstrappers.demo;

import org.bootstrappers.ExamsBootstrapperBase;
import org.usermanagement.domain.model.User;

public class ExamsBootstrapper extends ExamsBootstrapperBase {

    public boolean execute() {

        User teacher1 = findUserByEmail("teacher@email.com");
        User teacher2 = findUserByEmail("teacher2@email.com");


        createExam("MAT-1", "Exame de Matemática", "Exame de Matemática", "01-01-2024 09:00", "01-01-2024 11:00", teacher1);

        createExam("APROG-5", "Exame de Algoritmia", "Exame de Algoritmia e Programação", "01-03-2024 09:00", "01-03-2024 11:00", teacher1);

        createExam("APROG-5", "Exame de Programação", "Exame de Algoritmia e Programação", "01-05-2024 09:00", "01-05-2024 11:00", teacher2);

        return true;
    }
}

package org.bootstrappers;


import eapli.framework.actions.Action;
import org.usermanagement.domain.model.User;

public class EnrollmentRequestsBootstrapper
        extends EnrollmentRequestsBootstrapperBase
        implements Action {
    /**
     * Executes enrollment requests bootstrapping.
     * @return success or failure
     */
    @Override
    public boolean execute() {
        User student3 = findUserByEmail("student3@email.com");

        User student4 = findUserByEmail("student4@email.com");

        createRequest(
                "MAT-1",
                student3
        );

        createRequest(
                "APROG-3",
                student3
        );

        createRequest(
                "PT-1",
                student4
        );

        createRequest(
                "APROG-3",
                student4
        );

        return true;
    }
}

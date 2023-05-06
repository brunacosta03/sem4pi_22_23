package org.bootstrappers;

import eapli.framework.domain.repositories.ConcurrencyException;
import eapli.framework.domain.repositories.IntegrityViolationException;
import eapli.framework.general.domain.model.EmailAddress;
import org.domain.model.CourseCode;
import org.enrollment.request.application.EnrollmentRequestManagementService;
import org.enrollment.request.domain.EnrollmentRequest;
import org.persistence.PersistenceContext;
import org.usermanagement.domain.model.User;
import org.usermanagement.domain.repositories.UserRepository;

public class EnrollmentRequestsBootstrapperBase {
    UserRepository userRepo = PersistenceContext.repositories().users();
    EnrollmentRequestManagementService service = new EnrollmentRequestManagementService(
            PersistenceContext.repositories().courses(),
            PersistenceContext.repositories().enrollmentRequests()
    );


    protected User findUserByEmail(String email) {
        return userRepo.findUserByEmail(
                EmailAddress.valueOf(email)
        ).get();
    }

    protected EnrollmentRequest createRequest(String courseCode, User student) {

        EnrollmentRequest request = null;

        try{

             request = service.createRequest(
                    CourseCode.of(courseCode),
                    student
             );

            System.out.println(
                    "[+] EnrollmentRequest from "
                            + student.emailAddress()
                            + " to " + courseCode
            );

        } catch (final IntegrityViolationException | ConcurrencyException e) {
            System.out.println(
                    "Already exist --> "
                            + "[ " + student.emailAddress()
                            + " with " + courseCode + " ]"
            );
        }
        return request;
    }
}

package org.springframework.application;

import eapli.framework.application.UseCaseController;
import eapli.framework.general.domain.model.EmailAddress;
import org.domain.model.CourseCode;
import org.persistence.PersistenceContext;
import org.springframework.domain.EnrollmentRequest;



@UseCaseController
public class AnswerEnrollmentRequestController {

    private final EnrollmentRequestManagementService service;

    public AnswerEnrollmentRequestController() {
        this.service = new EnrollmentRequestManagementService(
                PersistenceContext.repositories().courses(),
                PersistenceContext.repositories().enrollmentRequests(),
                PersistenceContext.repositories().users(),
                PersistenceContext.repositories().newTransactionalContext()
        );
    }

    public EnrollmentRequest acceptEnrollmentRequest(String courseCode, String emailAddress){
        return this.service.acceptRequest(CourseCode.of(courseCode), EmailAddress.valueOf(emailAddress));
    }

    public EnrollmentRequest rejectEnrollmentRequest(String courseCode, String emailAddress){
        return this.service.rejectRequest(CourseCode.of(courseCode), EmailAddress.valueOf(emailAddress));
    }
}

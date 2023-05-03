package org.enrollment.request.application;

import eapli.framework.application.UseCaseController;
import org.authz.application.AuthorizationService;
import org.domain.model.CourseCode;
import org.persistence.PersistenceContext;
import org.enrollment.request.domain.EnrollmentRequest;
import org.user.management.CourseRoles;

@UseCaseController
public class RequestEnrollmentController {

    private final EnrollmentRequestManagementService service;
    private final AuthorizationService authzService;

    public RequestEnrollmentController(AuthorizationService authzService) {
        this.authzService = authzService;
        this.service = new EnrollmentRequestManagementService(
                PersistenceContext.repositories().courses(),
                PersistenceContext.repositories().enrollmentRequests(),
                PersistenceContext.repositories().users(),
                PersistenceContext.repositories().newTransactionalContext()
        );
    }


    public EnrollmentRequest createRequest(String courseCodeString){
        authzService.ensureAuthenticatedUserHasAnyOf(CourseRoles.STUDENT);
        return this.service.createRequest(
                CourseCode.of(courseCodeString),
                authzService
                        .session()
                        .orElseThrow(
                                () -> new IllegalArgumentException("There is no user Logged.")
                        )
                        .authenticatedUser()
        );
    }
}

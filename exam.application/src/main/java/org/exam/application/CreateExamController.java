package org.exam.application;

import eapli.framework.application.UseCaseController;
import org.authz.application.AuthorizationService;
import eapli.framework.validations.Preconditions;
import org.domain.model.ExamTemplate;
import org.persistence.PersistenceContext;
import org.user.management.CourseRoles;
import org.usermanagement.domain.model.User;
import org.usermanagement.domain.model.UserSession;

@UseCaseController
public class CreateExamController {

    private final ExamManagementService service;

    private final AuthorizationService authz;

    public CreateExamController() {
        this.authz = null;
        this.service = null;
    }

    public CreateExamController(
            final AuthorizationService authzServicep
    ) {
        this.authz = authzServicep;
        this.service = new ExamManagementService(
                PersistenceContext.repositories().exams(),
                PersistenceContext.repositories().courses()
        );
    }


    public ExamTemplate createExam(String courseCode,
                                   String examTitle,
                                   String examHeader,
                                   String examStartDate,
                                   String examEndDate) {
        authz.ensureAuthenticatedUserHasAnyOf(CourseRoles.TEACHER);

        Preconditions.ensure(courseCode != null, "Course code must not be null");

        UserSession session = authz.session().orElse(null);

        assert session != null;
        User teacher = session.authenticatedUser();

        return service.createExam(courseCode, examTitle, examHeader, examStartDate, examEndDate, teacher);
    }


}

package org.exam.application;

import org.authz.application.AuthorizationService;
import org.domain.model.ExamTemplate;
import org.persistence.PersistenceContext;
import org.user.management.CourseRoles;
import org.usermanagement.domain.model.User;
import org.usermanagement.domain.model.UserSession;

import java.time.temporal.Temporal;
import java.util.Set;

public class ListFutureExamsController {

    private final ExamManagementService service;

    private final AuthorizationService authz;

    public ListFutureExamsController(
            final AuthorizationService authzServicep
    ) {
        this.authz = authzServicep;
        this.service = new ExamManagementService(
                PersistenceContext.repositories().exams(),
                PersistenceContext.repositories().courses()
        );
    }

    public Iterable<ExamTemplate> listFutureExams() {

        authz.ensureAuthenticatedUserHasAnyOf(CourseRoles.STUDENT);

        UserSession session = authz.session().orElse(null);

        assert session != null;
        User student = session.authenticatedUser();

        return service.listFutureExams(student);

    }
}

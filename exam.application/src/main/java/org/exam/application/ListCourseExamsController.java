package org.exam.application;

import eapli.framework.application.UseCaseController;
import eapli.framework.validations.Preconditions;
import org.authz.application.AuthorizationService;
import org.domain.model.ExamTemplate;
import org.persistence.PersistenceContext;
import org.user.management.CourseRoles;

@UseCaseController
public class ListCourseExamsController {

    private final ExamManagementService service;

    private final AuthorizationService authz;

    public ListCourseExamsController(
            final AuthorizationService authzServicep
    ) {
        this.authz = authzServicep;
        this.service = new ExamManagementService(
                PersistenceContext.repositories().exams(),
                PersistenceContext.repositories().courses()
        );
    }

    public Iterable<ExamTemplate> listCourseExams(String courseCode) {
        authz.ensureAuthenticatedUserHasAnyOf(CourseRoles.TEACHER);

        Preconditions.ensure(courseCode != null, "Course code must not be null");

        return service.listCourseExams(courseCode);
    }
}

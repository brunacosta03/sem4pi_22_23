package org.course.controller;

import eapli.framework.application.UseCaseController;
import eapli.framework.general.domain.model.EmailAddress;
import org.authz.application.AuthorizationService;
import org.authz.application.AuthzRegistry;
import org.domain.model.*;
import org.domain.repositories.CourseRepository;
import org.persistence.PersistenceContext;
import org.user.management.CourseRoles;
import org.usermanagement.domain.model.User;
import org.usermanagement.domain.repositories.UserRepository;

@UseCaseController
public class AddCourseController {

    private final AuthorizationService authz = AuthzRegistry.authorizationService();
    private final CourseRepository repo = PersistenceContext.repositories().courses();

    private final UserRepository userRepo = PersistenceContext.repositories().users();

    private final CourseFactory factory = new CourseFactory();
    public Course addCourse(final String name,
                            final String code,
                            final String edition,
                            final String description,
                            final Integer max,
                            final Integer min,
                            final String headTeacher){

        authz.ensureAuthenticatedUserHasAnyOf(CourseRoles.MANAGER);

        User teacher = userRepo.findByEmail(EmailAddress.valueOf(headTeacher))
                .orElse(null);

        final Course course = factory.createCourse(name, code, edition,
                description, max, min, teacher);

        return repo.save(course);
    }
}

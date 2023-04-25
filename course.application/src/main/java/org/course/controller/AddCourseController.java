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

/**
 * The type Add course controller.
 */
@UseCaseController
public class AddCourseController {

    /**
     * The authorization service for managing user authorization.
     */
    private final AuthorizationService authz =
            AuthzRegistry.authorizationService();
    /**
     * The repository for managing course entities.
     */
    private final CourseRepository repo =
            PersistenceContext.repositories().courses();

    /**
     * The repository for managing user entities.
     */
    private final UserRepository userRepo =
            PersistenceContext.repositories().users();

    /**
     * The factory for creating course entities.
     */
    private final CourseFactory factory = new CourseFactory();

    /**
     * Add course course.
     *
     * @param name        the name
     * @param code        the code
     * @param edition     the edition
     * @param description the description
     * @param max         the max
     * @param min         the min
     * @param headTeacher the head teacher
     * @return the course
     */
    public Course addCourse(final String name,
                            final String code,
                            final String edition,
                            final String description,
                            final Integer max,
                            final Integer min,
                            final String headTeacher) {

        authz.ensureAuthenticatedUserHasAnyOf(CourseRoles.MANAGER);

        User teacher = userRepo.findUserByEmail(
                EmailAddress.valueOf(headTeacher)
                )
                .orElse(null);

        final Course course = factory.createCourse(name, code, edition,
                description, max, min, teacher);

        return repo.save(course);
    }
}

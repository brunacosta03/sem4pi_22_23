package org.course.controller;

import eapli.framework.domain.repositories.TransactionalContext;
import eapli.framework.general.domain.model.EmailAddress;
import eapli.framework.validations.Preconditions;
import org.authz.application.AuthorizationService;
import org.authz.application.AuthzRegistry;
import org.domain.model.Course;
import org.domain.model.CourseCode;
import org.domain.repositories.CourseRepository;
import org.persistence.PersistenceContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.user.management.CourseRoles;
import org.usermanagement.domain.model.User;
import org.usermanagement.domain.repositories.UserRepository;

@Service
public class CourseManagementService{

    private final UserRepository userRepo = PersistenceContext.repositories().users();
    private final CourseRepository courseRepo = PersistenceContext.repositories().courses();

    private final TransactionalContext txt = PersistenceContext.repositories().newTransactionalContext();

    private final AuthorizationService authz = AuthzRegistry.authorizationService();
    public CourseManagementService() {

    }

    public Course addStudent(String emailStudent, String c){
        txt.beginTransaction();

        authz.ensureAuthenticatedUserHasAnyOf(CourseRoles.MANAGER);

        User student = userRepo.findUserByEmail(EmailAddress.valueOf(emailStudent))
                .orElse(null);

        Course course = courseRepo.findByCode(CourseCode.of(c)).
                orElse(null);

        Preconditions.nonNull(course, "Course with code " + c + " does not exist");

        course.addStudent(student);

        txt.commit();

        return courseRepo.save(course);
    }

    public Course addTeacher(String emailTeacher, String c){
        txt.beginTransaction();

        authz.ensureAuthenticatedUserHasAnyOf(CourseRoles.MANAGER);

        User teacher = userRepo.findUserByEmail(EmailAddress.valueOf(emailTeacher))
                .orElse(null);

        Course course = courseRepo.findByCode(CourseCode.of(c)).
                orElse(null);

        Preconditions.nonNull(course, "Course with code " + c + " does not exist");

        course.addTeacher(teacher);

        txt.commit();

        return courseRepo.save(course);
    }
}

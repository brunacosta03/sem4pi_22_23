package org.course.controller;

import eapli.framework.domain.repositories.TransactionalContext;
import eapli.framework.general.domain.model.EmailAddress;
import eapli.framework.validations.Preconditions;
import org.authz.application.AuthorizationService;
import org.authz.application.AuthzRegistry;
import org.domain.model.*;
import org.domain.model.Class;
import org.domain.repositories.CourseRepository;
import org.persistence.PersistenceContext;
import org.springframework.stereotype.Service;
import org.user.management.CourseRoles;
import org.usermanagement.domain.model.User;
import org.usermanagement.domain.repositories.UserRepository;

@Service
public class CourseManagementService{

    private final UserRepository userRepo = PersistenceContext.repositories().users();
    private final CourseRepository courseRepo;

    private final TransactionalContext txt = PersistenceContext.repositories().newTransactionalContext();

    private final AuthorizationService authz = AuthzRegistry.authorizationService();

    private final ClassFactory classFactory = new ClassFactory();

    public CourseManagementService() {
        courseRepo = PersistenceContext.repositories().courses();
    }
    public CourseManagementService(final CourseRepository repo) {
        courseRepo = repo;
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

   public Course changeState(Course c){
        txt.beginTransaction();
        CourseState state = c.state();

        if(state.equals(CourseStateConstants.CLOSED)) {

            c.changeState(CourseStateConstants.OPEN);

        } else if(state.equals(CourseStateConstants.OPEN)) {

            c.changeState(CourseStateConstants.ENROLL);

        } else if(state.equals(CourseStateConstants.ENROLL)) {

            c.changeState(CourseStateConstants.IN_PROGRESS);

        } else {
            c.changeState(CourseStateConstants.CLOSED);
        }

        txt.commit();

        return courseRepo.save(c);
    }


    public Course changeState(CourseCode courseCode, CourseState state) {
        txt.beginTransaction();

        Course course = courseRepo.findByCode(courseCode).orElse(null);
        Preconditions.ensure(
                course != null,
                "Course with code " + courseCode + " does not exist"
        );

    	course.changeState(state);

        txt.commit();

    	return courseRepo.save(course);
    }

    public Course scheduleNewClass(String courseCode,
                                   String classTitle,
                                   String classDayOfWeek,
                                   String classStartTime,
                                   String classEndTime,
                                   User teacher) {
        Course course = courseRepo.findByCode(CourseCode.of(courseCode)).
                orElse(null);
        Preconditions.nonNull(course, "Course with code " + courseCode + " does not exist");

        Preconditions.ensure(
                courseRepo.findCoursesThatITeach(teacher) != null,
                "You can not add a class to course " + courseCode + " because you are not a teacher there"
        );

        Class newClass = classFactory.createClass(classTitle, classDayOfWeek, classStartTime, classEndTime, teacher, course.students());

        course.addClass(newClass);

        return courseRepo.save(course);
    }
}

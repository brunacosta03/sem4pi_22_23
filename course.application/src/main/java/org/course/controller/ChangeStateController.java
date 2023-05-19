package org.course.controller;


import eapli.framework.application.UseCaseController;
import eapli.framework.domain.repositories.TransactionalContext;
import org.authz.application.AuthorizationService;
import org.domain.model.Course;
import org.domain.model.CourseCode;
import org.domain.model.CourseState;
import org.domain.model.CourseStateConstants;
import org.domain.repositories.CourseRepository;
import org.persistence.PersistenceContext;
import org.usermanagement.domain.repositories.UserRepository;

@UseCaseController
public class ChangeStateController {


    /**
     * The authorization service for managing user authorization.
     */
    private final AuthorizationService authz;
    /**
     * The repository for managing course entities.
     */
    private final CourseRepository repo;

    /**
     * The repository for managing user entities.
     */
    private final UserRepository userRepo;

    /**
     * The service for creating course entities.
     */
    private final CourseManagementService service;

    public ChangeStateController(UserRepository userRepo,
                                 CourseRepository repo,
                                 AuthorizationService authz){
        this.authz = authz;
        this.userRepo = userRepo;
        this.repo = repo;
        service = new CourseManagementService(userRepo, repo, PersistenceContext.repositories().newTransactionalContext(), authz);
    }

    public Course verifyCourse(String course){
        return repo.findByCode(CourseCode.of(course)).get();
    }

    public boolean confirmForEnrollment(Course course){
        if(course.state().toString().equals(String.valueOf(CourseStateConstants.OPEN))||
                course.state().toString().equals(String.valueOf(CourseStateConstants.ENROLL))){
            return true;
        }else{
            throw new IllegalArgumentException("This course is in the state " + course.state().toString() + ", so it can't be changed");
        }
    }
    public boolean confirmforOpenClose(Course course){
        if(course.state().toString().equals(String.valueOf(CourseStateConstants.CLOSED))||
                course.state().equals(String.valueOf(CourseStateConstants.IN_PROGRESS))){
            return true;
        }else{
            throw new IllegalArgumentException("This course is in the state " + course.state().toString() + ", so it can't be changed");
        }
    }

    public Course changeState(Course course){
        return service.changeState(course);
    }
}

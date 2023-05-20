package org.course.controller;

import eapli.framework.general.domain.model.EmailAddress;
import eapli.framework.infrastructure.authz.domain.model.PlainTextEncoder;
import org.authz.application.AuthorizationService;
import org.domain.model.*;
import org.domain.repositories.CourseRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.persistence.PersistenceContext;
import org.user.management.CourseRoles;
import org.usermanagement.domain.model.ECoursePasswordPolicy;
import org.usermanagement.domain.model.User;
import org.usermanagement.domain.model.UserBuilder;
import org.usermanagement.domain.repositories.UserRepository;

import java.util.Calendar;
import java.util.HashSet;
import java.util.Optional;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.junit.Assert.*;

public class ChangeStateControllerTest {
    /*
    @Mock
    CourseRepository repo;

    @Mock
    UserRepository userRepo;

    UserBuilder builder;

    @InjectMocks
    ChangeStateController ctrl;

    User user;

    @BeforeEach
    void setUp() {
        user = mock(User.class);

        userRepo = mock(UserRepository.class);

        repo = mock(CourseRepository.class);

        builder = new UserBuilder(new ECoursePasswordPolicy(),
                new PlainTextEncoder());

        ctrl = new ChangeStateController(userRepo, repo, new AuthorizationService());
    }
    @Test
    void confirmForEnrollmentClosedTest(){
        builder.with("Pedro","Password1","Pedro Alves","pedro@email.com", "23/05/2002", CourseRoles.TEACHER,"333333333")
                .createdOn(Calendar.getInstance())
                .withAcronym("PPA");

        final User user = builder.build();

        userRepo.save(user);

        when(userRepo.findUserByEmail(EmailAddress.valueOf("pedro@email.com"))).thenReturn(Optional.of(user));

        Course c1 = new Course(CourseName.of("Matemática"),
                CourseCode.of("MAT-1"),
                CourseEdition.of("INTRO-MAT-SEM01"),
                CourseDescription.of("Mathematics"),
                CourseState.of(CourseStateConstants.CLOSED.toString()),
                CourseMaxNumberLimit.of(100),
                CourseMinNumberLimit.of(10),
                user, new HashSet<>(), new HashSet<>());

        assertThrows(IllegalArgumentException.class,()->ctrl.confirmForEnrollment(c1));
    }

    @Test
    void confirmForEnrollmentInProgressTest(){
        builder.with("Pedro","Password1","Pedro Alves","pedro@email.com", "23/05/2002", CourseRoles.TEACHER,"333333333")
                .createdOn(Calendar.getInstance())
                .withAcronym("PPA");

        final User user = builder.build();

        userRepo.save(user);

        when(userRepo.findUserByEmail(EmailAddress.valueOf("pedro@email.com"))).thenReturn(Optional.of(user));

        Course c1 = new Course(CourseName.of("Matemática"),
                CourseCode.of("MAT-1"),
                CourseEdition.of("INTRO-MAT-SEM01"),
                CourseDescription.of("Mathematics"),
                CourseState.of(CourseStateConstants.IN_PROGRESS.toString()),
                CourseMaxNumberLimit.of(100),
                CourseMinNumberLimit.of(10),
                user, new HashSet<>(), new HashSet<>());

        assertThrows(IllegalArgumentException.class,()->ctrl.confirmForEnrollment(c1));
    }
    @Test
    void confirmForOpenCloseOpenedTest(){
        builder.with("Pedro","Password1","Pedro Alves","pedro@email.com", "23/05/2002", CourseRoles.TEACHER,"333333333")
                .createdOn(Calendar.getInstance())
                .withAcronym("PPA");

        final User user = builder.build();

        userRepo.save(user);

        when(userRepo.findUserByEmail(EmailAddress.valueOf("pedro@email.com"))).thenReturn(Optional.of(user));

        Course c1 = new Course(CourseName.of("Matemática"),
                CourseCode.of("MAT-1"),
                CourseEdition.of("INTRO-MAT-SEM01"),
                CourseDescription.of("Mathematics"),
                CourseState.of(CourseStateConstants.OPEN.toString()),
                CourseMaxNumberLimit.of(100),
                CourseMinNumberLimit.of(10),
                user, new HashSet<>(), new HashSet<>());

        assertThrows(IllegalArgumentException.class,()->ctrl.confirmForOpenClose(c1));
    }
    @Test
    void confirmForOpenCloseEnrollmentTest(){
        builder.with("Pedro","Password1","Pedro Alves","pedro@email.com", "23/05/2002", CourseRoles.TEACHER,"333333333")
                .createdOn(Calendar.getInstance())
                .withAcronym("PPA");

        final User user = builder.build();

        userRepo.save(user);

        when(userRepo.findUserByEmail(EmailAddress.valueOf("pedro@email.com"))).thenReturn(Optional.of(user));

        Course c1 = new Course(CourseName.of("Matemática"),
                CourseCode.of("MAT-1"),
                CourseEdition.of("INTRO-MAT-SEM01"),
                CourseDescription.of("Mathematics"),
                CourseState.of(CourseStateConstants.ENROLL.toString()),
                CourseMaxNumberLimit.of(100),
                CourseMinNumberLimit.of(10),
                user, new HashSet<>(), new HashSet<>());

        assertThrows(IllegalArgumentException.class,()->ctrl.confirmForOpenClose(c1));
    }
     */
}

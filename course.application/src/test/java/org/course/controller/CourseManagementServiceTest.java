package org.course.controller;

import eapli.framework.general.domain.model.EmailAddress;
import eapli.framework.infrastructure.authz.domain.model.PlainTextEncoder;
import org.authz.application.AuthorizationService;
import org.authz.application.AuthzRegistry;
import org.domain.model.*;
import org.domain.repositories.CourseRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.persistence.PersistenceContext;
import org.user.management.CourseRoles;
import org.usermanagement.domain.model.ECoursePasswordPolicy;
import org.usermanagement.domain.model.User;
import org.usermanagement.domain.model.UserBuilder;
import org.usermanagement.domain.repositories.UserRepository;

import java.util.Calendar;
import java.util.HashSet;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class CourseManagementServiceTest {

    @Mock
    CourseRepository repo;

    @Mock
    UserRepository userRepo;

    UserBuilder builder;

    @InjectMocks
    CourseManagementService srvc;

    User user;

    @BeforeEach
    void setUp() {
        user = mock(User.class);

        userRepo = mock(UserRepository.class);

        repo = mock(CourseRepository.class);

        builder = new UserBuilder(new ECoursePasswordPolicy(),
                new PlainTextEncoder());

        srvc = new CourseManagementService(userRepo, repo, PersistenceContext.repositories().newTransactionalContext(), new AuthorizationService());
    }

    @Test
    void createCourseTest(){

        builder.with("Pedro","Password1","Pedro Alves","pedro@email.com", "23/05/2002", CourseRoles.TEACHER,"333333333")
                .createdOn(Calendar.getInstance())
                .withAcronym("PPA");

        final User user = builder.build();

        userRepo.save(user);

        when(userRepo.findUserByEmail(EmailAddress.valueOf("pedro@email.com"))).thenReturn(Optional.of(user));

        builder.with("João", "Password2", "João Santiago", "joao@email.com", "25/06/1999", CourseRoles.TEACHER, "111111111")
                .createdOn(Calendar.getInstance())
                .withAcronym("JPA");

        ArgumentCaptor<Course> courseCaptor = ArgumentCaptor.forClass(Course.class);

        srvc.createCourse("Matemática", "MAT-1", "INTRO-MAT-SEM01", "Mathematics", 100, 10, user);

        verify(repo).save(courseCaptor.capture());
    }
}

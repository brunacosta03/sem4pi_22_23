package org.exam.application;

import eapli.framework.infrastructure.authz.domain.model.PlainTextEncoder;
import org.domain.model.*;
import org.domain.repositories.CourseRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.user.management.CourseRoles;
import org.usermanagement.domain.model.ECoursePasswordPolicy;
import org.usermanagement.domain.model.User;
import org.usermanagement.domain.model.UserBuilder;
import org.usermanagement.domain.repositories.UserRepository;
import repositories.ExamRepository;

import java.util.Calendar;
import java.util.HashSet;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ExamManagementServiceTest {

    @Mock
    CourseRepository courseRepo;

    @Mock
    ExamRepository examRepo;

    @Mock
    UserRepository userRepo;

    ExamBuilder builder;

    UserBuilder userBuilder;

    @Mock
    ExamManagementService srvc;

    ExamTemplate exam;

    User user;

    @BeforeEach
    void setUp() {
        exam = mock(ExamTemplate.class);

        user = mock(User.class);

        examRepo = mock(ExamRepository.class);

        courseRepo = mock(CourseRepository.class);

        userRepo = mock(UserRepository.class);

        userBuilder = new UserBuilder(new ECoursePasswordPolicy(),
                new PlainTextEncoder());

        builder = new ExamBuilder();

        srvc = new ExamManagementService(examRepo, courseRepo);
    }

    @Test
    void createExam() {

        userBuilder.with("Bruna", "Password1", "Bruna Costa", "bruna@email.com", "30/10/2003", CourseRoles.TEACHER, "333333333")
                .createdOn(Calendar.getInstance())
                .withAcronym("PPA");

        final User teacher = userBuilder.build();

        userRepo.save(teacher);

        Course c1 = new Course(CourseName.of("Matemática"),
                CourseCode.of("MAT-1"),
                CourseEdition.of("INTRO-MAT-SEM01"),
                CourseDescription.of("Mathematics"),
                CourseStateConstants.ENROLL,
                CourseMaxNumberLimit.of(100),
                CourseMinNumberLimit.of(10),
                teacher, new HashSet<>(), new HashSet<>());

        when(courseRepo.findByCode(any())).thenReturn(Optional.of(c1));
        when(examRepo.save(any())).thenReturn(any(ExamTemplate.class));


        srvc.createExam("MAT-1", "Title", "Matemática 1 Exame", "01-01-2024 09:00", "01-01-2024 11:00", teacher);

        verify(examRepo, times(1)).save(any(ExamTemplate.class));

    }

}
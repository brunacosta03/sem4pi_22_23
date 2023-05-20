package org.domain.model;

import eapli.framework.infrastructure.authz.domain.model.PlainTextEncoder;
import org.domain.repositories.CourseRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.user.management.CourseRoles;
import org.usermanagement.domain.model.ECoursePasswordPolicy;
import org.usermanagement.domain.model.User;
import org.usermanagement.domain.model.UserBuilder;

import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.*;

class ExamTemplateTest {

    private final String examTitle = "Exam Title";

    private final String examHeader = "Exam Header for the exam";

    private final String examStartDate = "01-08-2023 10:00";

    private final String examEndDate = "01-08-2023 11:00";

    private User teacher;

    private Course course;

    private static final String STRING_SHORTNAME = "shortName";
    private static final String STRING_FULLNAME = "fullName";
    private static final String STRING_PASSWORD = "Correct5";
    private static final String STRING_EMAIL = "email@email.com";
    private static final String MEC_NUMBER = "202300001";
    private static final String STRING_ACRONYM = "TTT";
    private static final String STRING_BIRTHDATE = "16/11/2002";

    private static final String STRING_TAXPAYERNUMBER = "999999999";

    ECoursePasswordPolicy passwordPolicy = new ECoursePasswordPolicy();


    @BeforeEach
    void setup(){
        teacher = buildTeacher();
        course = buildCourse();
    }

    private User buildTeacher() {
        UserBuilder userBuilder = new UserBuilder(passwordPolicy, new PlainTextEncoder());

        return userBuilder.with(STRING_SHORTNAME,
                        STRING_PASSWORD,
                        STRING_FULLNAME,
                        STRING_EMAIL,
                        CourseRoles.TEACHER)
                .withAcronym(STRING_ACRONYM)
                .build();
    }

    private Course buildCourse() {
        CourseFactory courseFactory = new CourseFactory();

        return courseFactory.createCourse("Matemática", "MAT-1", "INTRO-MAT-SEM01", "Mathematics from the begining of time",
                100, 10, buildTeacher());
    }

    @Test
    public void testCreateValidExamTemplate() {

        ExamBuilder examBuilder = new ExamBuilder();

        ExamTemplate examTemplate = examBuilder.withTitle(examTitle)
                .withHeader(examHeader)
                .withStartDate(examStartDate)
                .withEndDate(examEndDate)
                .withTeacher(teacher)
                .withStudents(new HashSet<>())
                .withCourse(course)
                .build();

        assertEquals(examTitle, examTemplate.title().toString());
        assertEquals(examHeader, examTemplate.header().toString());

        String formatterStartDate = "2023-08-01T10:00";
        String formatterEndDate = "2023-08-01T11:00";

        assertEquals(formatterStartDate, examTemplate.formattedStartDate());
        assertEquals(formatterEndDate, examTemplate.formattedEndDate());
    }

    @Test
    public void testCreateExamTemplateWithNullTitle() {

            ExamBuilder examBuilder = new ExamBuilder();

            assertThrows(IllegalArgumentException.class, () -> {
                ExamTemplate examTemplate = examBuilder.withTitle(null)
                        .withHeader(examHeader)
                        .withStartDate(examStartDate)
                        .withEndDate(examEndDate)
                        .withTeacher(teacher)
                        .withStudents(new HashSet<>())
                        .withCourse(course)
                        .build();
            });
    }

    @Test
    public void testCreateExamTemplateWithNullHeader() {

            ExamBuilder examBuilder = new ExamBuilder();

            assertThrows(IllegalArgumentException.class, () -> {
                ExamTemplate examTemplate = examBuilder.withTitle(examTitle)
                        .withHeader(null)
                        .withStartDate(examStartDate)
                        .withEndDate(examEndDate)
                        .withTeacher(teacher)
                        .withStudents(new HashSet<>())
                        .build();
            });
    }

    @Test
    public void testCreateExamTemplateWithNullStartDate() {

            ExamBuilder examBuilder = new ExamBuilder();

            assertThrows(IllegalArgumentException.class, () -> {
                ExamTemplate examTemplate = examBuilder.withTitle(examTitle)
                        .withHeader(examHeader)
                        .withStartDate(null)
                        .withEndDate(examEndDate)
                        .withTeacher(teacher)
                        .withStudents(new HashSet<>())
                        .build();
            });
    }

    @Test
    public void testCreateExamTemplateWithNullEndDate() {

            ExamBuilder examBuilder = new ExamBuilder();

            assertThrows(IllegalArgumentException.class, () -> {
                ExamTemplate examTemplate = examBuilder.withTitle(examTitle)
                        .withHeader(examHeader)
                        .withStartDate(examStartDate)
                        .withEndDate(null)
                        .withTeacher(teacher)
                        .withStudents(new HashSet<>())
                        .build();
            });
    }

    @Test
    public void testCreateExamTemplateWithNullTeacher() {

            ExamBuilder examBuilder = new ExamBuilder();

            assertThrows(IllegalArgumentException.class, () -> {
                ExamTemplate examTemplate = examBuilder.withTitle(examTitle)
                        .withHeader(examHeader)
                        .withStartDate(examStartDate)
                        .withEndDate(examEndDate)
                        .withTeacher(null)
                        .build();
            });
    }

    @Test
    public void testCreateExamTemplateWithInvalidStartDate() {

                ExamBuilder examBuilder = new ExamBuilder();

                assertThrows(IllegalArgumentException.class, () -> {
                    ExamTemplate examTemplate = examBuilder.withTitle(examTitle)
                            .withHeader(examHeader)
                            .withStartDate("01-01-2023 10:00")
                            .withEndDate(examEndDate)
                            .withTeacher(teacher)
                            .build();
                });
    }

    @Test
    public void testCreateExamTemplateWithInvalidEndDate() {

                ExamBuilder examBuilder = new ExamBuilder();

                assertThrows(IllegalArgumentException.class, () -> {
                    ExamTemplate examTemplate = examBuilder.withTitle(examTitle)
                            .withHeader(examHeader)
                            .withStartDate(examStartDate)
                            .withEndDate("01-01-2023 11:00")
                            .withTeacher(teacher)
                            .build();
                });
    }

    @Test
    public void testCreateExamTemplateWithSameStartAndEndTime() {

                    ExamBuilder examBuilder = new ExamBuilder();

                    assertThrows(IllegalArgumentException.class, () -> {
                        ExamTemplate examTemplate = examBuilder.withTitle(examTitle)
                                .withHeader(examHeader)
                                .withStartDate(examStartDate)
                                .withEndDate(examStartDate)
                                .withTeacher(teacher)
                                .build();
                    });
    }

    @Test
    public void testCompareTwoEqualExams() {

        ExamBuilder examBuilder = new ExamBuilder();

        ExamTemplate examTemplate1 = examBuilder.withTitle(examTitle)
                                    .withHeader(examHeader)
                                    .withStartDate(examStartDate)
                                    .withEndDate(examEndDate)
                                    .withTeacher(teacher)
                                    .withStudents(new HashSet<>())
                                    .withCourse(course)
                                    .build();

        ExamTemplate examTemplate2 = examBuilder.withTitle(examTitle)
                                    .withHeader(examHeader)
                                    .withStartDate(examStartDate)
                                    .withEndDate(examEndDate)
                                    .withTeacher(teacher)
                                    .withStudents(new HashSet<>())
                                    .withCourse(course)
                                    .build();
        assertEquals(examTemplate1, examTemplate2);
    }

    @Test
    public void testCompareTwoDifferentExamTitle() {

        ExamBuilder examBuilder = new ExamBuilder();

        ExamTemplate examTemplate1 = examBuilder.withTitle(examTitle)
                                    .withHeader(examHeader)
                                    .withStartDate(examStartDate)
                                    .withEndDate(examEndDate)
                                    .withTeacher(teacher)
                                    .withStudents(new HashSet<>())
                                    .withCourse(course)
                                    .build();


        ExamTemplate examTemplate2 = examBuilder.withTitle("Different Title")
                                    .withHeader(examHeader)
                                    .withStartDate(examStartDate)
                                    .withEndDate(examEndDate)
                                    .withTeacher(teacher)
                                    .withCourse(course)
                                    .build();
        assertNotEquals(examTemplate1, examTemplate2);
    }

    @Test
    public void testCompareTwoDifferentExamHeader() {

        ExamBuilder examBuilder = new ExamBuilder();

        ExamTemplate examTemplate1 = examBuilder.withTitle(examTitle)
                                    .withHeader(examHeader)
                                    .withStartDate(examStartDate)
                                    .withEndDate(examEndDate)
                                    .withTeacher(teacher)
                                    .withStudents(new HashSet<>())
                                    .withCourse(course)
                                    .build();

        ExamTemplate examTemplate2 = examBuilder.withTitle(examTitle)
                                    .withHeader("Different Header")
                                    .withStartDate(examStartDate)
                                    .withEndDate(examEndDate)
                                    .withTeacher(teacher)
                                    .withStudents(new HashSet<>())
                                    .withCourse(course)
                                    .build();
        assertNotEquals(examTemplate1, examTemplate2);
    }

    @Test
    public void testCompareTwoDifferentExamStartDate() {

        ExamBuilder examBuilder = new ExamBuilder();

        ExamTemplate examTemplate1 = examBuilder.withTitle(examTitle)
                                    .withHeader(examHeader)
                                    .withStartDate(examStartDate)
                                    .withEndDate(examEndDate)
                                    .withTeacher(teacher)
                                    .withStudents(new HashSet<>())
                                    .withCourse(course)
                                    .build();

        ExamTemplate examTemplate2 = examBuilder.withTitle(examTitle)
                                    .withHeader(examHeader)
                                    .withStartDate("20-07-2023 09:00")
                                    .withEndDate(examEndDate)
                                    .withTeacher(teacher)
                                    .withStudents(new HashSet<>())
                                    .withCourse(course)
                                    .build();
        assertNotEquals(examTemplate1, examTemplate2);
    }

    @Test
    public void testCompareTwoDifferentExamEndDate() {

        ExamBuilder examBuilder = new ExamBuilder();

        ExamTemplate examTemplate1 = examBuilder.withTitle(examTitle)
                                    .withHeader(examHeader)
                                    .withStartDate(examStartDate)
                                    .withEndDate(examEndDate)
                                    .withTeacher(teacher)
                                    .withStudents(new HashSet<>())
                                    .withCourse(course)
                                    .build();

        ExamTemplate examTemplate2 = examBuilder.withTitle(examTitle)
                                    .withHeader(examHeader)
                                    .withStartDate(examStartDate)
                                    .withEndDate("01-06-2024 09:00")
                                    .withTeacher(teacher)
                                    .withStudents(new HashSet<>())
                                    .withCourse(course)
                                    .build();
        assertNotEquals(examTemplate1, examTemplate2);
    }

    @Test
    public void testCompareTwoDifferentExams() {

        ExamBuilder examBuilder = new ExamBuilder();


        ExamTemplate examTemplate1 = examBuilder.withTitle(examTitle)
                                    .withHeader(examHeader)
                                    .withStartDate(examStartDate)
                                    .withEndDate(examEndDate)
                                    .withTeacher(teacher)
                                    .withStudents(new HashSet<>())
                                    .withCourse(course)
                                    .build();

        assertNotEquals(examTemplate1, new Object());

    }

}
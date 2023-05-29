package org.exam.application;

import eapli.framework.infrastructure.authz.domain.model.PlainTextEncoder;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import org.domain.model.Course;
import org.domain.model.CourseFactory;
import org.domain.model.CourseStateConstants;
import org.domain.model.exam.Exam;
import org.domain.model.examtemplate.ExamTemplateBuilder;
import org.domain.model.examtemplate.ExamTemplateErrorListener;
import org.domain.model.examtemplate.ExamTemplateLexer;
import org.domain.model.examtemplate.ExamTemplateParser;
import org.domain.model.examtemplate.domain.ExamTemplate;
import org.domain.model.examtemplate.domain.ExamTitle;
import org.domain.repositories.CourseRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.user.management.CourseRoles;
import org.usermanagement.domain.model.ECoursePasswordPolicy;
import org.usermanagement.domain.model.User;
import org.usermanagement.domain.model.UserBuilder;
import repositories.ExamRepository;
import repositories.ExamTemplateRepository;

import java.io.IOException;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.mockito.Mockito.*;

class ExamServiceTest {
    @Mock ExamRepository repo;
    @Mock ExamTemplateRepository templateRepo;
    @Mock CourseRepository courseRepo;
    private static final String STRING_SHORTNAME = "shortName";
    private static final String STRING_FULLNAME = "fullName";
    private static final String STRING_PASSWORD = "Correct5";
    private static final String STRING_EMAIL = "email@email.com";
    private static final String MEC_NUMBER = "202300001";
    private static final String STRING_ACRONYM = "TTT";
    private static final String STRING_BIRTHDATE = "16/11/2002";

    private static final String STRING_TAXPAYERNUMBER = "999999999";

    private static final String FILE_EXAM_TEMPLATE_1 = "src/test/resources/examtemplate1.txt";
    private static final String FILE_EXAM_RESOLUTION_1 = "src/test/resources/resolution_1_from_1.txt";

    ECoursePasswordPolicy passwordPolicy = new ECoursePasswordPolicy();

    ExamTitle title;

    ExamTemplate template;

    ExamService service;

    User student;

    @BeforeEach
    void setUp() throws IOException {
        repo = mock(ExamRepository.class);
        templateRepo = mock(ExamTemplateRepository.class);
        courseRepo = mock(CourseRepository.class);

        title = ExamTitle.of("Mathematics");
        template = buildTemplate();
        student = buildStudent();

        service = new ExamService(repo, templateRepo, courseRepo);
    }

    @Test
    void testEvaluateExamFromFile() throws IOException {
        when(templateRepo.findByTitle(title)).thenReturn(Optional.of(template));

        service.evaluateExamFromFile(FILE_EXAM_RESOLUTION_1, student, title);

        verify(templateRepo, times(1)).findByTitle(title);
        verify(repo, times(1)).save(any());
    }

    User buildStudent(){
        UserBuilder userBuilder = new UserBuilder(passwordPolicy, new PlainTextEncoder());

        return userBuilder.with(STRING_SHORTNAME,
                        STRING_PASSWORD,
                        STRING_FULLNAME,
                        STRING_EMAIL,
                        CourseRoles.STUDENT)
                .withMecanographicNumber(MEC_NUMBER)
                .build();
    }

    ExamTemplate buildTemplate() throws IOException {
        ExamTemplateLexer lexer = new ExamTemplateLexer(CharStreams.fromFileName(FILE_EXAM_TEMPLATE_1));
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        ExamTemplateParser parser = new ExamTemplateParser(tokens);

        parser.addErrorListener(new ExamTemplateErrorListener());

        ParseTree tree = parser.start();
        ParseTreeWalker walker = new ParseTreeWalker();
        ExamTemplateBuilder builder = new ExamTemplateBuilder();

        builder.with(createValidCourse(), buildTeacher(), new HashSet<>(Set.of(buildStudent())));

        walker.walk(builder, tree);

        return builder.build();
    }

    Course createValidCourse(){
        CourseFactory factory = new CourseFactory();
        Course course = factory.createCourse(
                "Test Course",
                "TC",
                "Test Course Edition",
                "TCE",
                100,
                10,
                buildTeacher()
        );
        course.changeState(CourseStateConstants.ENROLL);
        course.addStudent(buildStudent());
        return course;
    }

    User buildTeacher(){

        UserBuilder userBuilder = new UserBuilder(passwordPolicy, new PlainTextEncoder());

        return userBuilder.with(STRING_SHORTNAME,
                        STRING_PASSWORD,
                        STRING_FULLNAME,
                        STRING_EMAIL,
                        CourseRoles.TEACHER)
                .withAcronym(STRING_ACRONYM)
                .build();
    }
}
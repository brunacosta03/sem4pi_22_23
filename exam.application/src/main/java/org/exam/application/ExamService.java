package org.exam.application;

import eapli.framework.validations.Preconditions;
import org.domain.model.exam.Exam;
import org.domain.model.examtemplate.domain.ExamTemplate;
import org.domain.model.examtemplate.domain.ExamTitle;
import org.domain.repositories.CourseRepository;
import org.springframework.stereotype.Service;
import org.usermanagement.domain.model.User;
import repositories.ExamRepository;
import repositories.ExamTemplateRepository;

import java.io.IOException;

@Service
public class ExamService {
    private ExamRepository repo;
    private ExamTemplateRepository templateRepo;
    private CourseRepository courseRepo;

    public ExamService(ExamRepository repo, ExamTemplateRepository templateRepo, CourseRepository courseRepo) {
        this.repo = repo;
        this.templateRepo = templateRepo;
        this.courseRepo = courseRepo;
    }

    public Exam evaluateExamFromFile(String filePath, User student, ExamTitle examTitle) throws IOException {

        ExamTemplate examTemplate = templateRepo.findByTitle(examTitle)
                .orElseThrow(
                        () -> new IllegalArgumentException("Exam with such title does not exist.\nTry again.")
                );

        Preconditions.ensure(
                examTemplate.containsStudent(student),
                "Student is not enrolled in this exam.\nTry again."
        );


        Exam evaluated = ExamEvaluator.evaluateFromFile(filePath, student, examTemplate);

        return this.repo.save(evaluated);
    }

}

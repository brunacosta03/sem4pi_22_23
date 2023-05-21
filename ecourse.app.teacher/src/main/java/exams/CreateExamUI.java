package exams;

import eapli.framework.presentation.console.AbstractUI;
import org.domain.model.examtemplate.domain.ExamTemplate;
import org.exam.application.CreateExamController;
import org.authz.application.AuthzRegistry;

import java.util.Scanner;

public class CreateExamUI extends AbstractUI {

    Scanner scanner = new Scanner(System.in);

    private final CreateExamController ctrl = new CreateExamController(AuthzRegistry.authorizationService());

    @Override
    protected boolean doShow() {
        try {
            System.out.println("For which course do you want to create an exam?");

            String courseCode = scanner.nextLine();

            System.out.println("What is the exam file?");

            String filePath = scanner.nextLine();

            ExamTemplate exam = ctrl.createExam(courseCode, examTitle, examHeader, startDate, endDate);

            System.out.println("Exam created successfully!");


        } catch (Exception e) {
            System.out.println("Error creating exam: " + e.getMessage());
        }
        return true;
    }

    @Override
    public String headline() {
        return "Create Exam UI";
    }
}

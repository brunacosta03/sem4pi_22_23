package exams;

import eapli.framework.presentation.console.AbstractUI;
import org.domain.model.ExamTemplate;
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

            System.out.println("What is the title of the exam?");

            String examTitle = scanner.nextLine();

            System.out.println("What is the header of the exam?");

            String examHeader = scanner.nextLine();

            System.out.println("When does the exam start? (dd-MM-yyyy HH:mm)");

            String startDate = scanner.nextLine();

            System.out.println("When does the exam end? (dd-MM-yyyy HH:mm)");

            String endDate = scanner.nextLine();

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

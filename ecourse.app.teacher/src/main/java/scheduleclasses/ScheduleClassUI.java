package scheduleclasses;

import eapli.framework.presentation.console.AbstractUI;
import org.authz.application.AuthzRegistry;
import org.course.controller.ScheduleClassController;
import org.domain.model.CourseCode;

import java.util.Scanner;

public class ScheduleClassUI extends AbstractUI {

    private final Scanner scanner = new Scanner(System.in);

    private final ScheduleClassController controller = new ScheduleClassController(AuthzRegistry.authorizationService());

    @Override
    protected boolean doShow() {
        try {

            System.out.println("Which course do you want to schedule a class for?");
            String courseCode = scanner.nextLine();
            System.out.println();

            System.out.println("Which class title do you want to schedule?");
            String classTitle = scanner.nextLine();
            System.out.println();

            System.out.println("Which day of the week do you want to schedule the class for?");
            String dayOfWeek = scanner.nextLine();
            System.out.println();

            System.out.println("What time do you want to schedule the class for?");
            String startTime = scanner.nextLine();
            System.out.println();

            System.out.println("What time do you want the class to end?");
            String endTime = scanner.nextLine();
            System.out.println();

            controller.createClass(courseCode, classTitle, dayOfWeek, startTime, endTime);

            System.out.println("Class scheduled successfully.");

        } catch (IllegalArgumentException iae) {
            System.out.println(iae.getMessage());
        }
        return true;
    }

    @Override
    public String headline() {
        return "Schedule Class UI";
    }
}
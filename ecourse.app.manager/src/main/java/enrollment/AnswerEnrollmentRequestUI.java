package enrollment;

import eapli.framework.presentation.console.AbstractUI;
import org.springframework.application.AnswerEnrollmentRequestController;

import java.util.Scanner;

public class AnswerEnrollmentRequestUI extends AbstractUI {

    private final Scanner sc = new Scanner(System.in);
    private final AnswerEnrollmentRequestController ctrl = new AnswerEnrollmentRequestController();
    @Override
    protected boolean doShow() {

        try{
            System.out.print("Course code of enrollment: ");
            String courseCode = sc.nextLine();
            System.out.println();

            System.out.print("Email address of User: ");
            String emailAddress = sc.nextLine();
            System.out.println();

            System.out.println("Accept or decline? (1/2)");
            Integer opt = Integer.parseInt(sc.nextLine());

            if(opt.equals(1)){
                ctrl.acceptEnrollmentRequest(courseCode, emailAddress);
                System.out.println("Enrollment Request accepted with success!\n");
            }else if(opt.equals(2)){
                ctrl.rejectEnrollmentRequest(courseCode, emailAddress);
                System.out.println("Enrollment Request rejected with success!\n");
            }else {
                throw new IllegalArgumentException("Invalid option.\nTry again.\n");
            }

        } catch (NumberFormatException nfe){
            System.out.println("Invalid option.\nTry again.\n");
        } catch (IllegalArgumentException iae){
            System.out.println(iae.getMessage() + "\n");
        }


        return true;
    }

    @Override
    public String headline() {
        return "Answer Request";
    }
}

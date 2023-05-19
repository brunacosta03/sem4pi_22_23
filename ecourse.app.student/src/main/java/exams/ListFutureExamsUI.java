package exams;

import eapli.framework.presentation.console.AbstractUI;
import org.exam.application.ListFutureExamsController;
import org.authz.application.AuthzRegistry;
import org.domain.model.ExamTemplate;
public class ListFutureExamsUI extends AbstractUI {

    private final ListFutureExamsController ctrl = new ListFutureExamsController(AuthzRegistry.authorizationService());


    @Override
    protected boolean doShow() {

        Iterable<ExamTemplate> exams = ctrl.listFutureExams();

        System.out.println("Future exams found successfully.");

        return true;
    }

    @Override
    public String headline() {
        return "Find Future Exams";
    }
}

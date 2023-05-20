package org.bootstrappers;

import eapli.framework.general.domain.model.EmailAddress;
import org.authz.application.AuthzRegistry;
import org.domain.model.ExamTemplate;
import org.exam.application.CreateExamController;
import org.exam.application.ExamManagementService;
import org.persistence.PersistenceContext;
import org.usermanagement.domain.model.User;
import org.usermanagement.domain.repositories.UserRepository;

public class ExamsBootstrapperBase {

    private final ExamManagementService service = new ExamManagementService(
            PersistenceContext.repositories().exams(),
            PersistenceContext.repositories().courses());

    private final UserRepository userRepo = PersistenceContext.repositories().users();
    public ExamsBootstrapperBase() {
        super();
    }

    protected User findUserByEmail(final String email) {
        return userRepo.findUserByEmail(
                EmailAddress.valueOf(email)
        ).get();
    }

    /**
     * Register exam for bootstrap.
     *
     * @param courseCode course code
     * @param title exam title
     * @param header exam header
     * @param startDate exam start date
     * @param endDate exam end date
     * @param teacher exam teacher
     * @return the exam template
     */

    protected ExamTemplate createExam(final String courseCode,
                                      final String title,
                                      final String header,
                                      final String startDate,
                                      final String endDate,
                                      User teacher) {

        ExamTemplate exam = null;

        try {
            exam = service.createExam(courseCode, title, header, startDate, endDate, teacher);
            System.out.println("[+] " + title);
        } catch (final IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
        return exam;
    }
}
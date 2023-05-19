package repositories;

import eapli.framework.domain.repositories.DomainRepository;
import org.domain.model.Course;
import org.domain.model.ExamTemplate;
import org.domain.model.ExamTitle;
import org.usermanagement.domain.model.User;

import java.util.Optional;

public interface ExamRepository extends DomainRepository<ExamTitle, ExamTemplate> {

    ExamTemplate save(ExamTemplate exam);

    Optional<ExamTemplate> ofIdentity(ExamTitle id);

    /**
     * Get Exam with Title.
     * @param title - the title of the exam.
     * @return - the exam with the title.
     */
    default Optional<ExamTemplate> findByTitle(
            final ExamTitle title) {
        return ofIdentity(title);
    }

    Iterable<ExamTemplate> findAll();

    Iterable<ExamTemplate> findExamsThatIHadCreated(User teacher);


    Iterable<ExamTemplate> findByCourse(Course course);
}

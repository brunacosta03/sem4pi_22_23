package repositories;

import eapli.framework.domain.repositories.DomainRepository;
import eapli.framework.general.domain.model.EmailAddress;
import org.domain.model.exam.Exam;
import org.usermanagement.domain.model.User;

import java.util.Optional;

public interface ExamRepository extends DomainRepository<Long, Exam> {
    Exam save(Exam exam);

    Optional<Exam> ofIdentity(Long id);

    Iterable<Exam> findAll();

    Iterable<Exam> findGradesByStudentEmail(User student);
}

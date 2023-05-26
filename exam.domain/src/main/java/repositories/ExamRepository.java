package repositories;

import eapli.framework.domain.repositories.DomainRepository;
import org.domain.model.exam.Exam;

import java.util.Optional;

public interface ExamRepository extends DomainRepository<Long, Exam> {
    Exam save(Exam exam);

    Optional<Exam> ofIdentity(Long id);

    Iterable<Exam> findAll();
}

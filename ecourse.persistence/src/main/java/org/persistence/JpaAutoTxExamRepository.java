package org.persistence;

import eapli.framework.domain.repositories.TransactionalContext;
import eapli.framework.infrastructure.repositories.impl.jpa.JpaAutoTxRepository;
import org.domain.model.Course;
import org.domain.model.examtemplate.domain.ExamTemplate;
import org.domain.model.examtemplate.domain.ExamTitle;
import org.usermanagement.domain.model.User;
import repositories.ExamRepository;

import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.Map;
import java.util.Optional;

public class JpaAutoTxExamRepository
        extends JpaAutoTxRepository<ExamTemplate, Long, Long>
        implements ExamRepository {

    public JpaAutoTxExamRepository(final String persistenceUnitName,
                                   final String identityFieldName) {
        super(persistenceUnitName, identityFieldName);
    }

    public JpaAutoTxExamRepository(String persistenceUnitName,
                                   Map properties,
                                   String identityFieldName) {
        super(persistenceUnitName, properties, identityFieldName);
    }

    public JpaAutoTxExamRepository(TransactionalContext tx,
                                   String identityFieldName) {
        super(tx, identityFieldName);
    }

    public JpaAutoTxExamRepository(String persistenceUnitName,
                                   Map properties) {
        super(persistenceUnitName, properties, "exam_title");
    }

    @Override
    public ExamTemplate save(ExamTemplate exam) {
        return this.repo.save(exam);
    }

    @Override
    public Iterable<ExamTemplate> findAll() {
        return this.repo.findAll();
    }

    @Override
    public Optional<ExamTemplate> ofIdentity(ExamTitle id) {
        return Optional.empty();
    }

    @Override
    public void delete(ExamTemplate entity) {

    }

    @Override
    public void deleteOfIdentity(ExamTitle entityId) {

    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public Iterable<ExamTemplate> findExamsThatIHadCreated(User teacher) {
        return null;
    }

    @Override
    public Iterable<ExamTemplate> findByCourse(Course course) {
        TypedQuery<ExamTemplate> query = createQuery(
                "SELECT e FROM ExamTemplate e WHERE e.course = :course",
                ExamTemplate.class
        );

        query.setParameter("course", course);

        try {
            return query.getResultList();
        } catch (NoResultException nre) {
            return new ArrayList<>();
        }
    }

    @Override
    public Iterable<ExamTemplate> findFutureExams(Course course) {

        TypedQuery<ExamTemplate> query = createQuery(
                "SELECT e FROM ExamTemplate e WHERE e.course = :course AND e.closeDate.value > CURRENT_DATE",
                ExamTemplate.class
        );

        query.setParameter("course", course);

        try {
            return query.getResultList();
        } catch (NoResultException nre) {
            return new ArrayList<>();
        }
    }

}

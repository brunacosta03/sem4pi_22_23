package org.persistence;

import eapli.framework.domain.repositories.TransactionalContext;
import eapli.framework.infrastructure.repositories.impl.jpa.JpaAutoTxRepository;
import org.domain.model.Course;
import org.springframework.domain.EnrollmentRequest;
import org.springframework.repositories.EnrollmentRequestRepository;
import org.usermanagement.domain.model.User;

import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import java.util.Map;
import java.util.Optional;

public class JpaAutoTxEnrollmentRequestRepository
        extends JpaAutoTxRepository<EnrollmentRequest, Long, Long>
        implements EnrollmentRequestRepository {


    public JpaAutoTxEnrollmentRequestRepository(String persistenceUnitName, String identityFieldName) {
        super(persistenceUnitName, identityFieldName);
    }

    public JpaAutoTxEnrollmentRequestRepository(String persistenceUnitName, Map properties, String identityFieldName) {
        super(persistenceUnitName, properties, identityFieldName);
    }

    public JpaAutoTxEnrollmentRequestRepository(TransactionalContext tx, String identityFieldName) {
        super(tx, identityFieldName);
    }

    public JpaAutoTxEnrollmentRequestRepository(String persistenceUnitName, Map properties) {
        super(persistenceUnitName, properties, "id");
    }

    @Override
    public EnrollmentRequest findByCourseAndStudent(Course course, User student) {
        TypedQuery<EnrollmentRequest> query = createQuery(
                "SELECT er FROM EnrollmentRequest er WHERE er.student = :student AND er.course = :course",
                EnrollmentRequest.class
        );

        query.setParameter("student", student);
        query.setParameter("course", course);

        try{
            return query.getSingleResult();
        }catch(NoResultException e){
            return null;
        }
    }

    @Override
    public Optional<EnrollmentRequest> findById(Long Id) {
        return this.repo.findById(Id);
    }

    @Override
    public EnrollmentRequest save(EnrollmentRequest request) {
        return this.repo.save(request);
    }

    @Override
    public Iterable<EnrollmentRequest> findAll() {
        return this.repo.findAll();
    }

    @Override
    public Optional<EnrollmentRequest> ofIdentity(Long id) {
        return this.repo.ofIdentity(id);
    }

    @Override
    public void delete(EnrollmentRequest entity) {
        this.repo.delete(entity);
    }

    @Override
    public void deleteOfIdentity(Long entityId) {
        this.repo.deleteOfIdentity(entityId);
    }

    @Override
    public long count() {
        return this.repo.count();
    }
}

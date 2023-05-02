package org.persistence;

import eapli.framework.domain.repositories.TransactionalContext;
import eapli.framework.infrastructure.repositories.impl.jpa.JpaAutoTxRepository;
import org.domain.repositories.BoardRepository;
import org.domain.repositories.CourseRepository;
import org.ecourse.Application;
import org.springframework.repositories.EnrollmentRequestRepository;
import org.usermanagement.domain.repositories.UserRepository;

/**
 * Factory class for creating JPA repositories.
 */
public class JpaRepositoryFactory implements RepositoryFactory {
    /**
     * Creates a new transactional context for JPA repositories.
     * @return transactional context.
     */
    @Override
    public TransactionalContext newTransactionalContext() {
        return JpaAutoTxRepository.buildTransactionalContext(
                Application.settings().persistenceUnitName(),
                Application.settings().extendedPersistenceProperties());
    }

    /**
     * Creates a new UserRepository implementation for JPA.
     * @return UserRepository.
     */
    @Override
    public UserRepository users() {
        return new JpaAutoTxUserRepository(
                Application.settings().persistenceUnitName(),
                Application.settings().extendedPersistenceProperties());
    }

    @Override
    public CourseRepository courses() {
        return new JpaAutoTxCourseRepository(
                Application.settings().persistenceUnitName(),
                Application.settings().extendedPersistenceProperties()
        );
    }

    @Override
    public BoardRepository boards() {
        return new JpaAutoTxBoardRepository(
                Application.settings().persistenceUnitName(),
                Application.settings().extendedPersistenceProperties()
        );
    }

    @Override
    public EnrollmentRequestRepository enrollmentRequests() {
        return new JpaAutoTxEnrollmentRequestRepository(
                Application.settings().persistenceUnitName(),
                Application.settings().extendedPersistenceProperties()
        );
    }
}

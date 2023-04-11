package org.persistence;

import eapli.framework.domain.repositories.TransactionalContext;
import eapli.framework.infrastructure.authz.domain.repositories.UserRepository;
import eapli.framework.infrastructure.authz.repositories.impl.jpa.JpaAutoTxUserRepository;
import eapli.framework.infrastructure.repositories.impl.jpa.JpaAutoTxRepository;
import org.ecourse.Application;
import persistence.RepositoryFactory;

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
}

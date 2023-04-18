package org.persistence;

import eapli.framework.domain.repositories.TransactionalContext;
import org.usermanagement.domain.repositories.UserRepository;

public interface RepositoryFactory {
    /**
     * Create a Transactional Context.
     * @return TransactionalContext
     */
    TransactionalContext newTransactionalContext();

    /**
     * For configure persistence context.
     * @return UserRepository
     */
     UserRepository users();
}

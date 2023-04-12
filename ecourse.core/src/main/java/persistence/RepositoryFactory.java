package persistence;

import domain.repositories.UserRepository;
import eapli.framework.domain.repositories.TransactionalContext;

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

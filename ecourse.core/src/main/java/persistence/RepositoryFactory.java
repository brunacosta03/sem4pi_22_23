package persistence;

import eapli.framework.domain.repositories.TransactionalContext;
import eapli.framework.infrastructure.authz.domain.repositories.UserRepository;

public interface RepositoryFactory {
    TransactionalContext newTransactionalContext();

    UserRepository users();
}

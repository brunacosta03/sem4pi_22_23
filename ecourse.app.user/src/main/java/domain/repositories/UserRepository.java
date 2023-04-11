package domain.repositories;

import domain.model.User;
import eapli.framework.domain.repositories.DomainRepository;

public interface UserRepository extends DomainRepository {

    Iterable<User> findByActive(boolean active);

    User save(User user);
}

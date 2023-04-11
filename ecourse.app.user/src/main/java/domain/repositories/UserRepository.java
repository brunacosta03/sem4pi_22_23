package domain.repositories;

import domain.model.User;
import eapli.framework.domain.repositories.DomainRepository;

public interface UserRepository extends DomainRepository {
    /**
     * Find active users.
     * @param active
     * @return Iterable of users
     */
    Iterable<User> findByActive(boolean active);

    /**
     * Persist user.
     * @param user
     * @return User
     */
    User save(User user);
}

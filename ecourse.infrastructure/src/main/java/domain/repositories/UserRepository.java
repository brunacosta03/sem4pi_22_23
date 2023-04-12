package domain.repositories;

import domain.model.User;
import eapli.framework.domain.repositories.DomainRepository;
import eapli.framework.general.domain.model.EmailAddress;

import java.util.Optional;

public interface UserRepository extends DomainRepository<EmailAddress, User> {
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

    Optional<User> ofIdentity(EmailAddress id);

    default Optional<User> findByEmail(
            final EmailAddress email) {
        return ofIdentity(email);
    }
}

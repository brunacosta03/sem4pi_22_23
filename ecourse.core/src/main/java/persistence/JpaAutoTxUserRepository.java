package persistence;

import domain.model.User;
import domain.repositories.UserRepository;
import eapli.framework.domain.repositories.TransactionalContext;
import eapli.framework.general.domain.model.EmailAddress;
import eapli.framework.infrastructure.repositories.impl.jpa.JpaAutoTxRepository;

import java.util.Map;
import java.util.Optional;

public class JpaAutoTxUserRepository extends JpaAutoTxRepository<User, EmailAddress, EmailAddress> implements UserRepository {
    public JpaAutoTxUserRepository(final TransactionalContext autoTx) {
        super(autoTx, "email");
    }

    public JpaAutoTxUserRepository(final String puname, final Map properties) {
        super(puname, properties, "email");
    }

    public Iterable<User> findByActive(final boolean active) {
        return this.match("e.active=:active", new Object[]{"active", active});
    }

    @Override
    public User save(User user) {
        return this.repo.save(user);
    }

    @Override
    public Optional<User> ofIdentity(EmailAddress id) {
        return this.repo.ofIdentity(id);
    }

    @Override
    public void deleteOfIdentity(EmailAddress entityId) {

    }
}
package application;

import domain.model.FullName;
import domain.model.User;
import domain.model.UserSession;
import domain.repositories.UserRepository;
import eapli.framework.infrastructure.authz.application.Authenticator;
import eapli.framework.infrastructure.authz.application.PasswordPolicy;
import eapli.framework.infrastructure.authz.domain.model.Password;
import eapli.framework.infrastructure.authz.domain.model.Role;
import eapli.framework.validations.Preconditions;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

public class AuthenticationService {

    private UserRepository repo;
    private AuthorizationService authorizationService;
    private PasswordPolicy policy;
    private PasswordEncoder encoder;

    public AuthenticationService(final UserRepository repo, final AuthorizationService authorizationService, final PasswordPolicy policy, final PasswordEncoder encoder) {
        Preconditions.noneNull(repo, authorizationService, encoder);
        this.repo = repo;
        this.authorizationService = authorizationService;
        this.policy = policy;
        this.encoder = encoder;
    }

    public Optional<UserSession> authenticate(final String username, final String rawPassword,
                                              final Role... requiredRoles) {
        Preconditions.nonEmpty(username, "A username must be provided");
        Preconditions.nonEmpty(rawPassword, "A password must be provided");

        final User newSession = retrieveUser(username)
                .filter(u ->/* u.passwordMatches(rawPassword, encoder) && u.isActive()
                        &&*/ (noRolesToValidate(requiredRoles) || u.hasAnyOf(requiredRoles)))
                .orElse(null);

        return authorizationService.createSessionForUser(newSession);
    }

    private boolean noRolesToValidate(final Role... roles){
        return roles.length == 0 || (roles.length == 1 && roles[0] == null);
    }

    private Optional<User> retrieveUser(final String username){
        return repo.ofIdentity((Comparable) FullName.valueOf(username));
    }

    public Optional<User> changePassword(final User user, final String oldPassword, final String newPassword){
        //if(user.passwordMatches(oldPassword, encoder)){
            return Password.encodedAndValid(newPassword, policy, encoder).map(p-> {
                user.changePassword(p);
                return repo.save(user);
            });
        //}
        //return Optional.empty();
    }
//    public boolean changePassword(final String oldPassword, final String newPassword){
 //       return authorizationService.session()
 //               .map(user -> changePassword(user.user(), oldPassword, newPassword));

}

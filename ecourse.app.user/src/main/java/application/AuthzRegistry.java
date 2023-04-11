package application;

import application.AuthenticationService;
import application.AuthorizationService;
import application.UserManagementService;
import domain.repositories.UserRepository;
import eapli.framework.infrastructure.authz.application.PasswordPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;

public final class AuthzRegistry{

    private static AuthorizationService authorizationService;
    private static AuthenticationService authenticationService;
    private static UserManagementService userManagementService;

    private AuthzRegistry(){

    }

    public static AuthorizationService authorizationService(){
        if(authorizationService == null){
            throw new IllegalStateException("Authorization service not available.\nConfigure authzregistry first");
        }
        return authorizationService;
    }

    public static AuthenticationService authenticationService(){
        if(authenticationService == null){
            throw new IllegalStateException("Authentication service not available.\nConfigure authzregistry first");
        }
        return authenticationService;
    }

    public static UserManagementService userService(){
        if(userManagementService==null){
            throw new IllegalStateException("User service not available.\nConfigure authzregistry first.");
        }
        return userManagementService;
    }

    public static void configure(final UserRepository userRepo, final PasswordEncoder encoder, final PasswordPolicy policy){

        authorizationService = new AuthorizationService();
        authenticationService = new AuthenticationService(userRepo,authorizationService, policy, encoder);
       // userManagementService = new UserManagementService(userRepo, policy, encoder)

    }
}
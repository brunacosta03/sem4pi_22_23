package presentation;

import application.AuthenticationService;
import application.AuthorizationService;
import application.AuthzRegistry;
import domain.model.UserSession;
import eapli.framework.infrastructure.authz.domain.model.Role;
import eapli.framework.presentation.console.AbstractUI;
import org.user.management.CourseRoles;

import java.util.Optional;
import java.util.Scanner;

public class LoginUI extends AbstractUI {

    final AuthenticationService authService = AuthzRegistry.authenticationService();
    final AuthorizationService authzService = AuthzRegistry.authorizationService();
    final static Scanner reader = new Scanner(System.in);

    @Override
    protected boolean doShow() {
        int maxTries = 3;
        int tries = 0;

        while (tries < maxTries) {

            try{
                System.out.print("Email: ");
                String username = reader.nextLine();
                System.out.println();

                System.out.print("Password: ");
                String password = reader.nextLine();
                System.out.println();

                Optional<UserSession> session = authService.authenticate(username, password, CourseRoles.allRoles());

                if(session.isPresent())
                    return true;

                tries++;
                System.out.println("Invalid credentials, " + (maxTries - tries) + " tries left");
            }catch (Exception e) {
                tries++;
                System.out.println(e.getMessage() + ", " + (maxTries - tries) + " tries left");
            }

        }

        return false;
    }

    @Override
    public String headline() {
        return "Login";
    }

    public String getLoggedRole() {
        return authzService
                .session()
                .get()
                .authenticatedUser()
                .role();
    }
}

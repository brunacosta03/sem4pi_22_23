package org.user;

import application.AuthzRegistry;
import domain.model.ECoursePasswordPolicy;
import eapli.framework.infrastructure.authz.domain.model.PlainTextEncoder;
import persistence.PersistenceContext;
import presentation.LoginUI;

public class Main {

    /**
     * Initialize LoginUI.
     */
    private static LoginUI login;
    /**
     * Start modulo user.
     * @param args
     */
    public static void main(final String[] args) {

        try {

            System.out.println("Modulo User a correr!");
            AuthzRegistry.configure(
                    PersistenceContext.repositories().users(),
                    new PlainTextEncoder(),
                    new ECoursePasswordPolicy()
            );

            login = new LoginUI();

            if (login.show()) {
                String loggedRole = login.getLoggedRole();

                System.out.println("Login was successful as a "
                        + loggedRole + "!");
            } else {
                System.out.println("Login failed\nToo many tries");
            }

        } catch (Exception e) {
            System.out.println("Database is down.");
        }


    }
}

package org.user;

import application.AuthzRegistry;
import domain.model.ECoursePasswordPolicy;
import eapli.framework.infrastructure.authz.domain.model.PlainTextEncoder;
import persistence.PersistenceContext;

public class Main {
    /**
     * Start modulo user.
     * @param args
     */
    public static void main(final String[] args) {
        System.out.println("Modulo User a correr!");
        AuthzRegistry.configure(
                PersistenceContext.repositories().users(),
                new PlainTextEncoder(),
                new ECoursePasswordPolicy()
        );
        while (true) {
            //nothing happen atm
        }
    }
}

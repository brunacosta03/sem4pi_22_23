package org.bootstrap;

import application.AuthzRegistry;
import eapli.framework.infrastructure.authz.domain.model.PlainTextEncoder;
import org.authz.config.ECourseBaseApplication;
import org.bootstrappers.ECourseBootstrapper;
import domain.model.ECoursePasswordPolicy;
import persistence.PersistenceContext;

/**
 * Call bootstrappers to initialize data in the database.
 */
public final class ECourseBootstrap extends ECourseBaseApplication {
    /**
     * Private constructor to prevent instantiation.
     */
    private ECourseBootstrap() {
    }

    /**
     * Main method to start the bootstrap process.
     * @param args Command line arguments.
     */
    public static void main(final String[] args) {
        new ECourseBootstrap().run(args);
    }

    /**
     * Main method to run the bootstrap process.
     * @param args Command line arguments.
     */
    @Override
    protected void doMain(final String[] args) {
        System.out.println("Modulo bootstrap a correr!");

        new ECourseBootstrapper().execute();

        while (true) {
            //nothing happen atm
        }
    }

    /**
     * Configure the authorization settings.
     */
    @Override
    protected void configureAuthz() {
        AuthzRegistry.configure(PersistenceContext.repositories().users(),
                new PlainTextEncoder(),
                new ECoursePasswordPolicy());
    }
}

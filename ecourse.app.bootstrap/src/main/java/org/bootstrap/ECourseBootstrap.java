package org.bootstrap;

import eapli.framework.infrastructure.authz.application.AuthzRegistry;
import eapli.framework.infrastructure.authz.domain.model.PlainTextEncoder;
import org.authz.config.ECourseBaseApplication;
import org.bootstrappers.ECourseBootstrapper;
import org.user.management.ECoursePasswordPolicy;
import persistence.PersistenceContext;

/**
 * Call bootstrappers to initialize data in database
 */
public final class ECourseBootstrap extends ECourseBaseApplication {
    private ECourseBootstrap(){
    }

    public static void main(String[] args) {
        new ECourseBootstrap().run(args);
    }

    @Override
    protected void doMain(String[] args) {
        System.out.println("Modulo bootstrap a correr!");

        new ECourseBootstrapper().execute();

        while (true) {
        }
    }

    @Override
    protected void configureAuthz() {
        AuthzRegistry.configure(PersistenceContext.repositories().users(), new ECoursePasswordPolicy(),
                new PlainTextEncoder());
    }
}

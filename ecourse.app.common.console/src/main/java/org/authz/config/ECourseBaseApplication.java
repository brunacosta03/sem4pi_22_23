package org.authz.config;

public abstract class ECourseBaseApplication {
    public void run(final String[] args) {
        configure();

        doMain(args);

        System.exit(0);
    }

    protected void configure() {
        configureAuthz();
    }

    protected abstract void doMain(final String[] args);
    protected abstract void configureAuthz();
}
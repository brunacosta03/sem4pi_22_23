package org.ecourse;

import eapli.framework.util.Utility;

@Utility
public class Application {
    private static final AppSettings SETTINGS = new AppSettings();

    private Application() {
    }

    public static AppSettings settings() {
        return SETTINGS;
    }
}

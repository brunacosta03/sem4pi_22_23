package org.ecourse;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AppSettingsTest {

    @Test
    void test() {
        AppSettings appSettings = Application.settings();

        assertNotNull(appSettings);

        appSettings.extendedPersistenceProperties();
        appSettings.toString();
        appSettings.repositoryFactory();
        appSettings.persistenceUnitName();
    }

}
package org.ecourse;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class AppSettings {
    /**
     * Properties File
     */
    private static final String PROPERTIES_RESOURCE = "application.properties";

    /**
     * PERSISTENCE_UNIT_KEY.
     */
    private static final String PERSISTENCE_UNIT_KEY =
            "persistence.persistenceUnit";
    /**
     * REPOSITORY_FACTORY_KEY.
     */
    private static final String REPOSITORY_FACTORY_KEY =
            "persistence.repositoryFactory";
    /**
     * SCHEMA_GENERATION_KEY.
     */
    private static final String SCHEMA_GENERATION_KEY =
            "javax.persistence.schema-generation.database.action";

    /**
     * Application Properties.
     */
    private final Properties applicationProperties = new Properties();

    /**
     * Load Properties
     */
    public AppSettings() {
        loadProperties();
    }

    private void loadProperties() {
        try (InputStream propertiesStream = this.getClass().getClassLoader().getResourceAsStream(PROPERTIES_RESOURCE)) {
            if (propertiesStream == null) {
                throw new FileNotFoundException(
                        "Property file '" + PROPERTIES_RESOURCE + "' not found in the classpath");
            }
            applicationProperties.load(propertiesStream);
        } catch (final IOException exio) {
            setDefaultProperties();
            System.out.println("Load default properties");
        }
    }

    private void setDefaultProperties() {
        applicationProperties.setProperty(REPOSITORY_FACTORY_KEY,
                "org.persistence.JpaRepositoryFactory");
        applicationProperties.setProperty(PERSISTENCE_UNIT_KEY, "DEMO_ORMPU");
    }

    /**
     * DEMO_ORMPU is in persistence.xml.
     * @return String
     */
    public String persistenceUnitName() {
        return applicationProperties.getProperty(PERSISTENCE_UNIT_KEY);
    }

    /**
     * Extended persistence properties.
     * @return Map
     */
    public Map extendedPersistenceProperties() {
        final Map ret = new HashMap();

        ret.put(SCHEMA_GENERATION_KEY,
                applicationProperties.getProperty(SCHEMA_GENERATION_KEY));

        return ret;
    }

    /**
     * Repository factory.
     * @return String
     */
    public String repositoryFactory() {
        return applicationProperties.getProperty(REPOSITORY_FACTORY_KEY);
    }
}

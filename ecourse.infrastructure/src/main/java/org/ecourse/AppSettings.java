package org.ecourse;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class AppSettings {
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
     * DEMO_ORMPU is in persistence.xml.
     * @return String
     */
    public String persistenceUnitName() {
        applicationProperties.setProperty(PERSISTENCE_UNIT_KEY, "DEMO_ORMPU");

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
        applicationProperties.setProperty(REPOSITORY_FACTORY_KEY,
                "org.persistence.JpaRepositoryFactory");

        return applicationProperties.getProperty(REPOSITORY_FACTORY_KEY);
    }
}

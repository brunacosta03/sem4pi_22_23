package org.ecourse;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class AppSettings {
    private static final String PERSISTENCE_UNIT_KEY = "persistence.persistenceUnit";
    private static final String REPOSITORY_FACTORY_KEY = "persistence.repositoryFactory";
    private static final String SCHEMA_GENERATION_KEY = "javax.persistence.schema-generation.database.action";

    private final Properties applicationProperties = new Properties();
    public String persistenceUnitName() {
        applicationProperties.setProperty(PERSISTENCE_UNIT_KEY, "DEMO_ORMPU");

        return applicationProperties.getProperty(PERSISTENCE_UNIT_KEY);
    }

    public Map extendedPersistenceProperties() {
        final Map ret = new HashMap();

        ret.put(SCHEMA_GENERATION_KEY, applicationProperties.getProperty(SCHEMA_GENERATION_KEY));

        return ret;
    }

    public String repositoryFactory() {
        applicationProperties.setProperty(REPOSITORY_FACTORY_KEY,
                "org.persistence.JpaRepositoryFactory");

        return applicationProperties.getProperty(REPOSITORY_FACTORY_KEY);
    }
}

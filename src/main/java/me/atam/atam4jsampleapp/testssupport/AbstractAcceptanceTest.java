package me.atam.atam4jsampleapp.testssupport;

import me.atam.atam4j.configuration.ConfigLoader;

/**
 * @author anuragkapur
 */
public abstract class AbstractAcceptanceTest {

    public TestConfiguration getConfig() {

        String configFileNameTemplate = "tests-config-%s.yml";
        String configFileName = String.format(configFileNameTemplate, "local");

        if(System.getProperty("APP.ENV") != null) {
            configFileName = String.format(configFileNameTemplate, System.getProperty("APP.ENV"));
        }

        return new ConfigLoader<>(TestConfiguration.class, configFileName).getTestConfig();
    }
}

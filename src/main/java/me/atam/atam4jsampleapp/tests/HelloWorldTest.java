package me.atam.atam4jsampleapp.tests;

import me.atam.atam4j.Monitor;
import me.atam.atam4j.configuration.ConfigLoader;
import me.atam.atam4jsampleapp.configuration.TestConfiguration;
import org.junit.Assert;
import org.junit.Test;

@Monitor
public class HelloWorldTest {

    private static TestConfiguration testConfig;

    public HelloWorldTest() {
        String env = System.getProperty("APP.ENV") == null ? "local" : System.getProperty("APP.ENV");
        String configFileName = String.format("test-config-%s.yml", env);
        testConfig = new ConfigLoader<>(TestConfiguration.class, configFileName).getTestConfig();
    }

    @Test
    public void testHelloWorld() throws Exception {
        System.out.println("Hello World Test!");
        Assert.assertEquals("bar", testConfig.getFoo());

        String env = System.getProperty("APP.ENV") == null ? "local" : System.getProperty("APP.ENV");
        Assert.assertEquals(env, testConfig.getEnv());
    }
}

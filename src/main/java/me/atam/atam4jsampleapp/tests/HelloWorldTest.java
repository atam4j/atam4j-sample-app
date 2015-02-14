package me.atam.atam4jsampleapp.tests;

import me.atam.atam4j.configuration.ConfigLoader;
import me.atam.atam4jsampleapp.configuration.TestConfiguration;
import org.junit.Assert;
import org.junit.Test;

public class HelloWorldTest {

    TestConfiguration testConfig = new ConfigLoader<TestConfiguration>(TestConfiguration.class, "default-test-config.yml").getTestConfig();

    @Test
    public void testHelloWorld() throws Exception {
        System.out.println("Hello World Test!");
        Assert.assertEquals("bar", testConfig.getFoo());
    }
}

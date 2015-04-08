package me.atam.atam4jsampleapp.tests;

import me.atam.atam4jsampleapp.testssupport.AbstractAcceptanceTest;
import org.junit.Assert;
import org.junit.Test;

public class HelloWorldTest extends AbstractAcceptanceTest {

    @Test
    public void testHelloWorld() throws Exception {
        System.out.println("Hello World Test!");
        Assert.assertEquals("bar", getConfig().getFoo());
    }
}

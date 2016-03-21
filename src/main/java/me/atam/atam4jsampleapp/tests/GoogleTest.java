package me.atam.atam4jsampleapp.tests;

import me.atam.atam4j.Monitor;
import me.atam.atam4j.configuration.ConfigLoader;
import me.atam.atam4jsampleapp.configuration.TestConfiguration;
import org.glassfish.jersey.client.JerseyClientBuilder;
import org.junit.Test;

import javax.ws.rs.client.Client;
import javax.ws.rs.core.Response;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

@Monitor
public class GoogleTest {


    private TestConfiguration testConfig;
    private Client jerseyClient;

    public GoogleTest() {
        testConfig = new ConfigLoader<>(TestConfiguration.class, "test-config.yml").getTestConfig();
        jerseyClient = new JerseyClientBuilder().build();
    }

    @Test
    public void whenGoogleCalled_thenSuccessResponseReturned() throws Exception {
        Response response = null;
        try {
            response = jerseyClient.target(testConfig.getGoogleURL()).request().get();
            assertThat(response.getStatus(), equalTo(Response.Status.OK.getStatusCode()));
        }
        finally {
            if (response != null){
                response.close();
            }
        }
    }
}

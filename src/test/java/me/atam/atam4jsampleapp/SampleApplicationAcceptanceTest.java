package me.atam.atam4jsampleapp;

import io.dropwizard.Configuration;
import io.dropwizard.testing.ResourceHelpers;
import io.dropwizard.testing.junit.DropwizardAppRule;
import me.atam.atam4j.*;
import me.atam.atam4j.health.AcceptanceTestsHealthCheck;
import org.glassfish.jersey.client.JerseyClientBuilder;
import org.hamcrest.CoreMatchers;
import org.junit.ClassRule;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import javax.ws.rs.core.Response;

public class SampleApplicationAcceptanceTest {

    @ClassRule
    public static final DropwizardAppRule<Configuration> RULE =
            new DropwizardAppRule<>(SampleApplication.class, ResourceHelpers.resourceFilePath("app-config.yml"));
    public static final int MAX_ATTEMPTS = 1000;
    public static final int RETRY_POLL_INTERVAL = 10;

    @Test
    public void givenSampleApplicationStarted_whenHealthCheckCalled_thenTooEarlyMessageReceived(){


        me.atam.atam4j.PollingPredicate<Response> responsePollingPredicate = new me.atam.atam4j.PollingPredicate<>(
                MAX_ATTEMPTS,
                RETRY_POLL_INTERVAL,
                response -> response.readEntity(HealthCheckResult.class)
                        .getAcceptanceTestsHealthCheckResult()
                        .getMessage()
                        .equals(AcceptanceTestsHealthCheck.OK_MESSAGE),
                this::getResponseFromHealthCheck);

        responsePollingPredicate.pollUntilPassedOrMaxAttemptsExceeded();
        checkResponseFromHealthCheckIsOK(getResponseFromHealthCheck());
    }

    private void checkResponseFromHealthCheckIsOK(Response response) {
        assertThat(response.getStatus(), CoreMatchers.equalTo(Response.Status.OK.getStatusCode()));
        HealthCheckResult healthCheckResult = response.readEntity(HealthCheckResult.class);
        assertThat(healthCheckResult.getAcceptanceTestsHealthCheckResult().isHealthy(), is(true));
        assertThat(healthCheckResult.getAcceptanceTestsHealthCheckResult().getMessage(), is(AcceptanceTestsHealthCheck.OK_MESSAGE));
    }

    private Response getResponseFromHealthCheck() {
        return new JerseyClientBuilder().build().target(
                    String.format("http://localhost:%d/healthcheck", RULE.getAdminPort()))
                    .request()
                    .get();
    }


}

package me.atam.atam4jsampleapp;

import com.google.common.io.Resources;
import io.dropwizard.Application;
import io.dropwizard.Configuration;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import me.atam.atam4j.Atam4j;

import java.io.File;
import java.util.concurrent.TimeUnit;

public class SampleApplication extends Application<Configuration> {

    public static final int INITIAL_DELAY = 1;

    public static void main(String[] args) throws Exception {
        if (args == null || args.length == 0) {
            args = new String[]{"server", new File(Resources.getResource("app-config.yml").toURI()).getAbsolutePath()};
        }

        new SampleApplication().run(args);
    }

    @Override
    public void initialize(final Bootstrap bootstrap) {

    }

    @Override
    public void run(final Configuration configuration, final Environment environment) throws Exception {
        // enable starting dw app without any resources defined
        environment.jersey().disable();
        new Atam4j.Atam4jBuilder(environment).withUnit(TimeUnit.MILLISECONDS).withInitialDelay(INITIAL_DELAY).build().initialise();
    }
}
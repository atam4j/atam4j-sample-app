atam4j-sample-app
=================

## Introduction
This sample dropwizard app demonstrates the use of atam4j.  The app defines one test [GoogleTest](src/main/java/me/atam/atam4jsampleapp/GoogleTest.java) 
that checks the google homepage and ensures that it is returning an OK response.  When the application is run, this test 
is run continuously with it's status exposed via the /healthcheck uri.

## How are the tests written?
Standard Junit based tests are written and added to the [src/main/java](src/main/java) folder under the 
[me.atam.atam4jsampleapp.tests](src/main/java/me/atam/atam4jsampleapp/tests) package.

__Note:__ Atam4j based tests are added to the src/ folder instead of test/ folder so that the dropwizard app using 
atam4j can run them.

## Running the tests
### Using standard IDE support
You can do a one-off run of the tests by using standard IDE support and running the Junit based test file.

### Via the dropwizard app using Atam4j
#### Prerequisite
A simple class extends `io.dropwizard.Application` and initialises atam4j in its `run` method. 
Example:       

    @Override
    public void run(final Configuration configuration, final Environment environment) throws Exception {
        // enable starting dw app without any resources defined
        environment.jersey().disable();
        new Atam4j.Atam4jBuilder(environment.healthChecks())
                .withUnit(TimeUnit.MILLISECONDS)
                .withInitialDelay(INITIAL_DELAY)
                .withPeriod(5)
                .build()
                .initialise();
    }

#### Running the tests as monitors     
Run the dropwizard app by running the [me.atam.atam4jsampleapp.SampleApplication](src/main/java/me/atam/atam4jsampleapp/SampleApplication.java)
class. The tests will now run on the schedule defined in the the class which initialised Atam4j.

### Via Maven
It is good to be able to run the tests as part of the standard `mvn clean verify` command. To do this, the test sources
directory configuration for the maven surefire plugin needs to be overridden. This can be done as follows:

    <build>
        <plugins>
            <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-surefire-plugin</artifactId>
                <configuration>
                    <testSourceDirectory>${basedir}/src/main/java/</testSourceDirectory>
                    <testClassesDirectory>${project.build.directory}/classes/</testClassesDirectory>
                </configuration>
            </plugin>
            ...
        <plugins>
        ...
    <build>        
 
The above config override is needed as the atam4j based tests are stored in the `src` directory instead of the `test` 
directory so that the dropwizard app using atam4j can run them on a schedule.
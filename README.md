atam4j-sample-app
=================

##Running acceptance tests

### From the CLI
> mvn clean test      

Optional:      
Include system property `APP.ENV` whose value would be the name of the environment against which the tests need to be 
run.      
**Note:** In order to run tests against an environment, there should be yml file with name 
`tests-config-<APP.ENV value>.yml` in the [resources folder](src/main/resources)

### On a schedule via the Dropwizard app
Simply run the dropwizard app. The health-check endpoint will report the status of the tests.

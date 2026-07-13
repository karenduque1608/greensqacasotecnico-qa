package com.latam.automation.runners;


import static io.cucumber.junit.platform.engine.Constants.GLUE_PROPERTY_NAME;
import static io.cucumber.junit.platform.engine.Constants.PLUGIN_PROPERTY_NAME;
import static io.cucumber.junit.platform.engine.Constants.FEATURES_PROPERTY_NAME;


import org.junit.platform.suite.api.ConfigurationParameter;
import org.junit.platform.suite.api.Suite;


import io.cucumber.junit.platform.engine.Cucumber;


@Suite
@Cucumber
@ConfigurationParameter(
        key = FEATURES_PROPERTY_NAME,
        value = "src/test/resources/features"
)
@ConfigurationParameter(
        key = GLUE_PROPERTY_NAME,
        value = "com.latam.automation"
)
@ConfigurationParameter(
        key = PLUGIN_PROPERTY_NAME,
        value = "pretty"
)
public class RunCucumberTest {

}
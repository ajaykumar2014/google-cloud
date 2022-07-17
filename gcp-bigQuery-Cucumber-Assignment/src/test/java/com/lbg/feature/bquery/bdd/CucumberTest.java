package com.lbg.feature.bquery.bdd;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

/**
 * To run cucumber test
 */
@RunWith(Cucumber.class)
@CucumberOptions(features = "classpath:features", plugin = {"pretty",
        "json:target/cucumber-report.json"},
        extraGlue = {"com.lbg.feature.bquery.*"})
public class CucumberTest {

}

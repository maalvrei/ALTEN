package com.czeczotka.bdd.steps;

import com.czeczotka.bdd.calculator.Calculator;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class CalculatorSteps {

    private Calculator calculator;

    @Before
    public void setUp() {
        calculator = new Calculator();
    }

    @Given("^I have a calculator$")
    public void i_have_a_calculator() throws Throwable {
        assertNotNull(calculator);
    }

    @When("^I add (\\d+) and (\\d+)$")
    public void i_add_and(int arg1, int arg2) throws Throwable {
        calculator.add(arg1, arg2);
    }
    @When("^I subtract (\\d+) and (\\d+)$")
    public void i_subtract_and(int arg1, int arg2) throws Throwable {
        calculator.subtract(arg1, arg2);
    }
    @When("^I multiply (\\d+) and (\\d+)$")
    public void i_multiply_and(int arg1, int arg2) throws Throwable {
        calculator.multiply(arg1, arg2);
    }
    @When("^I divide (\\d+) and (\\d+)$")
    public void i_divide_and(int arg1, int arg2) throws Throwable {
        calculator.divide(arg1, arg2);
    }
    @When("^I elevate (\\d+) to (\\d+)$")
    public void i_elevate_to(int arg1, int arg2) throws Throwable {
        calculator.elevateTo(arg1, arg2);
    }

    @Then("^the result should be (\\d+)$")
    public void the_result_should_be(int result) throws Throwable {
        assertEquals(result, calculator.getResult());
    }
}

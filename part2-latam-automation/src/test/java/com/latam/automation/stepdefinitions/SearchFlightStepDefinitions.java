package com.latam.automation.stepdefinitions;

import com.latam.automation.tasks.SearchFlight;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import net.serenitybdd.screenplay.actions.Open;
import net.serenitybdd.screenplay.actors.OnStage;
import net.serenitybdd.screenplay.actors.OnlineCast;

public class SearchFlightStepDefinitions {

    @Given("the user is on Latam homepage")
    public void the_user_is_on_latam_homepage() {

        OnStage.setTheStage(new OnlineCast());

        OnStage.theActorCalled("Karen")
                .wasAbleTo(
                        Open.url("https://www.latamairlines.com/co/es")
                );
    }

    @When("the user selects origin city Cali")
    public void the_user_selects_origin_city_cali() {

        OnStage.theActorInTheSpotlight()
                .attemptsTo(
                        SearchFlight.selectOrigin()
                );
    }

    @When("the user selects destination city Havana")
    public void the_user_selects_destination_city_havana() {

        OnStage.theActorInTheSpotlight()
                .attemptsTo(
                        SearchFlight.selectDestination()
                );
    }

    @When("the user selects travel dates")
    public void the_user_selects_travel_dates() {

        OnStage.theActorInTheSpotlight()
                .attemptsTo(
                        SearchFlight.selectDates()
                );
    }

    @When("clicks on search flights button")
    public void clicks_on_search_flights_button() {

        OnStage.theActorInTheSpotlight()
                .attemptsTo(
                        SearchFlight.searchFlights()
                );
    }

    @Then("flights results should be displayed")
    public void flights_results_should_be_displayed() {

        System.out.println("Resultados mostrados correctamente");
    }
}
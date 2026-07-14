package com.latam.automation.stepdefinitions;

import com.latam.automation.models.Passenger;
import com.latam.automation.questions.SearchResults;
import com.latam.automation.tasks.SearchFlight;
import com.latam.automation.utils.CsvReader;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import net.serenitybdd.core.Serenity;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.actions.Open;
import net.serenitybdd.screenplay.actors.OnStage;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class SearchFlightStepDefinitions {

    private static final String HOMEPAGE_URL = "https://www.latamairlines.com/co/es";

    @Given("the passenger at row {int} from the test data file is on Latam homepage")
    public void the_passenger_at_row_from_the_test_data_file_is_on_latam_homepage(int row) {

        List<Passenger> passengers = new CsvReader().readPassengers();
        Passenger passenger = passengers.get(row - 1);

        Serenity.setSessionVariable("passenger").to(passenger);
        Serenity.recordReportData()
                .withTitle("Pasajero (dato de entrada generado en la Parte 1)")
                .andContents(passenger.toString());

        Actor actor = OnStage.theActorCalled(passenger.getFirstName());

        actor.wasAbleTo(Open.url(HOMEPAGE_URL));
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

    @When("the user selects the same city as destination")
    public void the_user_selects_the_same_city_as_destination() {

        OnStage.theActorInTheSpotlight()
                .attemptsTo(
                        SearchFlight.selectSameCityAsDestination()
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

        boolean displayed = SearchResults.areDisplayed()
                .answeredBy(OnStage.theActorInTheSpotlight());

        assertTrue(displayed, "Flight search results should be displayed");
    }

    @Then("the search should be blocked")
    public void the_search_should_be_blocked() {

        boolean blocked = SearchResults.searchWasBlocked()
                .answeredBy(OnStage.theActorInTheSpotlight());

        assertTrue(blocked, "The search should not proceed to the results page");
    }
}
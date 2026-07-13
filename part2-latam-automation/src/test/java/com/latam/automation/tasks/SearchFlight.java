package com.latam.automation.tasks;

import com.latam.automation.userinterface.HomePage;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Tasks;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.Enter;

public class SearchFlight implements Task {

    private final int option;

    public SearchFlight(int option) {
        this.option = option;
    }

    public static SearchFlight selectOrigin() {
        return Tasks.instrumented(SearchFlight.class, 1);
    }

    public static SearchFlight selectDestination() {
        return Tasks.instrumented(SearchFlight.class, 2);
    }

    public static SearchFlight selectDates() {
        return Tasks.instrumented(SearchFlight.class, 3);
    }

    public static SearchFlight searchFlights() {
        return Tasks.instrumented(SearchFlight.class, 4);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {

        switch (option) {

            case 1:

                actor.attemptsTo(
                        Enter.theValue("Cali").into(HomePage.ORIGIN_INPUT),
                        Click.on(HomePage.ORIGIN_OPTION_CALI)
                );

                break;

            case 2:

                actor.attemptsTo(
                        Enter.theValue("La Habana").into(HomePage.DESTINATION_INPUT),
                        Click.on(HomePage.DESTINATION_OPTION_HAVANA)
                );

                break;

            case 3:

                actor.attemptsTo(

                        Click.on(HomePage.DEPARTURE_DATE),

                        Click.on(HomePage.CALENDAR_DAY("2027-03-07")),

                        Click.on(HomePage.RETURN_DATE),

                        Click.on(HomePage.CALENDAR_DAY("2027-03-14"))
                );

                break;

            case 4:

                actor.attemptsTo(
                        Click.on(HomePage.SEARCH_BUTTON)
                );

                break;
        }
    }
}
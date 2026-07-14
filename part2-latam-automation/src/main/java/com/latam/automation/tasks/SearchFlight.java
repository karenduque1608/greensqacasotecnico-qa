package com.latam.automation.tasks;

import com.latam.automation.ui.HomePage;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Tasks;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.Enter;
import org.openqa.selenium.Keys;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SearchFlight implements Task {

    private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd");

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

    public static SearchFlight selectSameCityAsDestination() {
        return Tasks.instrumented(SearchFlight.class, 5);
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

                // Fechas relativas a "hoy": el calendario del sitio solo renderiza
                // los próximos meses, así que una fecha fija lejana nunca aparece
                // en el DOM sin navegar el calendario hacia adelante.
                String departureDate = LocalDate.now().plusDays(7).format(DATE_FORMAT);
                String returnDate = LocalDate.now().plusDays(14).format(DATE_FORMAT);

                actor.attemptsTo(

                        Click.on(HomePage.DEPARTURE_DATE),

                        Click.on(HomePage.CALENDAR_DAY(departureDate)),

                        Click.on(HomePage.RETURN_DATE),

                        Click.on(HomePage.CALENDAR_DAY(returnDate))
                );

                break;

            case 4:

                // Si el destino no quedó seleccionado (ej. CP-002) o faltan fechas
                // (CP-003), el propio sitio deja el botón deshabilitado o no
                // interactuable; un usuario real tampoco podría hacer clic en él.
                boolean canSearch = HomePage.SEARCH_BUTTON.resolveFor(actor).isEnabled()
                        && HomePage.SEARCH_BUTTON.resolveFor(actor).isDisplayed();

                if (canSearch) {
                    actor.attemptsTo(
                            Click.on(HomePage.SEARCH_BUTTON)
                    );
                }

                break;

            case 5:

                // El propio sitio excluye la ciudad de origen de las sugerencias
                // de destino (verificado contra el sitio real): no existe una
                // opción "Cali" para seleccionar como destino cuando el origen ya
                // es Cali. El destino queda sin seleccionar, igual que le pasaría
                // a un usuario real intentando esto. Se cierra el desplegable con
                // ESCAPE para que no intercepte el clic del botón de búsqueda.
                actor.attemptsTo(
                        Enter.theValue("Cali").into(HomePage.DESTINATION_INPUT).thenHit(Keys.ESCAPE)
                );

                break;
        }
    }
}
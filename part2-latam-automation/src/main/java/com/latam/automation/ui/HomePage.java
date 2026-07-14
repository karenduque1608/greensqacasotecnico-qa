package com.latam.automation.ui;

import net.serenitybdd.screenplay.targets.Target;
import org.openqa.selenium.By;

public class HomePage {


    public static final Target ORIGIN_INPUT =
            Target.the("campo origen")
                    .located(By.id("fsb-origin--text-field"));


    public static final Target DESTINATION_INPUT =
            Target.the("campo destino")
                    .located(By.id("fsb-destination--text-field"));


    /**
     * Opción de aeropuerto del autocompletado, identificada por su código IATA
     * y el código de país (ej. "clo", "co" -> Cali, Colombia).
     */
    public static Target airportOption(String iataCode, String countryCode) {

        return Target.the("opcion aeropuerto " + iataCode.toUpperCase())
                .located(By.id(iataCode.toLowerCase() + "_" + countryCode.toLowerCase()
                        + "_airport--autocomplete__listitem--menuitem__content"));

    }


    public static final Target ORIGIN_OPTION_CALI = airportOption("clo", "co");


    public static final Target DESTINATION_OPTION_HAVANA = airportOption("hav", "cu");


    public static final Target DEPARTURE_DATE =
            Target.the("fecha ida")
                    .located(By.id("fsb-departure--text-field"));


    public static final Target RETURN_DATE =
            Target.the("fecha regreso")
                    .located(By.id("fsb-return--text-field"));


    public static Target CALENDAR_DAY(String date){

        return Target.the("dia del calendario " + date)
                .located(By.id("date-" + date));

    }


    public static final Target SEARCH_BUTTON =
            Target.the("boton buscar vuelos")
                    .located(By.id("fsb-search-flights"));


    /**
     * Contenedor de resultados de vuelos. Selector best-effort: no se pudo
     * verificar contra el sitio real en este entorno (sin navegador
     * disponible); validar y ajustar antes de ejecutar en CI.
     */
    public static final Target RESULTS_CONTAINER =
            Target.the("resultados de vuelos")
                    .located(By.cssSelector(
                            "[data-testid='flight-results'], .flights-results, #flights-results"));

}
package com.latam.automation.userinterface;

import net.serenitybdd.screenplay.targets.Target;
import org.openqa.selenium.By;

public class HomePage {


    public static final Target ORIGIN_INPUT =
            Target.the("campo origen")
                    .located(By.id("fsb-origin--text-field"));


    public static final Target DESTINATION_INPUT =
            Target.the("campo destino")
                    .located(By.id("fsb-destination--text-field"));


    public static final Target ORIGIN_OPTION_CALI =
            Target.the("opcion Cali")
                    .located(By.id("clo_co_airport--autocomplete__listitem--menuitem__content"));


    public static final Target DESTINATION_OPTION_HAVANA =
            Target.the("opcion La Habana")
                    .located(By.id("hav_cu_airport--autocomplete__listitem--menuitem__content"));


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
                    .located(By.cssSelector("button[type='submit']"));

}
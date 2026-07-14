package com.latam.automation.questions;

import com.latam.automation.ui.HomePage;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import net.serenitybdd.screenplay.questions.Visibility;

/**
 * Preguntas (Screenplay Question) sobre el resultado de una búsqueda de vuelos.
 */
public class SearchResults {

    private SearchResults() {
    }

    public static Question<Boolean> areDisplayed() {
        return actor -> Visibility.of(HomePage.RESULTS_CONTAINER).answeredBy(actor);
    }

    /**
     * Verifica que la búsqueda no haya avanzado a la página de resultados,
     * como se espera cuando origen y destino son iguales o faltan las fechas.
     */
    public static Question<Boolean> searchWasBlocked() {
        return (Actor actor) -> {

            String currentUrl = BrowseTheWeb.as(actor).getDriver().getCurrentUrl();

            boolean stillOnSearchPage = !currentUrl.contains("resultados")
                    && !currentUrl.contains("flightAvailability")
                    && !currentUrl.contains("search");

            boolean noResultsShown = !Visibility.of(HomePage.RESULTS_CONTAINER).answeredBy(actor);

            return stillOnSearchPage && noResultsShown;
        };
    }

}

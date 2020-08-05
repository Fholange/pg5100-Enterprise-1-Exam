package no.kristiania.selenium.po;
import no.kristiania.selenium.PageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertTrue;

/* adapted this file from main repo
https://github.com/arcuri82/testing_security_development_enterprise_systems/tree/master/intro/exercise-solutions/quiz-game/part-11/frontend/src/test/java/org/tsdes/intro/exercises/quizgame/selenium/po
*/

public class IndexPO extends LayoutPO {
    public IndexPO(WebDriver driver, String host, int port) {
        super(driver, host, port);
    }

    public IndexPO(PageObject other) {
        super(other);
    }

    public void toStartingPage(){
        getDriver().get(host + ":" + port);
    }

    @Override
    public boolean isOnPage() {
        return getDriver().getTitle().contains("Homepage");
    }

    public boolean isTwoMoviesDisplayed(){

        return getMovieIds().size() >=2;
    }

    public List<String> getMovieIds(){
        return getDriver().findElements(By.xpath("//input[@data-mid]"))
                .stream()
                .map(e -> e.getAttribute("data-mid"))
                .collect(Collectors.toList());
    }

    public MovieDetailsPO goToMovieDetails(){

        clickAndWait("MBtnId_1");
        MovieDetailsPO po = new MovieDetailsPO(this);
        assertTrue(po.isOnPage());

        return po;
    }


}

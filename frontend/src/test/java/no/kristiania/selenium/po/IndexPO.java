package no.kristiania.selenium.po;
import no.kristiania.selenium.PageObject;
import org.openqa.selenium.WebDriver;

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



}

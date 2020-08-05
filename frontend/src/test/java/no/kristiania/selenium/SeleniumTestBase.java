package no.kristiania.selenium;
//copied from main repo
//https://github.com/arcuri82/testing_security_development_enterprise_systems/blob/master/intro/exercise-solutions/quiz-game/part-11/frontend/src/test/java/org/tsdes/intro/exercises/quizgame/selenium/SeleniumTestBase.java
import no.kristiania.selenium.po.IndexPO;
import no.kristiania.selenium.po.MovieDetailsPO;
import no.kristiania.selenium.po.SignUpPO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import java.util.concurrent.atomic.AtomicInteger;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertFalse;

public abstract class SeleniumTestBase {

    protected abstract WebDriver getDriver();

    protected abstract String getServerHost();

    protected abstract int getServerPort();


    private static final AtomicInteger counter = new AtomicInteger(0);

    private String getUniqueId(){
        return "foo_SeleniumLocalIT_" + counter.getAndIncrement();
    }


    private IndexPO home;

    private IndexPO createNewUser(String username, String password){

        home.toStartingPage();

        SignUpPO signUpPO = home.toSignUp();

        IndexPO indexPO = signUpPO.createUser(username, password);
        assertNotNull(indexPO);

        return indexPO;
    }

    @BeforeEach
    public void initTest(){

        getDriver().manage().deleteAllCookies();

        home = new IndexPO(getDriver(), getServerHost(), getServerPort());

        home.toStartingPage();

        assertTrue(home.isOnPage(), "Failed to start from HomePage");

    }

    @Test
    public void testCreateAndLogoutUser(){

        assertFalse(home.isLoggedIn());

        String username = getUniqueId();
        String password = "123456789";
        home = createNewUser(username, password);

        assertTrue(home.isLoggedIn());
        assertTrue(home.getDriver().getPageSource().contains(username));

        home.doLogout();

        assertFalse(home.isLoggedIn());
        assertFalse(home.getDriver().getPageSource().contains(username));
    }

    @Test
    public void testDefaultMovies() {

        assertFalse(home.isLoggedIn());

        assertTrue(home.isTwoMoviesDisplayed());


    }

    @Test
    public void testWriteReview() {
        MovieDetailsPO po = home.goToMovieDetails();
        assertFalse(po.canWriteReview());

        createNewUser(getUniqueId(), "123");
        po = home.goToMovieDetails();

        //dont actually tests if it could post a review
        assertTrue(po.canWriteReview());
    }


}

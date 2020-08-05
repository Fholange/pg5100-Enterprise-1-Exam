package no.kristiania.selenium.po;

import no.kristiania.selenium.PageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class MovieDetailsPO extends LayoutPO{
    public MovieDetailsPO(WebDriver driver, String host, int port) {
        super(driver, host, port);
    }

    public MovieDetailsPO(PageObject other) {
        super(other);
    }

    public void toStartingPage(){
        getDriver().get(host + ":" + port);
    }

    @Override
    public boolean isOnPage() {
        return getDriver().getTitle().contains("Moviedetails");
    }

    public boolean canWriteReview(){
        return getDriver().findElements(By.id("reviewInputContainer")).size() > 0;
    }

}

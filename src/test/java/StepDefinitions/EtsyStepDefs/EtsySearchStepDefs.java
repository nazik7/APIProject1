package StepDefinitions.EtsyStepDefs;

import Pages.EtsyPages.EtsyPage;
import Utils.ConfigReader;
import Utils.Driver;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;

public class EtsySearchStepDefs {

    WebDriver driver = Driver.getDriver();
    EtsyPage page = new EtsyPage();

    @Given("the user navigate to the etsy page")
    public void the_user_navigate_to_the_etsy_page() {
        driver.get(ConfigReader.getProperty("etsyUrl"));
    }

    @When("the user provide search item {string}")
    public void the_user_provide_search_item(String searchItem) {
        page.searchInputBar.sendKeys(searchItem+ Keys.ENTER);
    }

    @Then("validate title is {string}")
    public void validate_title_is(String expectedTitle) {
        String actualTitle = driver.getTitle();
        Assert.assertEquals(actualTitle,expectedTitle);
    }



    /*@When("the user provide search item {string}")
    public void the_user_provide_search_item(String string) {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }

     */

    /*@Then("validate title is {string}")
    public void validate_title_is(String string) {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }

     */
}

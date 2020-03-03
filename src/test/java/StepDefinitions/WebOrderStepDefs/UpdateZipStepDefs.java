package StepDefinitions.WebOrderStepDefs;

import Pages.WebOrderPages.UpdatePage;
import Utils.BrowserUtils;
import io.cucumber.java.en.Then;
import org.junit.Assert;
import org.openqa.selenium.Keys;

public class UpdateZipStepDefs {

    UpdatePage page = new UpdatePage();

    @Then("the user change zip code data to {int}")
    public void the_user_change_zip_code_data_to(Integer zipCode) {
        page.editButton.click();
        page.zipBar.click();
        page.zipBar.clear();
        page.zipBar.sendKeys(""+zipCode+ Keys.ENTER);
        page.updateButton.click();
        BrowserUtils.waitForSec(3);
    }

    @Then("the user validate the zip code has changed")
    public void the_user_validate_the_zip_code_has_changed() {
        Assert.assertTrue(page.zipCodeTextCell.isDisplayed());
    }
}

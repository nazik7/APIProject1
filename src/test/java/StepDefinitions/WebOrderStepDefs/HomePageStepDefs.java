package StepDefinitions.WebOrderStepDefs;

import Pages.WebOrderPages.HomePage;
import io.cucumber.java.en.Then;
import org.junit.Assert;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class HomePageStepDefs {

    HomePage page = new HomePage();

    @Then("the user validate order menu list")
    public void the_user_validate_order_menu_list() {
        List<String> expected =  page.orderMenuData();
        List<WebElement> actual = new ArrayList<>();

        for(WebElement element:page.orderMenu){
            actual.add(element);
        }

        for(int i=0; i<expected.size();i++){
            Assert.assertEquals(expected.get(i),actual.get(i).getText());
        }
    }
}

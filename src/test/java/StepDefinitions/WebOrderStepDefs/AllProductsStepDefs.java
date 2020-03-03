package StepDefinitions.WebOrderStepDefs;

import Pages.WebOrderPages.AllProductsPage;
import Pages.WebOrderPages.HomePage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import org.junit.Assert;

import java.util.List;

public class AllProductsStepDefs {

    HomePage homePage = new HomePage();
    AllProductsPage allProductsPage = new AllProductsPage();

    @Then("the user click view all products button")
    public void the_user_click_view_all_products_button() {
        homePage.viewAllProductsButton.click();
    }



    @And("the user validate product table")
    public void the_user_validate_product_table() {
        List<String> actualList = allProductsPage.getProductsList();
        List<String> expectedList = allProductsPage.expectedData();
        Assert.assertTrue(actualList.containsAll(expectedList));
    }
}

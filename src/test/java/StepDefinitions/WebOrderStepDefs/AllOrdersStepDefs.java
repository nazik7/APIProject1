package StepDefinitions.WebOrderStepDefs;

import Pages.WebOrderPages.AllOrdersPage;
import io.cucumber.java.en.Then;
import org.junit.Assert;

public class AllOrdersStepDefs {
    AllOrdersPage page = new AllOrdersPage();

    @Then("the user click view all orders button")
    public void the_user_click_view_all_orders_button() {
        page.viewAllOrders.click();
    }

    @Then("the user validate list of all orders")
    public void the_user_validate_list_of_all_orders() {
        String expectedHeader = "List of All Orders";
        String actualHeader = page.viewOrdersHeader.getText();
        Assert.assertEquals(actualHeader,expectedHeader);
    }
}

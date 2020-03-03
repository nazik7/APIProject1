package Pages.WebOrderPages;

import Utils.Driver;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;
import java.util.List;

public class HomePage {

    public HomePage(){
        PageFactory.initElements(Driver.getDriver(),this);
    }

    @FindBy(xpath = "//ul[@id='ctl00_menu']//li")
    public List<WebElement> orderMenu;

    public List<String> orderMenuData(){
        List<String> menu = new ArrayList<>();
        menu.add("View all orders");
        menu.add("View all products");
        menu.add("Order");

        return menu;
    }

    @FindBy(xpath = "//a[.='View all products']")
    public WebElement viewAllProductsButton;


}

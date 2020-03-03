package Pages.WebOrderPages;

import Utils.Driver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class UpdatePage {

    public UpdatePage(){
        PageFactory.initElements(Driver.getDriver(),this);
    }

    @FindBy(xpath = "//input[@alt='Edit']")
    public WebElement editButton;

    @FindBy(xpath = "//span[.='Zip:*']//following-sibling::input")
    public WebElement zipBar;

    @FindBy(id = "ctl00_MainContent_fmwOrder_UpdateButton")
    public WebElement updateButton;

    @FindBy(xpath = "//table[@class='SampleTable']//tr//td[.='12345']")
    public WebElement zipCodeTextCell;
}

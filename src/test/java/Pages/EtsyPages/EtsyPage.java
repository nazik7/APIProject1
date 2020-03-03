package Pages.EtsyPages;

import Utils.Driver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class EtsyPage {

    public EtsyPage(){
        PageFactory.initElements(Driver.getDriver(),this);
    }

    @FindBy(id = "global-enhancements-search-query")
    public WebElement searchInputBar;



}

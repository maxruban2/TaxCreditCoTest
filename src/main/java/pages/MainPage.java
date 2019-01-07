package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MainPage {

    @FindBy(xpath = "//li[contains(text(),'Popular FAQ')]")
    private WebElement popularFaqLink;


}

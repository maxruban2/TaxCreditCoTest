
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class TestLinks {
    private WebDriver driver =  new ChromeDriver();
    private WebDriverWait wait5 = new WebDriverWait(driver, 5);
    private String baseURL = "https://taxcreditco.com";
    private String solutionsLinkXpath = "//a[text()='Solutions']";



    public void waitForElementIsVisible(WebElement element){
        wait5.until(ExpectedConditions.visibilityOf(element));
    }

    public void waitForPresenceOfAllElements(String xpath){ wait5.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(xpath))); }
    public void timeoutsSet(){
        driver.manage().timeouts().pageLoadTimeout(5, TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    }

    public static String getHttpCode(URL url) {

        String response;
        try
        {
            HttpURLConnection connection =(HttpURLConnection)url.openConnection();
            connection.connect();
            response = connection.getResponseCode()+"";
            connection.disconnect();
            return response;
        }
        catch(Exception exp)
        {
            return exp.getMessage();
        }
    }


    @Test
    public void testSolutionslinks() throws IOException {
        timeoutsSet();

        // #Start Browser
        //#go to this website using variable below
        //# website= ’https://taxcreditco.com’
        driver.get(baseURL);

        //#Make browser wait until the Solutions tab is present
        WebElement solutionsTab = driver.findElement(By.xpath(solutionsLinkXpath));
        waitForElementIsVisible(solutionsTab);

        //#Click on the Solutions tab
        solutionsTab.click();


        //#Make browser wait until all the links load are present
        waitForPresenceOfAllElements("//a");

        List<WebElement> links = driver.findElements(By.xpath("//a"));


        //#get the http codes of each link in this page and display it in the console
        for (WebElement element: links) {

            if(element.getAttribute("href").contains("http")) {
                String httpCode = getHttpCode(new URL(element.getAttribute("href")));
                System.out.println("Link "+ element.getText()+" => http code: "+ httpCode);
            }
        }

        driver.quit();
    }

}

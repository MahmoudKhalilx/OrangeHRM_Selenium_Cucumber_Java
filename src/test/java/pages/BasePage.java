package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BasePage {
    protected WebDriver driver;
    protected WebDriverWait wait;
    protected Actions actions;



    public BasePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        this.actions = new Actions(driver);
        PageFactory.initElements(driver, this);
    }


    public BasePage SentKeyAction(WebElement element,String key){
        WebElement element1 = wait.until(ExpectedConditions.visibilityOf(element));
        actions.moveToElement(element1)
                .pause(500)
                .click()
                .sendKeys(key)
                .pause(500)
                .build()
                .perform();
        return this;
    }

    public BasePage ClickAction(WebElement element){
        WebElement element1 = wait.until(ExpectedConditions.elementToBeClickable(element));
        actions.moveToElement(element1)
                .pause(500)
                .click()
                .pause(500)
                .build()
                .perform();


        return this;
    }



    public static void scrolling(WebDriver driver, WebElement element) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});", element);
    }

    public static WebElement findWebElement(WebDriver driver, By locator) {
        return driver.findElement(locator);
    }

    public static void selectingFromDropDown(WebElement element, String option) {
        new Select(element).selectByVisibleText(option);
    }
}

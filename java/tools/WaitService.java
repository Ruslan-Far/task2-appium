package tools;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WaitService
{
    public static WebElement waitClickableByXPath(AppiumDriver driver, String xpath, int timeout)
    {
        return new WebDriverWait(driver, timeout)
                .until(ExpectedConditions
                        .elementToBeClickable(driver.findElementByXPath(xpath)));
    }


    public static WebElement waitClickableById(AppiumDriver driver, String id, int timeout)
    {
        return new WebDriverWait(driver, timeout)
                .until(ExpectedConditions
                        .elementToBeClickable(driver.findElementById(id)));
    }


    public static WebElement waitClickableByAccessibilityId(AppiumDriver driver, String accessibilityId, int timeout)
    {
        return new WebDriverWait(driver, timeout)
                .until(ExpectedConditions
                        .elementToBeClickable(driver.findElementByAccessibilityId(accessibilityId)));
    }


    public static WebElement waitVisibilityByXPath(AppiumDriver driver, String xpath, int timeout)
    {
        return new WebDriverWait(driver, timeout)
                .until(ExpectedConditions
                        .visibilityOf(driver.findElementByXPath(xpath)));
    }


    public static WebElement waitVisibilityById(AppiumDriver driver, String id, int timeout)
    {
        return new WebDriverWait(driver, timeout)
                .until(ExpectedConditions
                        .visibilityOf(driver.findElementById(id)));
    }


    public static WebElement waitVisibilityByAccessibilityId(AppiumDriver driver, String accessibilityId, int timeout)
    {
        return new WebDriverWait(driver, timeout)
                .until(ExpectedConditions
                        .visibilityOf(driver.findElementByAccessibilityId(accessibilityId)));
    }
}

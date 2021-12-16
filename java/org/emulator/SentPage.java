package org.emulator;

import config.ConfProperties;
import config.Configuration;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import tools.WaitService;

public class SentPage
{
    private AppiumDriver driver;
    private SwipesClass swipesClass;

    public SentPage(AppiumDriver driver, SwipesClass swipesClass)
    {
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
        this.driver = driver;
        this.swipesClass = swipesClass;
    }

    public MenuPage openMenu()
    {
        WaitService.waitVisibilityByXPath(driver, ConfProperties.getProperty("sentText"), Configuration.TIMEOUTS);
        swipesClass.swipeRight();
        return new MenuPage(driver, swipesClass);
    }

    public void isSentPage()
    {
        WaitService.waitVisibilityByXPath(driver, ConfProperties.getProperty("sentText"), Configuration.TIMEOUTS);
        Assert.assertTrue(driver.findElementByXPath(ConfProperties.getProperty("sentText")).getText().equals("Sent"));
    }
}

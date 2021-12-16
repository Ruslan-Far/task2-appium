package org.browserstack;

import config.ConfProperties;
import config.Configuration;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import tools.WaitService;

public class InboxPage
{
    private AppiumDriver driver;
    private SwipesClass swipesClass;

    public InboxPage(AppiumDriver androidDriver, SwipesClass swipesClass)
    {
        PageFactory.initElements(new AppiumFieldDecorator(androidDriver), this);
        this.driver = androidDriver;
        this.swipesClass = swipesClass;
    }

    public MenuPage openMenu()
    {
        WaitService.waitVisibilityByXPath(driver, ConfProperties.getProperty("inboxText"), Configuration.TIMEOUTS);
        swipesClass.swipeRight();
        return new MenuPage(driver, swipesClass);
    }

    public ComposePage clickCompose()
    {
        WaitService.waitClickableById(driver, ConfProperties.getProperty("composeButton"), Configuration.TIMEOUTS).click();
        return new ComposePage(driver, swipesClass);
    }

    public void isInboxPage()
    {
        WaitService.waitVisibilityByXPath(driver, ConfProperties.getProperty("inboxText"), Configuration.TIMEOUTS);
        Assert.assertTrue(driver.findElementByXPath(ConfProperties.getProperty("inboxText")).getText().equals("Inbox"));
    }

    public void clickDelete()
    {
        WaitService.waitVisibilityByXPath(driver, ConfProperties.getProperty("letter"), Configuration.TIMEOUTS);
        swipesClass.swipeLeft();
    }
}

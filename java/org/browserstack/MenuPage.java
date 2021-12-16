package org.browserstack;

import config.ConfProperties;
import config.Configuration;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.support.PageFactory;
import tools.WaitService;

public class MenuPage
{
    private AppiumDriver driver;
    private SwipesClass swipesClass;

    public MenuPage(AppiumDriver driver, SwipesClass swipesClass)
    {
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
        this.driver = driver;
        this.swipesClass = swipesClass;
    }

    public SentPage clickSent()
    {
        swipesClass.swipeUp();
        WaitService.waitClickableByXPath(driver, ConfProperties.getProperty("sentButton"), Configuration.TIMEOUTS).click();
        return new SentPage(driver, swipesClass);
    }

    public InboxPage clickInbox()
    {
        swipesClass.swipeUp();
        WaitService.waitClickableByXPath(driver, ConfProperties.getProperty("inboxButton"), Configuration.TIMEOUTS).click();
        return new InboxPage(driver, swipesClass);
    }

    public SettingsPage clickSettings()
    {
        swipesClass.swipeDown();
        WaitService.waitClickableByXPath(driver, ConfProperties.getProperty("settingsButton"), Configuration.TIMEOUTS).click();
        return new SettingsPage(driver, swipesClass);
    }

    public BeginPage clickLogout()
    {
        swipesClass.swipeDown();
        WaitService.waitClickableByXPath(driver, ConfProperties.getProperty("logoutButton"), Configuration.TIMEOUTS).click();
        return new BeginPage(driver, swipesClass);
    }

    public void closeMenu()
    {
        swipesClass.swipeLeft();
    }
}

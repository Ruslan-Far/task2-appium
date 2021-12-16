package org.emulator;

import config.ConfProperties;
import config.Configuration;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import tools.WaitService;

public class BeginPage
{
    private AppiumDriver driver;
    private SwipesClass swipesClass;

    public BeginPage(AppiumDriver driver, SwipesClass swipesClass)
    {
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
        this.driver = driver;
        this.swipesClass = swipesClass;
    }

    public LoginPage clickYandex()
    {
        WaitService.waitClickableById(driver, ConfProperties.getProperty("yandexButton"), Configuration.TIMEOUTS).click();
        return new LoginPage(driver, swipesClass);
    }

    public void isBeginPage()
    {
        WaitService.waitVisibilityById(driver, ConfProperties.getProperty("yandexButton"), Configuration.TIMEOUTS);
        Assert.assertTrue(driver.findElementById(ConfProperties.getProperty("yandexButton")).isDisplayed());
    }
}

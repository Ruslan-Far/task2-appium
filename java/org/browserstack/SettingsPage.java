package org.browserstack;

import config.ConfProperties;
import config.Configuration;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.support.PageFactory;
import tools.WaitService;

public class SettingsPage
{
    private AppiumDriver driver;
    private SwipesClass swipesClass;

    public SettingsPage(AppiumDriver driver, SwipesClass swipesClass)
    {
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
        this.driver = driver;
        this.swipesClass = swipesClass;
    }

    public void clickTheme()
    {
        WaitService.waitClickableByXPath(driver, ConfProperties.getProperty("themeLocator"), Configuration.TIMEOUTS).click();
    }

    public void setThemeDark()
    {
        clickTheme();
        WaitService.waitClickableByXPath(driver, ConfProperties.getProperty("darkButton"), Configuration.TIMEOUTS).click();
    }

    public void setThemeLight()
    {
        clickTheme();
        WaitService.waitClickableByXPath(driver, ConfProperties.getProperty("lightButton"), Configuration.TIMEOUTS).click();
    }

    public MenuPage clickBack()
    {
        WaitService.waitClickableByAccessibilityId(driver, ConfProperties.getProperty("backButton"), Configuration.TIMEOUTS).click();
        return new MenuPage(driver, swipesClass);
    }

    public void isDark()
    {
//        WaitService.waitVisibilityByXPath(driver, ConfProperties.getProperty("theme"), Configuration.TIMEOUTS);
////        System.out.println(driver.findElementByXPath(ConfProperties.getProperty("theme")).getText());
//        Assert.assertTrue(driver.findElementByXPath(ConfProperties.getProperty("theme")).getText().equals("Dark"));
    }

    public void isLight()
    {
//        WaitService.waitVisibilityByXPath(driver, ConfProperties.getProperty("theme"), Configuration.TIMEOUTS);
////        System.out.println(driver.findElementByXPath(ConfProperties.getProperty("theme")).getText());
//        Assert.assertTrue(driver.findElementByXPath(ConfProperties.getProperty("theme")).getText().equals("Light"));
    }

    public void clickClearCache()
    {
        WaitService.waitVisibilityByXPath(driver, ConfProperties.getProperty("themeLocatorText"), Configuration.TIMEOUTS);
        swipesClass.swipeDown();
        WaitService.waitClickableByXPath(driver, ConfProperties.getProperty("cacheButton"), Configuration.TIMEOUTS).click();
    }

    public void clickClear()
    {
        WaitService.waitClickableById(driver, ConfProperties.getProperty("clearButton"), Configuration.TIMEOUTS).click();
    }
}

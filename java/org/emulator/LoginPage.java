package org.emulator;

import config.ConfProperties;
import config.Configuration;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.support.PageFactory;
import tools.WaitService;

public class LoginPage
{
    private AppiumDriver driver;
    private SwipesClass swipesClass;

    public LoginPage(AppiumDriver driver, SwipesClass swipesClass)
    {
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
        this.driver = driver;
        this.swipesClass = swipesClass;
    }

    public void inputLogin(String login)
    {
        WaitService.waitVisibilityById(driver, ConfProperties.getProperty("loginField"), Configuration.TIMEOUTS);
        driver.findElementById(ConfProperties.getProperty("loginField")).sendKeys(login);
    }

    public void inputPassword(String password)
    {
        WaitService.waitVisibilityById(driver, ConfProperties.getProperty("passwordField"), Configuration.TIMEOUTS);
        driver.findElementById(ConfProperties.getProperty("passwordField")).sendKeys(password);
    }

    public void clickButtonNext()
    {
        WaitService.waitClickableById(driver, ConfProperties.getProperty("nextButton"), Configuration.TIMEOUTS).click();
    }

    public InboxPage clickContinue()
    {
        WaitService.waitClickableById(driver, ConfProperties.getProperty("continueButton"), Configuration.TIMEOUTS).click();
        return new InboxPage(driver, swipesClass);
    }

    public InboxPage login(String login, String password)
    {
        inputLogin(login);
        clickButtonNext();
        inputPassword(password);
        clickButtonNext();
        return clickContinue();
    }
}

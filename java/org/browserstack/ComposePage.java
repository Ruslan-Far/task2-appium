package org.browserstack;

import config.ConfProperties;
import config.Configuration;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.support.PageFactory;
import tools.WaitService;

public class ComposePage {
    private AppiumDriver driver;
    private SwipesClass swipesClass;

    public ComposePage(AppiumDriver androidDriver, SwipesClass swipesClass)
    {
        PageFactory.initElements(new AppiumFieldDecorator(androidDriver), this);
        this.driver = androidDriver;
        this.swipesClass = swipesClass;
    }

    public void clickAllow()
    {
        WaitService.waitClickableById(driver, ConfProperties.getProperty("allowButton"), Configuration.TIMEOUTS).click();
    }

    public void inputEmail(String email)
    {
        WaitService.waitVisibilityById(driver, ConfProperties.getProperty("emailField"), Configuration.TIMEOUTS);
        driver.findElementById(ConfProperties.getProperty("emailField")).sendKeys(email);
    }

    public void attachFile()
    {
        WaitService.waitClickableByAccessibilityId(driver, ConfProperties.getProperty("attachFileButton"), Configuration.TIMEOUTS).click();
    }

    public void clickAllowPicturesVideo()
    {
        WaitService.waitClickableById(driver, ConfProperties.getProperty("allowPicturesVideoButton"), Configuration.TIMEOUTS).click();
    }

    public void clickDisk()
    {
        WaitService.waitClickableByXPath(driver, ConfProperties.getProperty("diskButton"), Configuration.TIMEOUTS).click();
    }

    public void clickFile()
    {
        WaitService.waitClickableByXPath(driver, ConfProperties.getProperty("fileIcon"),Configuration.TIMEOUTS).click();
    }

    public void clickAttach()
    {
        WaitService.waitClickableById(driver, ConfProperties.getProperty("attachButton"), Configuration.TIMEOUTS).click();
    }

    public void clickSend()
    {
        WaitService.waitClickableById(driver, ConfProperties.getProperty("sendButton"), Configuration.TIMEOUTS).click();
    }

    public void processRate()
    {
        WaitService.waitVisibilityById(driver, ConfProperties.getProperty("rate"), Configuration.TIMEOUTS);
        driver.findElementById(ConfProperties.getProperty("touchOutside")).click();
    }

    public InboxPage sendLetter(String email)
    {
        clickAllow();
        inputEmail(email);
        attachFile();
        attachFile();
        clickAllowPicturesVideo();
        clickAllow();
        clickDisk();
        clickFile();
        clickAttach();
        clickSend();
        processRate();

        return new InboxPage(driver, swipesClass);
    }
}

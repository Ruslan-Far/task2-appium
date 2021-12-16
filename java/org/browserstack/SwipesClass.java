package org.browserstack;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import org.openqa.selenium.Dimension;

public class SwipesClass
{
    private AppiumDriver driver;

    public SwipesClass(AppiumDriver driver)
    {
        this.driver = driver;
    }

    public void swipeDown()
    {
        Dimension dimension = driver.manage().window().getSize();

        Double scrollHeightStart = dimension.getHeight() * 0.99;
        int scrollStart = scrollHeightStart.intValue();

        Double scrollHeightEnd = dimension.getHeight() * 0.01;
        int scrollEnd = scrollHeightEnd.intValue();

        new TouchAction(driver).press(0, scrollStart).waitAction(1000).moveTo(0, scrollEnd).release().perform();
    }

    public void swipeUp()
    {
        Dimension dimension = driver.manage().window().getSize();

        Double scrollHeightStart = dimension.getHeight() * 0.1;
        int scrollStart = scrollHeightStart.intValue();

        Double scrollHeightEnd = dimension.getHeight() * 0.9;
        int scrollEnd = scrollHeightEnd.intValue();

        new TouchAction(driver).press(0, scrollStart).waitAction(1000).moveTo(0, scrollEnd).release().perform();
    }

    public void swipeLeft()
    {
        Dimension dimension = driver.manage().window().getSize();

        Double scrollWidthStart = dimension.getWidth() * 0.9;
        int scrollStart = scrollWidthStart.intValue();

        Double scrollWidthEnd = dimension.getWidth() * 0.01;
        int scrollEnd = scrollWidthEnd.intValue();

        Double height = (dimension.getHeight() * 0.5);
        int heightInt = height.intValue();

        new TouchAction(driver).press(scrollStart, heightInt).waitAction(1000).moveTo(scrollEnd, heightInt).release().perform();
    }

    public void swipeRight()
    {
        Dimension dimension = driver.manage().window().getSize();

        Double scrollWidthStart = dimension.getWidth() * 0.01;
        int scrollStart = scrollWidthStart.intValue();

        Double scrollWidthEnd = dimension.getWidth() * 0.9;
        int scrollEnd = scrollWidthEnd.intValue();

        Double height = (dimension.getHeight() * 0.5);
        int heightInt = height.intValue();

        new TouchAction(driver).press(scrollStart, heightInt).waitAction(1000).moveTo(scrollEnd, heightInt).release().perform();
    }
}

package org.browserstack;

import java.net.URL;
import java.net.MalformedURLException;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import org.openqa.selenium.remote.DesiredCapabilities;

public class BrowserStackSample {
    public static void main(String[] args) throws MalformedURLException, InterruptedException {

        DesiredCapabilities caps = new DesiredCapabilities();

        // Set your access credentials
        caps.setCapability("browserstack.user", "bsuser_jx1F9m");
        caps.setCapability("browserstack.key", "vWXVekE7cKb1Yx9QhHvR");

        // Set URL of the application under test
        caps.setCapability("app", "bs://33d14136d5a04f2fe4eadf3b20a9d344eb360457");

        // Specify device and os_version for testing
        caps.setCapability("device", "Google Pixel 3");
        caps.setCapability("os_version", "9.0");

        // Set other BrowserStack capabilities
        caps.setCapability("project", "First Java Project");
        caps.setCapability("build", "browserstack-build-1");
        caps.setCapability("name", "first_test");


        // Initialise the remote Webdriver using BrowserStack remote URL
        // and desired capabilities defined above
        AndroidDriver<AndroidElement> driver = new AndroidDriver<AndroidElement>(
                new URL("http://hub.browserstack.com/wd/hub"), caps);

        // Write your test case statements here

        BaseTest baseTest = new BaseTest();
        baseTest.all(driver);

        // Invoke driver.quit() after the test is done to indicate that the test is completed.
        driver.quit();
    }
}

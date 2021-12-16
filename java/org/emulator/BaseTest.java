package org.emulator;

import config.ConfProperties;
import config.Configuration;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import org.json.JSONObject;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;
import tools.JSONService;

import java.net.URL;
import java.util.concurrent.TimeUnit;

public class BaseTest {

    private DesiredCapabilities capabilities;

    protected static AppiumDriver<MobileElement> driver;

    private SwipesClass swipesClass;
    private LoginPage loginPage;
    private InboxPage inboxPage;
    private ComposePage composePage;
    private MenuPage menuPage;
    private BeginPage beginPage;
    private SentPage sentPage;
    private SettingsPage settingsPage;

    @BeforeSuite
    public void setUp() {
        switch (Configuration.PLATFORM) {
            case "IOS" -> setIOSCapabilities();
            case "Android" -> setAndroidCapabilities();
            default -> {
                throw new RuntimeException("Incorrect platform");
            }
        }
        swipesClass = new SwipesClass(driver);
        resetApp();
    }

    private void setIOSCapabilities() {
        this.capabilities = new DesiredCapabilities();
        JSONObject appiumJson = JSONService.readJsonFromFile(this.getClass().getClassLoader().getResource("capabilities/iosSim.json").getPath());
        JSONObject caps = JSONService.getCapabilities(appiumJson);
        caps.keySet().forEach(keyStr -> this.capabilities.setCapability(keyStr, caps.get(keyStr)));
        try {
            this.driver = new IOSDriver<MobileElement>(new URL(JSONService.getUrl(appiumJson)), this.capabilities);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    private void setAndroidCapabilities() {
        this.capabilities = new DesiredCapabilities();
        JSONObject appiumJson = JSONService.readJsonFromFile(this.getClass().getClassLoader().getResource("capabilities/androidSim.json").getPath());
        JSONObject caps = JSONService.getCapabilities(appiumJson);
        caps.keySet().forEach(keyStr -> this.capabilities.setCapability(keyStr, caps.get(keyStr)));
        try {
            this.driver = new AndroidDriver<MobileElement>(new URL(JSONService.getUrl(appiumJson)), this.capabilities);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @Test
    public void all()
    {
        /** начальные действия перед авторизацией */
        loginPage = new LoginPage(driver, swipesClass);
        inboxPage = loginPage.clickContinue();
        menuPage = inboxPage.openMenu();
        beginPage = menuPage.clickLogout();
        beginPage.isBeginPage();
        loginPage = beginPage.clickYandex();

        /** авторизация */
        inboxPage = loginPage.login(ConfProperties.getProperty("login"), ConfProperties.getProperty("password"));
        inboxPage.isInboxPage();

        /** переход в отправленные сообщения */
        menuPage = inboxPage.openMenu();
        sentPage = menuPage.clickSent();
        sentPage.isSentPage();

        /** открытие/закрытие меню */
        menuPage = sentPage.openMenu();
        menuPage.closeMenu();

        /** переход во входящие сообщения */
        menuPage = sentPage.openMenu();
        inboxPage = menuPage.clickInbox();
        inboxPage.isInboxPage();

        /** отправить сообщение */
        composePage = inboxPage.clickCompose();
        inboxPage = composePage.sendLetter(ConfProperties.getProperty("email"));

        Wait();

        /** удалить сообщение */
        inboxPage.clickDelete();

        /** изменение темы */
        menuPage = inboxPage.openMenu();
        settingsPage = menuPage.clickSettings();
        settingsPage.setThemeDark();
        settingsPage.isDark();
        settingsPage.setThemeLight();
        settingsPage.isLight();

        /** очистка кэша */
        settingsPage.clickClearCache();
        settingsPage.clickClear();
        menuPage = settingsPage.clickBack();

        /** выход из аккаунта */
        menuPage.clickLogout();
    }

    @AfterSuite(alwaysRun = true)
    public void tearDown() {
        if (this.driver != null) {
            driver.quit();
        }
    }

    private void resetApp() {
        driver.resetApp();
    }

    private void Wait()
    {
        try
        {
            Thread.sleep(50000);
        } catch (InterruptedException e)
        {
            System.out.println("3");
        }
    }
}

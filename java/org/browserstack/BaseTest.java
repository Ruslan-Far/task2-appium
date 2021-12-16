package org.browserstack;

import config.ConfProperties;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;

public class BaseTest {

    private SwipesClass swipesClass;
    private LoginPage loginPage;
    private InboxPage inboxPage;
    private ComposePage composePage;
    private MenuPage menuPage;
    private BeginPage beginPage;
    private SentPage sentPage;
    private SettingsPage settingsPage;

    public void all(AndroidDriver<AndroidElement> driver)
    {
        /** начальные действия перед авторизацией */
        swipesClass = new SwipesClass(driver);
        beginPage = new BeginPage(driver, swipesClass);
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

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.URL;
import java.time.Duration;

public class TestSearch {
    private AppiumDriver driver;

    @Before
    public void setUp() throws  Exception {
        DesiredCapabilities capabilities = new DesiredCapabilities();

        capabilities.setCapability("platformName","android");
        capabilities.setCapability("appium:deviceName","avd");
        capabilities.setCapability("appium:platformVersion","12");
        capabilities.setCapability("appium:appPackage","org.wikipedia");
        capabilities.setCapability("appium:appActivity",".main.MainActivity");
        capabilities.setCapability("app","C:\\ProgiIntelliJ_IDEA\\AppiumMobile\\apks\\org.wikipedia.apk");
        driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"),capabilities);
    }

    @After
    public void tearDown(){
        driver.quit();
    }

    @Test
    public void testSearch(){
        waitForElementAndClick(By.xpath(
                        "//*[contains(@text,'Пропустить')]"),
                "Невозможно нажать на поле ввода",
                15 );
        waitForElementAndClick(By.xpath(
                        "//*[contains(@text,'Поиск по Википедии')]"),
                "Невозможно нажать на поле ввода",
                15 );
        waitForElementAndSendKeys(By.xpath("//*[contains(@text,'Поиск по Википедии')]"),
                "Кемеровский государственный университет",
                "Невозможно найти поле ввода",15);
        waitForElementAndClick(By.xpath(
                        "//*[@resource-id='org.wikipedia:id/page_list_item_description' and @text='высшее учебное заведение в Кемерове']"),
                "Невозможно найти 'Кемеровский государственный университет'",
                15);
        WebElement title_element = waitForElementPresent(By.xpath("//*[@text='Кемеровский государственный университет']"),
                "Невозможно найти поле ввода",15);
        String result = title_element.getText();
        Assert.assertEquals("Найдено несовпадение в названии статьи", "Хоббит", result);
    }
    private WebElement waitForElementPresent(By by, String error_message, long timeoutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds));
        wait.withMessage(error_message + "\n");
        return  wait.until(ExpectedConditions.presenceOfElementLocated(by));
    }
    private WebElement waitForElementAndSendKeys(By by, String value, String error_message, long timeoutInSeconds) {
        WebElement element = waitForElementPresent(by, error_message, timeoutInSeconds);
        element.sendKeys(value);
        return  element;
    }
    private WebElement waitForElementAndClick(By by, String error_message, long timeoutInSeconds) {
        WebElement element = waitForElementPresent(by, error_message, timeoutInSeconds);
        element.click();
        return  element;
    }
}

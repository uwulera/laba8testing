import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.URL;
import java.time.Duration;

public class Zadanie {
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
    public void zadanie(){
        //Пропуск обучения
        waitForElementAndClick(By.xpath(
                        "//*[contains(@text,'Пропустить')]"),
                "Невозможно нажать на поле ввода",
                15 );
        //Нажание на поле Поиск по Википедии
        waitForElementAndClick(By.xpath(
                        "//*[contains(@text,'Поиск по Википедии')]"),
                "Невозможно нажать на поле ввода",
                15 );
        //Ввод в поиск
        waitForElementAndSendKeys(By.xpath("//*[contains(@text,'Поиск по Википедии')]"),
                "Хоббит, или Туда и обратно",
                "Невозможно найти поле ввода",15);
        //Нажатие на статью Хоббита
        waitForElementAndClick(By.xpath(
                        "//*[@resource-id='org.wikipedia:id/page_list_item_description' and @text='повесть английского писателя Джона Р. Р. Толкина']"),
                "Невозможно найти 'повесть английского писателя Джона Р. Р. Толкина'",
                15);
        //Нажатие на три точки
        waitForElementAndClick(By.xpath(
                        "//*[@content-desc='Больше настроек']"),
                "Невозможно найти",
                15);
        //Нажатие на кнопку Сохранить
        waitForElementAndClick(By.xpath(
                        "//*[@resource-id='org.wikipedia:id/page_save']"),
                "Невозможно найти кнопку Сохранения",
                60);
        //Нажатие в выпадающем баре Добавить с список
        waitForElementAndClick(By.xpath(
                        "//*[@resource-id='org.wikipedia:id/snackbar_action']"),
                "Невозможно найти",
                15);
        //Нажать на кнопку Создать новый
        //waitForElementAndClick(By.xpath(
          //              "//*[@resource-id='org.wikipedia:id/create_button']"),
           //     "Невозможно нажать на кнопку Создать новый",
             //   15);
        //Ввод названия списка Хоббит
        waitForElementAndSendKeys(By.xpath("//*[contains(@text,'Название этого списка')]"),
                "Хоббит",
                "Невозможно найти поле ввода",15);
        //Нажатие на кнопку ОК
        waitForElementAndClick(By.xpath(
                        "//*[@resource-id='android:id/button1']"),
                "Невозможно нажать на кнопку ОК",
                15);
        //Нажатие на кнопку Посмотреть список
        waitForElementAndClick(By.xpath(
                        "//*[@resource-id='org.wikipedia:id/snackbar_action']"),
                "Невозможно найти",
                15);
        //Нажатие на три точки
        waitForElementAndClick(By.xpath(
                        "//*[@content-desc='Ещё']"),
                "Невозможно найти",
                15);
        //Нажатие на кнопку Удилить из списка
        waitForElementAndClick(By.xpath(
                        "//*[@resource-id='org.wikipedia:id/title' and @text='Удалить список']"),
                "Невозможно найти",
                15);
        //Нажатие на кнопку ОК
        waitForElementAndClear(By.xpath(
                        "//*[@resource-id='android:id/button1']"),
                "Невозможно найти",
                15);
    }

    private WebElement waitForElementAndClear(By by, String error_message, long timeoutInSeconds){
        WebElement element = waitForElementPresent(by,error_message, timeoutInSeconds);
        element.clear();
        return element;
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

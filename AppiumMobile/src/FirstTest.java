import lib.CoreTestCase;
import lib.ui.MainPageObject;
import lib.ui.SearchPageObject;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;


public class FirstTest extends CoreTestCase {
    private MainPageObject MainPageObject;
    protected void setUp() throws  Exception {
        super.setUp();
        MainPageObject = new MainPageObject(driver);

    }
    @Test
    public void testSearchElement(){
        SearchPageObject SearchPageObject = new SearchPageObject(driver);

        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Кемеровский государственный университет");
        SearchPageObject.waitForSearchResult("высшее учебное заведение в Кемерове");

    }
    public void firstTest(){

        MainPageObject.waitForElementAndClick(By.xpath(
                        "//*[contains(@text,'Пропустить')]"),
                "Невозможно нажать на поле ввода",
                15 );
        MainPageObject.waitForElementAndClick(By.xpath(
                        "//*[contains(@text,'Поиск по Википедии')]"),
                "Невозможно нажать на поле ввода",
                15 );
        MainPageObject.waitForElementAndSendKeys(By.xpath("//*[contains(@text,'Поиск по Википедии')]"),
                "Кемеровский государственный университет",
                "Невозможно найти поле ввода",15);
        MainPageObject.waitForElementAndClick(By.xpath(
                        "//*[@resource-id='org.wikipedia:id/page_list_item_description' and @text='высшее учебное заведение в Кемерове']"),
                "Невозможно найти 'Кемеровский государственный университет'",
                15);
        WebElement title_element = MainPageObject.waitForElementPresent(By.xpath("//*[@text='Кемеровский государственный университет']"),
                "Невозможно найти поле ввода",15);
        String result = title_element.getText();
        Assert.assertEquals("Найдено несовпадение в названии статьи", "Хоббит", result);
    }


}

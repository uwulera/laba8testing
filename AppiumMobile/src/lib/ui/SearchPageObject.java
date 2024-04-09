package lib.ui;
import io.appium.java_client.AppiumDriver;
import org.junit.Test;
import org.openqa.selenium.By;

public class SearchPageObject extends MainPageObject{
    public SearchPageObject (AppiumDriver driver){
        super(driver);
    }
    private static final String
    SEARCH_INIT_ELEMENT = "//*[contains(@text,'Поиск по Википедии')]",
    SEARCH_INPUT = "//*[contains(@text,'Поиск')]",
    SEARCH_RESULT = "//*[@resource-id='org.wikipedia:id/page_list_item_description' and @text='{SUBSTRING}']";

    public void initSearchInput(){
        this.waitForElementAndClick(By.xpath(SEARCH_INIT_ELEMENT),
                "Невозможно найти поле ввода",
                15);

        this.waitForElementPresent(By.xpath(SEARCH_INIT_ELEMENT),
                "Невозможно найти поле ввода",15);

    }
    public void typeSearchLine(String search_line){
        this.waitForElementAndSendKeys(By.xpath(SEARCH_INPUT), search_line,
                "Невозможно найти поле ввода", 15);
    }
    private  static  String getResultSearchElement(String substring){
        return  SEARCH_RESULT.replace("{SUBSTRING}",substring);
    }
    public void waitForSearchResult(String substring){
        String search_result_xpath = getResultSearchElement(substring);
        this.waitForElementPresent(By.xpath(search_result_xpath),
                "Невозможно найти" + substring,15);
    }

}

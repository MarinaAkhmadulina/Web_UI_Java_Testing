package ru.geekbrains.homeworks.pages;

import org.assertj.core.api.Assertions;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SearchPage extends BasePage {

    @FindBy(xpath = "//*[@id='header-top-search']")
    private WebElement stringSearchShort;

    @FindBy(xpath = "//*[@placeholder='Поиск книг']")
    private WebElement inputSearch;

    @FindBy(xpath = "//*[text()='Авторы ']")
    private WebElement tabAuthors;

    @FindBy(xpath = "//*[@id='objects-block']//b[text()]")
    private WebElement stringAuthorName;

    @FindBy(xpath = "//*[@class='not-found-text']")
    private WebElement stringNotFoundText;

    public SearchPage(WebDriver webDriver) {
        super(webDriver);
    }

    public SearchPage clickElementSearch() {

        try {
            new WebDriverWait(webDriver, 7).until(ExpectedConditions.elementToBeClickable(stringSearchShort)).click();
        } catch (StaleElementReferenceException e) {
            System.out.println(e.getMessage());
            new WebDriverWait(webDriver, 7).until(ExpectedConditions.elementToBeClickable(stringSearchShort)).click();
        }
        return this;
    }

    public SearchPage search(String searchObject) {

        inputSearch.sendKeys(searchObject);
        inputSearch.sendKeys(Keys.ENTER);
        return this;
    }

    public SearchPage checkPositiveResult() {

        tabAuthors.click();
        JavascriptExecutor jse = (JavascriptExecutor) webDriver;
        jse.executeScript("scroll(0, 250);");

        Assertions.assertThat(stringAuthorName.getText()).isEqualTo("Фёдор Достоевский");
        return this;
    }

    public SearchPage checkNegativeResult() {

        Assertions.assertThat(stringNotFoundText.getText()).contains("По вашему запросу ничего не найдено.");
        return this;
    }
}
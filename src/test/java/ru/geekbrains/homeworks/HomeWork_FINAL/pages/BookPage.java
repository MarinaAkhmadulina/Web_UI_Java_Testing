package ru.geekbrains.homeworks.HomeWork_FINAL.pages;

import io.qameta.allure.Step;
import org.assertj.core.api.Assertions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BookPage extends BasePage {

    @FindBy(xpath = "//*[@placeholder='Поиск книг']")
    private WebElement inputSearch;

    @FindBy(xpath = "//button[text()='Сохранить']")
    private WebElement buttonSave;

    @FindBy(id = "span-userbook-1003980769-0")
    private WebElement buttonAdd;

    @FindBy(xpath = "//div[@data-link='/book/1003980769-idiot-fjodor-dostoevskij']")
    private WebElement elementBook;

    @FindBy(xpath = "//label[text()='Хочу прочитать']")
    private WebElement buttonWantToRead;

    @FindBy(xpath = "//*[@class='bc-menu__status bc-menu__status-lists']")
    private WebElement bookmarkStatus;

    @FindBy(xpath = "//*[@data-view_mode='main']/a")
    private WebElement buttonChange;

    @FindBy(xpath = "//button[text()='Да, удалить']")
    private WebElement buttonDelete;

    public BookPage(WebDriver webDriver) {
        super(webDriver);
    }

   @Step("Присвоить книге статус ХОЧУ ПРОЧИТАТЬ")
    public BookPage assignStatusToBook() {

        new WebDriverWait(webDriver, 5).until(ExpectedConditions.elementToBeClickable(elementBook)).click();
        new WebDriverWait(webDriver, 5).until(ExpectedConditions.elementToBeClickable(buttonAdd)).click();
        buttonWantToRead.click();
        buttonSave.click();
        return this;
    }

    @Step("Проверить статус книги")
    public BookPage checkStatusBook() {

        Assertions.assertThat(bookmarkStatus.getText()).isEqualTo("Хочу прочитать");
        return this;
    }

    @Step("Удалить книгу из списка для чтения")
    public BookPage deleteBook() {

        buttonChange.click();
        buttonWantToRead.click();
        buttonDelete.click();
        buttonSave.click();
        return this;
    }
}
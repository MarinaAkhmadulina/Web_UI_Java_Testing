package ru.geekbrains.homeworks;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

@DisplayName("Добавление книги в список для чтения")
public class AddBookToReadingListTest extends BaseTest {

    @DisplayName("Позитивный тест: книга добавлена")
    @Test
    public void positiveAddBookTest() {

        try {
            new WebDriverWait(webDriver, 5).until(ExpectedConditions
                    .elementToBeClickable(By.xpath("//*[@id='header-top-search']"))).click();
        } catch (StaleElementReferenceException e) {
            System.out.println(e.getMessage());
            new WebDriverWait(webDriver, 5).until(ExpectedConditions
                    .elementToBeClickable(By.xpath("//*[@id='header-top-search']"))).click();
        }

        webDriver.findElement(By.xpath("//*[@placeholder='Поиск книг']")).sendKeys("идиот");
        webDriver.findElement(By.xpath("//*[@placeholder='Поиск книг']")).sendKeys(Keys.ENTER);
        new WebDriverWait(webDriver, 5).until(ExpectedConditions
                .elementToBeClickable(By.xpath("//div[@data-link='/book/1003980769-idiot-fjodor-dostoevskij']"))).click();
        new WebDriverWait(webDriver, 5).until(ExpectedConditions
                .elementToBeClickable(By.id("span-userbook-1003980769-0"))).click();
        webDriver.findElement(By.xpath("//label[text()='Хочу прочитать']")).click();
        webDriver.findElement(By.xpath("//button[text()='Сохранить']")).click();

        Assertions.assertThat(webDriver.findElement(By.xpath("//*[@class='bc-menu__status bc-menu__status-lists']")).getText())
                .isEqualTo("Хочу прочитать");

        webDriver.findElement(By.xpath("//*[@data-view_mode='main']/a")).click();
        webDriver.findElement(By.xpath("//label[text()='Хочу прочитать']")).click();
        webDriver.findElement(By.xpath("//button[text()='Да, удалить']")).click();
        webDriver.findElement(By.xpath("//button[text()='Сохранить']")).click();
    }
}
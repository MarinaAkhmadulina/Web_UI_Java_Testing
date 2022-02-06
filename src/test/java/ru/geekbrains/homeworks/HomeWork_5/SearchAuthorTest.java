package ru.geekbrains.homeworks.HomeWork_5;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.geekbrains.homeworks.HomeWork_FINAL.tests.BaseTest;

@DisplayName("Поиск автора")
public class SearchAuthorTest extends BaseTest {

    @BeforeEach
    void clickElementSearch() {
        try {
            new WebDriverWait(webDriver, 7).until(ExpectedConditions
                    .elementToBeClickable(By.xpath("//*[@id='header-top-search']"))).click();
        } catch (StaleElementReferenceException e) {
            System.out.println(e.getMessage());
            new WebDriverWait(webDriver, 7).until(ExpectedConditions
                    .elementToBeClickable(By.xpath("//*[@id='header-top-search']"))).click();
        }
    }

    @DisplayName("Позитивный тест: Автор найден")
    @Test
    public void positiveSearchAuthorTest() {

        webDriver.findElement(By.xpath("//*[@placeholder='Поиск книг']")).sendKeys("федор достоевский");
        webDriver.findElement(By.xpath("//*[@placeholder='Поиск книг']")).sendKeys(Keys.ENTER);
        webDriver.findElement(By.xpath("//*[text()='Авторы ']")).click();

        JavascriptExecutor jse = (JavascriptExecutor) webDriver;
        jse.executeScript("scroll(0, 250);");

        Assertions.assertThat(webDriver.findElement(By.xpath("//*[@id='objects-block']//b[text()]")).getText())
                .isEqualTo("Фёдор Достоевский");
    }

    @DisplayName("Негативный тест: Автор не найден")
    @Test
    public void negativeSearchAuthorTest() {

        webDriver.findElement(By.xpath("//*[@placeholder='Поиск книг']")).sendKeys("atljh ljcnjtdcrbq");
        webDriver.findElement(By.xpath("//*[@placeholder='Поиск книг']")).sendKeys(Keys.ENTER);

        Assertions.assertThat(webDriver.findElement(By.xpath("//*[@class='not-found-text']"))
                .getText()).contains("По вашему запросу ничего не найдено.");
    }
}
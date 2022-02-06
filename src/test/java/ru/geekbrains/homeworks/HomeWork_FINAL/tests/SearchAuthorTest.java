package ru.geekbrains.homeworks.HomeWork_FINAL.tests;

import io.qameta.allure.Allure;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.geekbrains.homeworks.HomeWork_FINAL.pages.SearchPage;

@DisplayName("Поиск автора")
public class SearchAuthorTest extends BaseAuthorizationTest {

    String author = "федор достоевский";
    String errorAuthor = "atljh ljcnjtdcrbq";

    @DisplayName("Позитивный тест: Автор найден")
    @Test
    public void positiveSearchAuthorTest() {
        Allure.parameter("имя автора ", author);

        new SearchPage(webDriver)
                .clickElementSearch()
                .search(author)
                .checkPositiveResult();
    }

    @DisplayName("Негативный тест: Автор не найден")
    @Test
    public void negativeSearchAuthorTest() {
        Allure.parameter("имя автора ", errorAuthor);

        new SearchPage(webDriver)
                .clickElementSearch()
                .search(errorAuthor)
                .checkNegativeResult();
    }
}
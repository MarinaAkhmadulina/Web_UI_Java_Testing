package ru.geekbrains.homeworks.tests;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.geekbrains.homeworks.pages.SearchPage;

public class SearchAuthorTest extends BaseAuthorizationTest {

    String author = "федор достоевский";
    String errorAuthor = "atljh ljcnjtdcrbq";

    @DisplayName("Позитивный тест: Автор найден")
    @Test
    public void positiveSearchAuthorTest() {

        new SearchPage(webDriver)
                .clickElementSearch()
                .search(author)
                .checkPositiveResult();
    }

    @DisplayName("Негативный тест: Автор не найден")
    @Test
    public void negativeSearchAuthorTest() {

        new SearchPage(webDriver)
                .clickElementSearch()
                .search(errorAuthor)
                .checkNegativeResult();
    }
}
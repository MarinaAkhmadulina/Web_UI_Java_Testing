package ru.geekbrains.homeworks.HomeWork_FINAL.tests;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.geekbrains.homeworks.HomeWork_FINAL.pages.BookPage;
import ru.geekbrains.homeworks.HomeWork_FINAL.pages.SearchPage;

@DisplayName("Добавление книги в список для чтения")
public class AddBookToReadingListTest extends BaseAuthorizationTest {

    String book = "идиот";

    @DisplayName("Позитивный тест: книга добавлена")
    @Test
    public void positiveAddBookTest() {

        new SearchPage(webDriver)
                .clickElementSearch()
                .search(book);
        new BookPage(webDriver)
                .assignStatusToBook()
                .checkStatusBook()
                .deleteBook();
    }
}
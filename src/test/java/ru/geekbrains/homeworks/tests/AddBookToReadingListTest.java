package ru.geekbrains.homeworks.tests;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.geekbrains.homeworks.pages.BookPage;
import ru.geekbrains.homeworks.pages.SearchPage;

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
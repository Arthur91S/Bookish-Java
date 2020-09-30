package org.softwire.training.bookish.models.page;

import org.softwire.training.bookish.models.database.Author;
import org.softwire.training.bookish.models.database.Book;

import java.util.List;
import java.util.Map;

public class BooksPageModel {
    private List<Book> books;
    private Map<Book, List<Author>> booksWithAuthor;

    public Map<Book, List<Author>> getBooksWithAuthor() {
        return booksWithAuthor;
    }

    public void setBooksWithAuthor(Map<Book, List<Author>> booksWithAuthor) {
        this.booksWithAuthor = booksWithAuthor;
    }

    public List<Book> getBooks() {
        return books;
    }
    public void setBooks(List<Book> allBooks) {
        this.books = allBooks;
    }
}

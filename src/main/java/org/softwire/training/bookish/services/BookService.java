package org.softwire.training.bookish.services;

import org.softwire.training.bookish.models.database.Book;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService extends DatabaseService {
    public List<Book> getAllBooks() {
        return jdbi.withHandle(handle ->
                handle.createQuery("SELECT * FROM books")
                        .mapToBean(Book.class)
                        .list()
        );
    }

    public Book getBook(int bookId) {
        return jdbi.withHandle(handle ->
                handle.createQuery("SELECT * FROM books WHERE id = :bookId")
                        .bind("bookId", bookId)
                        .mapToBean(Book.class)
                        .findOnly()
        );
    }

    public int addBook(Book book) {
        return jdbi.withHandle(handle ->
                handle.createUpdate("INSERT INTO books (title, isbn, copies_total) VALUES (:title, :isbn, :copies_total)")
                        .bind("title", book.getTitle())
                        .bind("isbn", book.getIsbn())
                        .bind("copies_total", book.getCopies_total())
                        .executeAndReturnGeneratedKeys("id")
                        .mapTo(Integer.class)
                        .findOnly()
        );
    }

    public void deleteBook(int bookId) {
        jdbi.useHandle(handle ->
                handle.createUpdate("DELETE FROM books WHERE id = :id")
                        .bind("id", bookId)
                        .execute()
        );
    }

    public boolean updateBook(Book book) {
        try {
            jdbi.useHandle(handle ->
                    handle.createUpdate("UPDATE books SET title=:title, isbn=:isbn, copies_total=:copies_total WHERE id=:id")
                            .bind("title", book.getTitle())
                            .bind("isbn", book.getIsbn())
                            .bind("copies_total", book.getCopies_total())
                            .bind("id", book.getId())
                            .execute()
            );
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}

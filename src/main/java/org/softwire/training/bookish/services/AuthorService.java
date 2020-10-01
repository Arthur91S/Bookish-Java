package org.softwire.training.bookish.services;

import org.softwire.training.bookish.models.database.Author;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class AuthorService extends DatabaseService {

    public List<Author> getAllAuthors() {
        return jdbi.withHandle(handle -> handle
                .createQuery("SELECT * FROM authors")
                .mapToBean(Author.class)
                .list()
        );
    }

    public List<Author> getAuthorBooks(int id) {
        return jdbi.withHandle(handle -> handle
                .createQuery("SELECT a.name, a.id FROM books b " +
                        "           INNER JOIN author_books ab " +
                        "           ON b.id = ab.book_id " +
                        "               INNER JOIN authors a " +
                        "               ON a.id = ab.author_id " +
                        "                   WHERE b.id = :id ")
                .bind("id", id)
                .mapToBean(Author.class)
                .list()
        );
    }

    public void addBookAuthors(int bookId, String authorsString) {

        Arrays.asList(authorsString.split(",")).forEach(author -> {
            jdbi.useHandle(handle -> {

                        int authorId;
                        Author existingAuthor = handle.createQuery("SELECT id, name  FROM authors WHERE name = :name LIMIT 1")
                                .bind("name", author)
                                .mapToBean(Author.class)
                                .findOnly();

                        if (existingAuthor == null) {
                            authorId = handle.createUpdate("INSERT INTO authors (name) VALUES (:name)")
                                    .bind("name", author)
                                    .executeAndReturnGeneratedKeys()
                                    .mapTo(Integer.class)
                                    .findOnly();
                        } else {
                            authorId = existingAuthor.getId();
                        }

                        handle.createUpdate("INSERT INTO author_books (book_id, author_id) VALUES (:book_id, :author_id)")
                                .bind("book_id", bookId)
                                .bind("author_id", authorId)
                                .execute();
                    }
            );
        });
    }

    public void deleteAuthor(int authorId) {
        jdbi.useHandle(handle -> handle.createUpdate("DELETE FROM authors WHERE id = :id")
                .bind("id", authorId)
                .execute()
        );
    }
}

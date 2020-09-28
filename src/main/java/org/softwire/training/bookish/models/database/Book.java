package org.softwire.training.bookish.models.database;

import java.beans.ConstructorProperties;

public class Book {

    int id;
    String title;
    String isbn;
    int copies_total;

    @ConstructorProperties({"id", "title", "isbn", "copies_total"})
    public Book(int id, String title, String isbn, int copies_total) {
        this.id = id;
        this.title = title;
        this.isbn = isbn;
        this.copies_total = copies_total;
    }

    public String getTitle() {
        return title;
    }
}

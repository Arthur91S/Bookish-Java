package org.softwire.training.bookish.models.database;

public class Book {

    int id;
    String title;
    String isbn;
    int copies_total;

    public void setId(int id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public void setCopies_total(int copies_total) {
        this.copies_total = copies_total;
    }

    public int getId() {
        return id;
    }

    public String getIsbn() {
        return isbn;
    }

    public int getCopies_total() {
        return copies_total;
    }

    public String getTitle() {
        return title;
    }

     @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", isbn='" + isbn + '\'' +
                ", copies_total=" + copies_total +
                '}';
    }
}

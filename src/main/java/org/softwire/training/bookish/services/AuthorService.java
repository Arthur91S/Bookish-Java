package org.softwire.training.bookish.services;

import org.softwire.training.bookish.models.database.Author;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorService extends DatabaseService {

    public List<Author> getAllAuthors(){
        return jdbi.withHandle(handle->
                handle.createQuery("SELECT * FROM authors")
                .mapToBean(Author.class)
                .list()
        );
    }

    public List<Author> getAuthorBooks(int id){
        return jdbi.withHandle(handle->
                handle.createQuery("SELECT a.name FROM books b LEFT JOIN author_books ab ON b.id = ab.book_id LEFT JOIN authors a ON a.id = ab.author_id WHERE b.id = :id")
                        .bind("id" , id)
                        .mapToBean(Author.class)
                        .list()
        );
    }



}

package org.softwire.training.bookish.controllers;

import org.softwire.training.bookish.models.database.Book;
import org.softwire.training.bookish.models.page.BooksPageModel;
import org.softwire.training.bookish.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

    @Controller
    @RequestMapping("/books")
    public class AuthorController {

        private final AuthorService authorService;

        @Autowired
        public AuthorController(AuthorService authorService) {
            this.authorService = authorService;
        }

        @RequestMapping("")
        ModelAndView viewAuthors() {
            List<Author> allAuthors = authorService.getAllAuthors();

            AuthorsPageModel authorsPageModel = new AuthorsPageModel();
            authorsPageModel.setBooks(allAuthors);

            return new ModelAndView("books","model", booksPageModel);
        }


}

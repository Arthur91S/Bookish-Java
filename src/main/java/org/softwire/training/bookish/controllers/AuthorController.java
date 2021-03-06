package org.softwire.training.bookish.controllers;

import org.softwire.training.bookish.models.database.Author;
import org.softwire.training.bookish.models.database.Book;
import org.softwire.training.bookish.models.page.AuthorPageModel;
import org.softwire.training.bookish.models.page.BooksPageModel;
import org.softwire.training.bookish.services.AuthorService;
import org.softwire.training.bookish.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;

    @Controller
    @RequestMapping("/authors")
    public class AuthorController {

        private final AuthorService authorService;

        @Autowired
        public AuthorController(AuthorService authorService) {
            this.authorService = authorService;
        }

        @RequestMapping("")
        ModelAndView viewAuthors() {
            List<Author> allAuthors = authorService.getAllAuthors();

            AuthorPageModel authorPageModel = new AuthorPageModel();
            authorPageModel.setAuthors(allAuthors);

            return new ModelAndView("authors","model", authorPageModel);
        }

        @RequestMapping("/delete-author")
        RedirectView deleteAuthor(@RequestParam int authorId) {
            authorService.deleteAuthor(authorId);
            return new RedirectView("/authors");
        }


}

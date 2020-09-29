package org.softwire.training.bookish.controllers;

import org.softwire.training.bookish.models.database.Book;
import org.softwire.training.bookish.models.database.Technology;
import org.softwire.training.bookish.models.page.BooksPageModel;
import org.softwire.training.bookish.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;
import java.util.List;

@Controller
@RequestMapping("/books")
public class BooksController {

    private final BookService bookService;

    @Autowired
    public BooksController(BookService bookService) {
        this.bookService = bookService;
    }

    @RequestMapping("")
    ModelAndView viewBooks() {
        List<Book> allBooks = bookService.getAllBooks();

        BooksPageModel booksPageModel = new BooksPageModel();
        booksPageModel.setBooks(allBooks);

        return new ModelAndView("books","model", booksPageModel);
    }

    @RequestMapping("/add-book")
    RedirectView addBook(@ModelAttribute Book book) {

        bookService.addBook(book);

        return new RedirectView("/books");
    }

    @RequestMapping("/delete-book")
    RedirectView deleteBook(@RequestParam int bookId) {
        bookService.deleteBook(bookId);
        return new RedirectView("/books");

    }

    @RequestMapping("/edit-book")
    RedirectView editBook(@RequestParam int bookId) {

        bookService.getBook(bookId);

        return new RedirectView("/edit-book/bookId");

    }










}

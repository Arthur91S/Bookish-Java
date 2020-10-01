package org.softwire.training.bookish.controllers;

import org.softwire.training.bookish.models.database.Author;
import org.softwire.training.bookish.models.database.Book;
import org.softwire.training.bookish.models.page.BooksPageModel;
import org.softwire.training.bookish.services.AuthorService;
import org.softwire.training.bookish.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/books")
public class BooksController {

    private final BookService bookService;
    private final AuthorService authorService;

    @Autowired
    public BooksController(BookService bookService, AuthorService authorService) {
        this.bookService = bookService;
        this.authorService = authorService;
    }

    @RequestMapping("")
    ModelAndView viewBooks() {
        List<Book> allBooks = bookService.getAllBooks();

        Map<Book, List<Author>> bookWithAuthor = new LinkedHashMap<>();
        for (Book book : allBooks) {
            List<Author> authors = authorService.getAuthorBooks(book.getId());
            if (authors != null) {
                bookWithAuthor.put(book, authors);
            }
        }

        BooksPageModel booksPageModel = new BooksPageModel();
        booksPageModel.setBooks(allBooks);
        booksPageModel.setBooksWithAuthor(bookWithAuthor);

        return new ModelAndView("books", "model", booksPageModel);
    }

    @RequestMapping("/add-book")
    RedirectView addBook(@ModelAttribute Book book, @RequestParam String authors) {

        int bookId = bookService.addBook(book);

        if (!authors.isEmpty()) {
            authorService.addBookAuthors(bookId, authors);
        }

        return new RedirectView("/books");
    }

    @RequestMapping("/delete-book")
    RedirectView deleteBook(@RequestParam int bookId) {
        bookService.deleteBook(bookId);
        return new RedirectView("/books");
    }

    @RequestMapping("/edit-book")
    ModelAndView editBook(@RequestParam int bookId) {

        Book book = bookService.getBook(bookId);

        Map<Book, List<Author>> bookWithAuthor = new LinkedHashMap<>();
        List<Author> bookWithAuthors = authorService.getAuthorBooks(bookId);
        if (bookWithAuthors != null) {
            bookWithAuthor.put(book, bookWithAuthors);
        }

        BooksPageModel booksPageModel = new BooksPageModel();
        booksPageModel.setBooksWithAuthor(bookWithAuthor);

        booksPageModel.getBooksWithAuthor().get(0);
        return new ModelAndView("edit_book", "model", booksPageModel);
    }

    @RequestMapping("/update-book")
    RedirectView updateBook(@ModelAttribute Book book, RedirectAttributes attr) {

        if (bookService.updateBook(book)) {
            attr.addFlashAttribute("success", "Excellent, everything went just fine.");
        } else {
            attr.addFlashAttribute("error", "Ops, something went wrong.");
        }
        return new RedirectView("/books");
    }


}

package kk.bookstore.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import kk.bookstore.model.Book;
import kk.bookstore.model.BookRepository;

@Controller
public class BookController {
private static final Logger log = LoggerFactory.getLogger(BookController.class);

    private final BookRepository bookRepository;

    public BookController(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }


    @GetMapping(value={"/", "/books"})
    public String getBooks(Model model) {
        model.addAttribute("books", bookRepository.findAll());
        return "/books";
    }

    @GetMapping("/addBook")
    public String addBook(Model model) {

        model.addAttribute("book", new Book());
        return "/addBook";
    }

    @PostMapping("/saveBook")
    public String addBook(Book book) {
        log.info("Kirja " + book.toString());
        bookRepository.save(book);
        return "redirect:/books";
    }

    @GetMapping("/deleteBook/{id}")
    public String deleteBook(@PathVariable("id") Long bookId, Model model) {
        log.info("Kirja id " + bookId);
        bookRepository.deleteById(bookId);
        return "redirect:/books";
    }

    @GetMapping("/editBook/{id}")
    public String editBook(@PathVariable("id") Long bookId, Model model) {
        log.info("Kirja id " + bookId);
        model.addAttribute("book", bookRepository.findById(bookId));
        return "/editBook";
    }

    @PostMapping("/saveEditedBook")
    public String saveEditedBook(@ModelAttribute Book book, Model model) {
        log.info("Kirja " + book.toString());
        bookRepository.save(book);
        return "redirect:/books";
    }

}
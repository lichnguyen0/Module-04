package com.example.md4b9bt2.controller;




import com.example.md4b9bt2.model.Book;
import com.example.md4b9bt2.repository.BookRepository;
import com.example.md4b9bt2.service.BorrowService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/books")
public class BookController {

    private final BookRepository bookRepository;
    private final BorrowService borrowService;

    public BookController(BookRepository bookRepository, BorrowService borrowService) {
        this.bookRepository = bookRepository;
        this.borrowService = borrowService;
    }

    @GetMapping
    public String listBooks(Model model) {
        model.addAttribute("books", bookRepository.findAll());
        return "books";
    }

    @GetMapping("/{id}")
    public String detailBook(@PathVariable Long id, Model model) {
        Book book = bookRepository.findById(id).orElse(null);
        model.addAttribute("book", book);
        return "book-detail";
    }

    @PostMapping("/{id}/borrow")
    public String borrowBook(@PathVariable Long id, Model model) {
        String code = borrowService.borrowBook(id);
        model.addAttribute("code", code);
        model.addAttribute("bookId", id);
        return "borrow-success";
    }

    @GetMapping("/return")
    public String returnForm() {
        return "return-book";
    }

    @PostMapping("/return")
    public String doReturn(@RequestParam("code") String code, Model model) {
        borrowService.returnBook(code);
        model.addAttribute("message", "Trả sách thành công!");
        return "return-book";
    }
}

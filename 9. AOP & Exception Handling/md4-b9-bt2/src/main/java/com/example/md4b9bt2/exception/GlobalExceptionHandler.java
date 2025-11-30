package com.example.md4b9bt2.exception;



import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BookNotAvailableException.class)
    public String handleBookNotAvailable(BookNotAvailableException ex, Model model) {
        model.addAttribute("message", ex.getMessage());
        return "error";
    }

    @ExceptionHandler(InvalidBorrowCodeException.class)
    public String handleInvalidBorrowCode(InvalidBorrowCodeException ex, Model model) {
        model.addAttribute("message", ex.getMessage());
        return "error";
    }

    @ExceptionHandler(Exception.class)
    public String handleOther(Exception ex, Model model) {
        model.addAttribute("message", "Có lỗi xảy ra: " + ex.getMessage());
        return "error";
    }
}



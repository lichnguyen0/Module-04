package com.example.md4b9bt2.service;

import com.example.md4b9bt2.exception.BookNotAvailableException;
import com.example.md4b9bt2.exception.InvalidBorrowCodeException;
import com.example.md4b9bt2.model.Book;
import com.example.md4b9bt2.repository.BookRepository;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class BorrowService {

    private final BookRepository bookRepository;
    // Lưu mã mượn -> bookId
    private final Map<String, Long> borrowMap = new ConcurrentHashMap<>();
    private final Random random = new Random();

    public BorrowService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    /**
     * Mượn sách
     * @param bookId id sách
     * @return mã mượn 5 chữ số
     */
    public synchronized String borrowBook(Long bookId) {
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new BookNotAvailableException("Không tìm thấy sách."));
        if (book.getQuantity() <= 0) {
            throw new BookNotAvailableException("Sách đã hết, không thể mượn.");
        }

        // Giảm số lượng
        book.setQuantity(book.getQuantity() - 1);
        bookRepository.save(book);

        // Sinh mã mượn và lưu vào map
        String code = generateCode();
        borrowMap.put(code, bookId);
        return code;
    }

    /**
     * Trả sách theo mã mượn
     * @param code mã mượn
     */
    public synchronized void returnBook(String code) {
        Long bookId = borrowMap.get(code);
        if (bookId == null) {
            throw new InvalidBorrowCodeException("Mã mượn không hợp lệ.");
        }

        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new InvalidBorrowCodeException("Mã mượn liên kết với sách không tồn tại."));

        // Tăng số lượng sách
        book.setQuantity(book.getQuantity() + 1);
        bookRepository.save(book);

        // Xóa mã mượn khỏi map
        borrowMap.remove(code);
    }

    /**
     * Kiểm tra xem mã mượn có tồn tại
     * @param code mã mượn
     * @return true nếu tồn tại
     */
    public boolean existsBorrowCode(String code) {
        return borrowMap.containsKey(code);
    }

    /**
     * Sinh mã mượn ngẫu nhiên 5 chữ số
     * @return mã mượn dạng "00000" - "99999"
     */
    private String generateCode() {
        int n = random.nextInt(100_000);
        return String.format("%05d", n);
    }
}

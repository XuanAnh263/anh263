package com.example.goodread.controller.admin;

import com.example.goodread.exception.BadRequestException;
import com.example.goodread.model.request.BookRequest;
import com.example.goodread.model.request.BookSearchRequest;
import com.example.goodread.model.response.CommonResponse;
import com.example.goodread.service.BookService;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping()
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class BookController {
    BookService bookService;

    @GetMapping("/admin/books")
    public String searchBook(Model model, BookSearchRequest request) {
        CommonResponse<?> commonResponse = bookService.searchBook(request);
        model.addAttribute("pageBookInfo", commonResponse);
        model.addAttribute("currentPage", request.getPageIndex());
        model.addAttribute("pageSize", request.getPageSize());
        return "admin/book/book-list";
    }

    @GetMapping("/admin/add-book")
    public String addBook(Model model) {
        model.addAttribute("categoryList", bookService.getAllCategories());
        return "admin/book/book-create";
    }

    @GetMapping("/admin/books/{bookId}")
    public String editBook(Model model, @PathVariable Long bookId) {
        model.addAttribute("bookDetails", bookService.findBookByBookId(bookId));
        model.addAttribute("categoryList", bookService.getAllCategories());
        return "admin/book/book-edit";
    }

    // API
    @PostMapping("/api/v1/admin/book")
    public ResponseEntity<?> createBook(@RequestBody @Valid BookRequest newBook) {
        bookService.createBook(newBook);
        return new ResponseEntity<>(null, HttpStatus.CREATED);
    }

    @PutMapping("/api/v1/admin/book")
    public ResponseEntity<?> updateBook(@RequestBody @Valid BookRequest updateBookRequest) {
        bookService.updateBook(updateBookRequest);
        return new ResponseEntity<>(null, HttpStatus.OK);
    }

    @DeleteMapping("/api/v1/admin/book/{bookId}")
    public ResponseEntity<?> deleteBook(@PathVariable Long bookId) throws BadRequestException {
        bookService.deleteBook(bookId);
        return new ResponseEntity<>(null, HttpStatus.OK);
    }
}

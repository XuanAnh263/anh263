package com.example.goodread.controller;


import com.example.goodread.exception.OtpExpiredException;
import com.example.goodread.model.request.BookSearchRequest;
import com.example.goodread.model.response.BookResponse;
import com.example.goodread.model.response.CommonResponse;
import com.example.goodread.model.response.ReviewResponse;
import com.example.goodread.model.response.UserResponse;
import com.example.goodread.security.SecurityUtils;
import com.example.goodread.service.BookService;
import com.example.goodread.service.UserService;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;


@Controller

@FieldDefaults(level = AccessLevel.PRIVATE)
public class WebController {
    private final UserService userService;
    private final BookService bookService;

    public WebController(UserService userService, BookService bookService) {
        this.userService = userService;
        this.bookService = bookService;
    }

    @GetMapping
    public String getHomePage(Model model) {
        Optional<Long> optionalId = SecurityUtils.getCurrentUserLoginId();
        CommonResponse<?> bookResponses;
        if (optionalId.isEmpty()) {
            bookResponses = bookService.findRandomBooks(null);
        } else {
            bookResponses = bookService.findRandomBooks(optionalId.get());
        }
        model.addAttribute("randomBookList", bookResponses);
        return "user/index";
    }

    @GetMapping("/login")
    public String getLoginPage(Model model) {
        return "account/login";
    }

    @GetMapping("/signup")
    public String getSignUpPage(Model model) {
        return "account/signup";
    }

    @GetMapping("/home")
    public String getPage(Model model) {
        return "user/home";
    }

    @GetMapping("/actived")
    public String getActivePage(@RequestParam("otpCode") String otpCode) {
        userService.activeAccount(otpCode);
        return "user/active-account";
    }

    @GetMapping("/check-otp-reset")
    public String getResetPasswordPage(@RequestParam("otpCode") String otpCode) {
        try {
            userService.checkOtp(otpCode);
            return "account/reset-password";
        } catch (OtpExpiredException e) {
            return "account/login";
        }
    }

    @GetMapping("/books")
    public String searchBook(Model model, @Valid BookSearchRequest request) {
        Optional<Long> optionalId = SecurityUtils.getCurrentUserLoginId();
        CommonResponse<?> bookSearchData;
        if (optionalId.isEmpty()) {
            bookSearchData = bookService.searchBookAuthen(request, null);
        } else {
            bookSearchData = bookService.searchBookAuthen(request, optionalId.get());
        }
        model.addAttribute("bookSearchData", bookSearchData);
        model.addAttribute("currentPage", request.getPageIndex());
        return "user/book-list";
    }

    @GetMapping("/books/{bookId}")
    public String getBookDetail(Model model, @PathVariable Long bookId) {
        Optional<Long> optionalId = SecurityUtils.getCurrentUserLoginId();
        BookResponse bookResponse;
        List<ReviewResponse> reviewResponses;
        ReviewResponse myReview = new ReviewResponse();

        if (optionalId.isPresent()) {
            UserResponse user = userService.findUserById(optionalId.get());
            bookResponse = bookService.findBookByBookId(bookId, optionalId.get());
            reviewResponses = bookService.getAllReviews(bookId, optionalId.get());

            for (ReviewResponse rv : reviewResponses) {
                if (rv.getCurrentUserId().equals(rv.getUserReviewId())) {

                    myReview = rv;
                }
            }
            myReview.setAvatar(user.getAvatar());
            reviewResponses.removeIf(rv -> rv.getUserReviewId().equals(optionalId.get()));
            reviewResponses.removeIf(rv -> rv.getContent() == null || rv.getContent().trim().isEmpty());
        } else {
            bookResponse = bookService.findBookByBookId(bookId, null);
            reviewResponses = bookService.getAllReviews(bookId, null);
            myReview = null;
        }

        model.addAttribute("bookDetail", bookResponse);
        model.addAttribute("reviewsList", reviewResponses);
        model.addAttribute("myReview", myReview);
        return "user/book-detail";
    }
}

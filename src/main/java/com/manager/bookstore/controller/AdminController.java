package com.manager.bookstore.controller;


import com.manager.bookstore.dto.BookDto;
import com.manager.bookstore.entity.Book;
import com.manager.bookstore.repository.BookRepo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Tag(name = "admin manager")
@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private BookRepo bookRepo;




    @Operation(
            summary = "добавить в ассортимент новую книгу",
            description = "метод добавляющий в ассортимент новую книгу. " +
                    "Доступ к методу имеет только пользователь с доступом админ"
    )
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @PostMapping("/addbook")
    public void addbook(@RequestBody BookDto bookDto) {
        Book book = new Book();
        book.setNameBook(bookDto.getNameBook());
        book.setPriceBook(bookDto.getPriceBook());
        bookRepo.save(book);
    }

    @Operation(
            summary = "удалить из ассортимента книгу",
            description = "метод удаляющий из ассортимента книгу по id/ISBN. " +
                    "Доступ к методу имеет только пользователь с доступом админ"
    )
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @DeleteMapping("/delete")
    public void deleteBooks(@RequestParam int id) {
        bookRepo.deleteById(id);
    }


    @Operation(
            summary = "вывести на экран весь ассортимент магазина",
            description = "метод вызывающий весь ассортимент из магазина. " +
                    "Доступ к методу имеет только пользователь с доступом админ"
    )
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @GetMapping("/allBook")
    public List<Book> allBooks() {
        return bookRepo.findAll();
    }



}


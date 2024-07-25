package com.manager.bookstore.controller;

import com.manager.bookstore.entity.Book;
import com.manager.bookstore.entity.Client;
import com.manager.bookstore.repository.BookRepo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@Tag(name = "Магазин Книг")
@RestController
@RequestMapping("/user")
public class UserController {


    @Autowired
    private BookRepo bookRepo;

    List<Client> clientList = new LinkedList<>();

    private static int masive1 = 0;
    private List<Integer> sumMasive = new ArrayList<>();



    private static int id = 0;
    private static int money  = 0;
    private static int messageUpdate = 0;


    @Operation(
            summary = "пополнить баланс",
            description = "метод пополняющий баланс"
    )
    @PostMapping("/money")
    public String butjet(int money){
        this.money = this.money + money;
        return "вы пополнили свой баланc.\nНа вашем счету: " + this.money;
    }



    @Operation(
            summary = "ассортимент магазина",
            description = "метод вызывающий весь ассортимент книг из магазина."
    )
    @PreAuthorize("hasAuthority('ROLE_USER')")
    @GetMapping("/allBooks")
    public List<Book> allBook() {
        return bookRepo.findAll();
    }


    @Operation(
            summary = "купить книгу",
            description = "Метод, который переносит книгу из ассортимента магазина в корзину клиента," +
                    " при этом клиент должен указать количество приобретаемых книг."
    )
    @PreAuthorize("hasAuthority('ROLE_USER')")
    @GetMapping("/shop")
    public String shop(@RequestParam int id, int quantityBook) {

        Book book = bookRepo.findById(id).orElseThrow();
        String bookstr = book.toString();
        int i = quantityBook * book.getPriceBook();
        if (quantityBook <= 0){
            return "вы указали недопустимое количество книг: " + quantityBook;
        } else if (i< money){

            Client client = new Client();
            client.setId(++this.id);
            client.setIsbn(book.getId());
            client.setNamebook(book.getNameBook());
            client.setQuantityBook(quantityBook);
            client.setPriceBook(i);
            clientList.add(client);

            sumMasive.add(masive1, i);
            masive1++;

            money -= i;
            return "КНИГА ДОБАВЛЕНА В КОРЗИНУ\n" + bookstr + "\nколичество книг: " + quantityBook +
                    "\nУ вас осталось денег: " + money;

        } else {
            return "НЕДОСТАТОЧНО ДЕНЕГ!!!книга НЕ добавлена в корзину. У вас осталось денег: " + money;
        }


    }

    @Operation(
            summary = "показать мою корзину",
            description = "Метод, отображающий все книги, находящиеся в корзине клиента."
    )
    @PreAuthorize("hasAuthority('ROLE_USER')")
    @GetMapping("/orderList")
    public List<Client> orderLIst() {

        ++messageUpdate;

        int sum = 0;
        Client client = new Client();

        for (int i = 0; i < sumMasive.size(); ++i){
            sum += sumMasive.get(i);
        }

        client.setNamebook("общая сумма заказа: "  + sum + ". Количество обновлений данного сообщения: " + messageUpdate);
        clientList.add(client);
        return clientList;
    }

    @Operation(
            summary = "удалить из корзины",
            description = "Метод, позволяющий удалить определенные книги из корзины " +
                    "по указанному идентификатору в корзине"
    )
    @PreAuthorize("hasAuthority('ROLE_USER')")
    @DeleteMapping("/removeFromCart")
    public void removeFromCart(int id) {
        int id_ = id - 1;
        clientList.remove(id_);
        sumMasive.remove(id_);
        masive1--;
    }


}

package com.ekinyaldiz.project.library.menu.admin.book;

import com.ekinyaldiz.project.library.menu.generic.ConsoleReader;
import com.ekinyaldiz.project.library.menu.generic.Menu;
import com.ekinyaldiz.project.library.menu.generic.MenuName;

import com.ekinyaldiz.project.library.repo.book.Book;
import com.ekinyaldiz.project.library.service.BookService;

import com.ekinyaldiz.project.library.system.SystemContext;

import java.util.Optional;

import static com.ekinyaldiz.project.library.menu.admin.book.ViewBookMenu.BOOK_ID;
import static com.ekinyaldiz.project.library.menu.admin.user.ViewUserMenu.USER_ID;

public class DeleteBookMenu extends Menu {


    public DeleteBookMenu(BookService bookService) {
        super("Delete User", bookService);

    }



    @Override
    public MenuName execute() {
        printTitle();
       String bookId = SystemContext.getProperty(BOOK_ID);
       Optional<Book> bookOptional= getBookService().getById(Integer.valueOf(bookId));
       Book book= bookOptional.orElseThrow();

         getBookService().deleteBookByBookId(book.id());

        System.out.printf("Book %s is successfully deleted. %n", book.id());

        println("Press ENTER to continue");

        SystemContext.removeProperty(BOOK_ID);

        ConsoleReader.readLine();
        return MenuName.ADMIN_MAIN_MENU;


      }



}

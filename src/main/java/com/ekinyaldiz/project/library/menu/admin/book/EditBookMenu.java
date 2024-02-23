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

public class EditBookMenu extends Menu {


    public EditBookMenu(BookService bookService) {
        super("Edit book", bookService);

    }



    @Override
    public MenuName execute() {
        printTitle();
       String bookId = SystemContext.getProperty(BOOK_ID);
       Optional<Book> bookOptional= getBookService().getById(Integer.valueOf(bookId));
       Book book= bookOptional.orElseThrow();


         String title = printfAndGet("Title", book.title());
         Integer year= Integer.valueOf(printfAndGet("Year", book.year().toString()));
         String author = printfAndGet("Author", book.author());


         Book updatedBook = new Book(book.id(), title, year, author
                 );

         getBookService().updateBook(updatedBook);

        System.out.println();
        println("Book is successfully updated.");
        println("Press ENTER to continue");
        SystemContext.removeProperty(BOOK_ID);
        ConsoleReader.readLine();
        return MenuName.ADMIN_MAIN_MENU;


      }



}

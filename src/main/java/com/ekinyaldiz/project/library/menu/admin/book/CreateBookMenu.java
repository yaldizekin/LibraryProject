package com.ekinyaldiz.project.library.menu.admin.book;

import com.ekinyaldiz.project.library.menu.generic.ConsoleReader;
import com.ekinyaldiz.project.library.menu.generic.Menu;
import com.ekinyaldiz.project.library.menu.generic.MenuName;
import com.ekinyaldiz.project.library.repo.book.Book;

import com.ekinyaldiz.project.library.service.BookService;
import com.ekinyaldiz.project.library.service.UserService;

public class CreateBookMenu extends Menu {


    public CreateBookMenu(BookService bookService) {
        super("Create a Book", bookService);

    }



    @Override
    public MenuName execute() {
        printTitle();



        String title = printAndGet("Title");
        Integer year= Integer.valueOf(printAndGet("Year"));
        String author = printAndGet("Author");


        Book newBook = new Book(null, title, year, author
        );


        getBookService().createBook(newBook);

        System.out.println();
        println("Book is successfully created.");
        println("Press ENTER to continue");

        ConsoleReader.readLine();
        return MenuName.ADMIN_MAIN_MENU;


      }


}

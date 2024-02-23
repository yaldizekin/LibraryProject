package com.ekinyaldiz.project.library.menu.admin.book;

import com.ekinyaldiz.project.library.menu.generic.Menu;
import com.ekinyaldiz.project.library.menu.generic.MenuName;
import com.ekinyaldiz.project.library.menu.generic.MenuOption;

import com.ekinyaldiz.project.library.repo.book.Book;
import com.ekinyaldiz.project.library.service.BookService;

import com.ekinyaldiz.project.library.system.SystemContext;

import java.util.List;
import java.util.Optional;


public class ViewBookMenu extends Menu {

    public static final String BOOK_ID = "BOOK_ID";
    public ViewBookMenu(BookService bookService) {
        super("View Book", bookService);
        setOptions(List.of(
                new MenuOption("E", " Edit book" ,MenuName.ADMIN_EDIT_BOOK),

                new MenuOption("D",  "Delete book" ,MenuName.ADMIN_DELETE_BOOK),

                new MenuOption("M", "Back to main menu", MenuName.ADMIN_MAIN_MENU)));
    }



    @Override
    public MenuName execute() {
        printTitle();
       String bookId = SystemContext.getProperty(BOOK_ID);
       Optional<Book> bookOptional= getBookService().getById(Integer.valueOf(bookId));
       Book book= bookOptional.orElseThrow();

       printfItem( "ID", book.id().toString());
       printfItem("Title", book.title());
       printfItem("Year", book.year().toString());
       printfItem("Author", book.author());


        System.out.println();
        printOptions();

        return run();


      }


}

package com.ekinyaldiz.project.library.menu.admin.book;

import com.ekinyaldiz.project.library.menu.generic.Menu;
import com.ekinyaldiz.project.library.menu.generic.MenuName;

import com.ekinyaldiz.project.library.repo.book.Book;
import com.ekinyaldiz.project.library.service.BookService;

import com.ekinyaldiz.project.library.system.SystemContext;

import java.util.List;

import static com.ekinyaldiz.project.library.menu.admin.book.ViewBookMenu.BOOK_ID;
import static com.ekinyaldiz.project.library.menu.admin.user.ViewUserMenu.USER_ID;

public class SearchBooksMenu extends Menu {
    public SearchBooksMenu(BookService bookService) {
        super("Search books", bookService);
    }

    @Override
    public MenuName execute() {
        printTitle();

        String searchTerm=printAndGet("Enter search term: ");

      List<Book> books= getBookService().searchBooks(searchTerm);


      if (books.isEmpty()){
          println("No users found, please try again.");
         return execute();
      }
      else {
          System.out.printf("%-5s|%-20s|%-5s|%-20s %n" , "ID", "Title", "Year", "Author" );

          for (Book c :books){
              System.out.printf("%-5.5s|%-20.20s|%-5.5s|%-20.20s %n" ,
                      c.id(), c.title(), c.year(), c.author());

          }

         String choice = printAndGet("Enter book ID to see or 'X' to go back to main menu:");

          if ("X".equalsIgnoreCase(choice)){
              return  MenuName.ADMIN_MAIN_MENU;
          } else {
               boolean idExists = books.stream().anyMatch(c -> c.id().toString().equals(choice));

               if (idExists){
                   SystemContext.addProperty(BOOK_ID, choice);
                   return MenuName.ADMIN_VIEW_BOOK;
               } else{
                   return  execute();
               }

          }

      }



    }

}

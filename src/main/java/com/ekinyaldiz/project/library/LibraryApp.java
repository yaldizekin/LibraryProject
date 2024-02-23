package com.ekinyaldiz.project.library;

import com.ekinyaldiz.project.library.menu.admin.AdminMainMenu;
import com.ekinyaldiz.project.library.menu.admin.book.*;
import com.ekinyaldiz.project.library.menu.admin.user.*;
import com.ekinyaldiz.project.library.menu.customer.UserMainMenu;
import com.ekinyaldiz.project.library.menu.customer.profile.MyProfileMenu;
import com.ekinyaldiz.project.library.menu.customer.profile.SeeMyProfileMenu;
import com.ekinyaldiz.project.library.menu.customer.profile.UpdateMyProfileMenu;
import com.ekinyaldiz.project.library.menu.generic.Menu;
import com.ekinyaldiz.project.library.menu.generic.MenuName;
import com.ekinyaldiz.project.library.menu.login.AdminLoginMenu;
import com.ekinyaldiz.project.library.menu.login.MainLoginMenu;
import com.ekinyaldiz.project.library.menu.login.UserLoginMenu;
import com.ekinyaldiz.project.library.repo.book.BookRepository;
import com.ekinyaldiz.project.library.repo.user.AdminUser;
import com.ekinyaldiz.project.library.repo.user.Customer;
import com.ekinyaldiz.project.library.repo.user.UserRepository;
import com.ekinyaldiz.project.library.service.*;

public class LibraryApp {

    public static void main(String[] args) {
        UserRepository userRepository = new UserRepository();
        BookRepository bookRepository = new BookRepository();
        createDummyUsers(userRepository);
        // createDummyBooks(bookRepository);
        MailService mailService = new MailServiceImpl();
        UserService userService = new UserServiceImpl(userRepository, mailService);
        BookService bookService = new BookServiceImpl(bookRepository);

        Menu mainLoginMenu = new MainLoginMenu();
        UserLoginMenu userLoginMenu = new UserLoginMenu(userService);
        AdminMainMenu adminMainMenu = new AdminMainMenu();
        AdminLoginMenu adminLoginMenu = new AdminLoginMenu(userService);
        SearchUsersMenu searchUsersMenu = new SearchUsersMenu(userService);
        ViewUserMenu viewUserMenu = new ViewUserMenu(userService);
        EditUserMenu editUserMenu = new EditUserMenu(userService);
        CreateUserMenu createUserMenu = new CreateUserMenu(userService);
        DeleteUserMenu deleteUserMenu = new DeleteUserMenu(userService);

        // -- book menus
        SearchBooksMenu searchBooksMenu = new SearchBooksMenu(bookService);
        ViewBookMenu viewBookMenu = new ViewBookMenu(bookService);
        EditBookMenu editBookMenu = new EditBookMenu(bookService);
        CreateBookMenu createBookMenu = new CreateBookMenu(bookService);
        DeleteBookMenu deleteBookMenu = new DeleteBookMenu(bookService);

        // -- user menus
        UserMainMenu userMainMenu = new UserMainMenu();
        MyProfileMenu myProfileMenu = new MyProfileMenu();
        UpdateMyProfileMenu updateMyProfileMenu = new UpdateMyProfileMenu(userService);
        SeeMyProfileMenu seeMyProfileMenu = new SeeMyProfileMenu(userService);

        /* -- borrow menu
         BorrowBooksMenu borrowBooksMenu = new BorrowBooksMenu(bookService);
         ReturnBooksMenu returnBooksMenu = new ReturnBooksMenu(bookService); */

        MenuName menuName = MenuName.MAIN_LOGIN;

        while (true) {
            menuName = switch (menuName) {
                case USER_LOGIN -> userLoginMenu.execute();
                case ADMIN_LOGIN -> adminLoginMenu.execute();
                case ADMIN_MAIN_MENU -> adminMainMenu.execute();
                case ADMIN_SEARCH_USERS -> searchUsersMenu.execute();
                case ADMIN_VIEW_USER -> viewUserMenu.execute();
                case ADMIN_EDIT_USER -> editUserMenu.execute();
                case ADMIN_CREATE_USER -> createUserMenu.execute();
                case ADMIN_DELETE_USER -> deleteUserMenu.execute();
                case ADMIN_SEARCH_BOOKS -> searchBooksMenu.execute();
                case ADMIN_VIEW_BOOK -> viewBookMenu.execute();
                case ADMIN_EDIT_BOOK -> editBookMenu.execute();
                case ADMIN_CREATE_BOOK -> createBookMenu.execute();
                case ADMIN_DELETE_BOOK -> deleteBookMenu.execute();
                case USER_MAIN_MENU -> userMainMenu.execute();
                case MY_PROFILE -> myProfileMenu.execute();
                case UPDATE_MY_PROFILE -> updateMyProfileMenu.execute();
                case SEE_MY_PROFILE -> seeMyProfileMenu.execute();
                //case BORROW_BOOK -> borrowBooksMenu.execute();
                //case RETURN_BOOK -> returnBooksMenu.execute();
                default -> mainLoginMenu.execute();
            };
        }
    }

    private static void createDummyUsers(UserRepository userRepository) {
        userRepository.createUser(
                new Customer(1, "tester1", "12345",
                        "TesterF", "testerL", "Addr", "P1", "Istanbul", "tester@tester.com"));
        userRepository.createUser(
                new Customer(2, "tester2", "12345",
                        "Baris", "Dere", "kalverstraat 1", "1234GF", "Amsterdam", "t@t.com"));
        userRepository.createUser(
                new AdminUser(10001, "admin1", "12345"));
    }

   /* private static void createDummyBooks(BookRepository bookRepository) {
        bookRepository.createBook(new Book(1, "Alice Munro: Selected Stories", 1996, "Alice Munro"));
        bookRepository.createBook(new Book(2, "Through the Looking-Glass", 1871, "Lewis Carroll"));
    }*/
}

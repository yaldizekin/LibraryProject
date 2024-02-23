package com.ekinyaldiz.project.library.menu.admin;

import com.ekinyaldiz.project.library.menu.generic.Menu;
import com.ekinyaldiz.project.library.menu.generic.MenuName;
import com.ekinyaldiz.project.library.menu.generic.MenuOption;
import com.ekinyaldiz.project.library.menu.login.UserLoginMenu;

import java.util.List;

public class AdminMainMenu extends Menu {



    public AdminMainMenu() {
        super("Admin Main Menu");

        setOptions(List.of(
                new MenuOption("U", "Search Users" , MenuName.ADMIN_SEARCH_USERS),

                new MenuOption("C",  "Create User" ,MenuName.ADMIN_CREATE_USER),
                new MenuOption("B",  "Search Books" ,MenuName.ADMIN_SEARCH_BOOKS),
                new MenuOption("K",  "Create Book" ,MenuName.ADMIN_CREATE_BOOK),

                new MenuOption("O", "Log off", MenuName.LOG_OFF )));
    }

}

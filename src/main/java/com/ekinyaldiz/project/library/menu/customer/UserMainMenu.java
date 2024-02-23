package com.ekinyaldiz.project.library.menu.customer;

import com.ekinyaldiz.project.library.menu.generic.Menu;
import com.ekinyaldiz.project.library.menu.generic.MenuName;
import com.ekinyaldiz.project.library.menu.generic.MenuOption;

import java.util.List;

public class UserMainMenu extends Menu {



    public UserMainMenu() {
        super("User Main Menu");

        setOptions(List.of(
                new MenuOption("P", "My Profile" , MenuName.MY_PROFILE),

                new MenuOption("B",  "Borrow book" ,MenuName.BORROW_BOOK),
                new MenuOption("R",  "Return Book" ,MenuName.RETURN_BOOK),
                new MenuOption("O", "Log off", MenuName.LOG_OFF )));
    }

}

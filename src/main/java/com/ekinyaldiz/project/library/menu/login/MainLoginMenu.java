package com.ekinyaldiz.project.library.menu.login;

import com.ekinyaldiz.project.library.menu.generic.Menu;
import com.ekinyaldiz.project.library.menu.generic.MenuName;
import com.ekinyaldiz.project.library.menu.generic.MenuOption;

import java.util.List;

public class MainLoginMenu extends Menu {



    public MainLoginMenu() {
        super("Login menu");

        setOptions(List.of(
                new MenuOption("U", " Customer Login" ,MenuName.USER_LOGIN),

                new MenuOption("A",  "Admin Login" ,MenuName.ADMIN_LOGIN),

                new MenuOption("X", "Exit", this::exit )));
    }


    private MenuName exit(){
        System.exit(1);
        return null;
    }
}

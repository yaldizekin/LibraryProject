package com.ekinyaldiz.project.library.menu.login;


import com.ekinyaldiz.project.library.menu.generic.Menu;
import com.ekinyaldiz.project.library.menu.generic.MenuName;
import com.ekinyaldiz.project.library.repo.user.SystemUser;
import com.ekinyaldiz.project.library.service.UserService;
import com.ekinyaldiz.project.library.system.SystemContext;

import java.util.Optional;

public class UserLoginMenu extends Menu {


    public UserLoginMenu(UserService userService) {
        super("User Login", userService);
    }

    @Override
    public MenuName execute() {
        printTitle();
        String username = printAndGet("User name: ");
        String password = printAndGet("Password: ");

        Optional<SystemUser> user = getUserService().getByUsernameAndPassword(username, password);

        if (user.isPresent()) {
            println("Login success, redirecting to main menu ...");
            SystemContext.logInUser(user.get());
            return MenuName.USER_MAIN_MENU;
        } else {
            error("Invalid credentials please try again");
            return execute();
        }
    }
}

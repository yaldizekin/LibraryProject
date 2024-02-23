package com.ekinyaldiz.project.library.menu.admin.user;

import com.ekinyaldiz.project.library.menu.generic.ConsoleReader;
import com.ekinyaldiz.project.library.menu.generic.Menu;
import com.ekinyaldiz.project.library.menu.generic.MenuName;
import com.ekinyaldiz.project.library.repo.user.Customer;
import com.ekinyaldiz.project.library.service.UserService;
import com.ekinyaldiz.project.library.system.SystemContext;

import java.util.Optional;

import static com.ekinyaldiz.project.library.menu.admin.user.ViewUserMenu.USER_ID;

public class DeleteUserMenu extends Menu {


    public DeleteUserMenu(UserService userService) {
        super("Delete User", userService);

    }



    @Override
    public MenuName execute() {
        printTitle();
       String userId = SystemContext.getProperty(USER_ID);
       Optional<Customer> customerOptional= getUserService().getById(Integer.valueOf(userId));
       Customer customer= customerOptional.orElseThrow();

         getUserService().deleteUserByUserId(customer.getId());

        System.out.printf("User %s is successfully deleted. %n", customer.getId());

        println("Press ENTER to continue");

        SystemContext.removeProperty(USER_ID);

        ConsoleReader.readLine();
        return MenuName.ADMIN_MAIN_MENU;


      }



}

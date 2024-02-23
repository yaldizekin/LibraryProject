package com.ekinyaldiz.project.library.menu.admin.user;

import com.ekinyaldiz.project.library.menu.generic.Menu;
import com.ekinyaldiz.project.library.menu.generic.MenuName;
import com.ekinyaldiz.project.library.menu.generic.MenuOption;
import com.ekinyaldiz.project.library.repo.user.Customer;
import com.ekinyaldiz.project.library.service.UserService;
import com.ekinyaldiz.project.library.system.SystemContext;

import java.util.List;
import java.util.Optional;

public class ViewUserMenu extends Menu {

    public static final String USER_ID = "USER_ID";
    public ViewUserMenu(UserService userService) {
        super("View User", userService);
        setOptions(List.of(
                new MenuOption("E", " Edit User" ,MenuName.ADMIN_EDIT_USER),

                new MenuOption("D",  "Delete User" ,MenuName.ADMIN_DELETE_USER),

                new MenuOption("M", "Back to main menu", MenuName.ADMIN_MAIN_MENU)));
    }



    @Override
    public MenuName execute() {
        printTitle();
       String userId = SystemContext.getProperty(USER_ID);
       Optional<Customer> customerOptional= getUserService().getById(Integer.valueOf(userId));
       Customer customer= customerOptional.orElseThrow();

       printfItem( "ID", customer.getId().toString());
       printfItem("Username", customer.getUsername());
       printfItem("FirstName", customer.getFirstName());
       printfItem("LastName", customer.getLastName());
       printfItem("Address:", customer.getAddress());
       printfItem("Postcode:", customer.getPostcode());
       printfItem("City:", customer.getCity());
       printfItem("Email:", customer.getEmail());


        printOptions();

        return run();


      }



}

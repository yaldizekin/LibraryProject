package com.ekinyaldiz.project.library.menu.admin.user;

import com.ekinyaldiz.project.library.menu.generic.ConsoleReader;
import com.ekinyaldiz.project.library.menu.generic.Menu;
import com.ekinyaldiz.project.library.menu.generic.MenuName;
import com.ekinyaldiz.project.library.repo.user.Customer;
import com.ekinyaldiz.project.library.service.UserService;
import com.ekinyaldiz.project.library.system.SystemContext;

import java.util.Optional;

import static com.ekinyaldiz.project.library.menu.admin.user.ViewUserMenu.USER_ID;

public class EditUserMenu extends Menu {


    public EditUserMenu(UserService userService) {
        super("Edit User", userService);

    }



    @Override
    public MenuName execute() {
        printTitle();
       String userId = SystemContext.getProperty(USER_ID);
       Optional<Customer> customerOptional= getUserService().getById(Integer.valueOf(userId));
       Customer customer= customerOptional.orElseThrow();


         String username = printfAndGet("Username", customer.getUsername());
         String firstName= printfAndGet("FirstName", customer.getFirstName());
         String lastName = printfAndGet("LastName", customer.getLastName());
         String address = printfAndGet("Address:", customer.getAddress());
         String postcode = printfAndGet("Postcode:", customer.getPostcode());
         String city = printfAndGet("City:", customer.getCity());
         String email = printfAndGet("Email:", customer.getEmail());

         Customer updatedCustomer = new Customer(customer.getId(),
                 username, customer.getPassword(),
                 firstName, lastName, address, postcode, city , email );

         getUserService().updateUser(updatedCustomer);

        System.out.println();
        println("User is successfully updated.");
        println("Press ENTER to continue");
        SystemContext.removeProperty(USER_ID);
        ConsoleReader.readLine();
        return MenuName.ADMIN_MAIN_MENU;


      }



}

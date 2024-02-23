package com.ekinyaldiz.project.library.menu.admin.user;

import com.ekinyaldiz.project.library.menu.generic.Menu;
import com.ekinyaldiz.project.library.menu.generic.MenuName;
import com.ekinyaldiz.project.library.repo.user.Customer;
import com.ekinyaldiz.project.library.service.UserService;
import com.ekinyaldiz.project.library.system.SystemContext;

import java.util.List;

import static com.ekinyaldiz.project.library.menu.admin.user.ViewUserMenu.USER_ID;

public class SearchUsersMenu extends Menu {
    public SearchUsersMenu(UserService userService) {
        super("Search Users", userService);
    }

    @Override
    public MenuName execute() {
        printTitle();

        String searchTerm=printAndGet("Enter search term: ");

      List<Customer> customers= getUserService().searchUsers(searchTerm);


      if (customers.isEmpty()){
          println("No users found, please try again.");
         return execute();
      }
      else {
          System.out.printf("%-5s|%-20s|%-20s|%-10s|%-15s %n" , "ID", "Name", "Adress", "Postcode", "City" );

          for (Customer c:customers){
              System.out.printf("%-5.5s|%-20.20s|%-20.20s|%-10.10s|%-15.15s %n" ,
                      c.getId(), c.getFirstName() + "" +c.getLastName(),
                      c.getAddress(), c.getPostcode(), c.getCity() );

          }

         String choice = printAndGet("Enter user ID to see or 'X' to go back to main menu:");

          if ("X".equalsIgnoreCase(choice)){
              return  MenuName.ADMIN_MAIN_MENU;
          } else {
               boolean idExists = customers.stream().anyMatch(c -> c.getId().toString().equals(choice));

               if (idExists){
                   SystemContext.addProperty(USER_ID, choice);
                   return MenuName.ADMIN_VIEW_USER;
               } else{
                   return  execute();
               }

          }

      }



    }

}

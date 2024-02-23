package com.ekinyaldiz.project.library.menu.login;

import com.ekinyaldiz.project.library.menu.generic.ConsoleReader;
import com.ekinyaldiz.project.library.menu.generic.Menu;
import com.ekinyaldiz.project.library.menu.generic.MenuName;
import com.ekinyaldiz.project.library.repo.user.AdminUser;
import com.ekinyaldiz.project.library.repo.user.SystemUser;
import com.ekinyaldiz.project.library.service.UserService;
import com.ekinyaldiz.project.library.system.SystemContext;

import java.util.Optional;

public class AdminLoginMenu extends Menu {
    public AdminLoginMenu(UserService userService) {
        super("Admin Login", userService);
    }

    @Override
    public MenuName execute() {
        printTitle();


        String username=printAndGet("User name: ");

        String password= printAndGet("Password: ");

      Optional<SystemUser>user= getUserService().getByUsernameAndPassword(username,password);

      if(user.isPresent()){

          if (user.get() instanceof AdminUser){
              println("Login success, redirecting to main menu...");
              SystemContext.logInUser(user.get());
              return MenuName.ADMIN_MAIN_MENU;
          } else{
              println("User must be an admin, please try again");
                return  execute();
          }


      }else{
          error("Invalid credentials please try again:");
        return  execute();
      }


    }
}

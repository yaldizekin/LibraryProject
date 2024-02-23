package com.ekinyaldiz.project.library.menu.customer.profile;

import com.ekinyaldiz.project.library.menu.generic.ConsoleReader;
import com.ekinyaldiz.project.library.menu.generic.Menu;
import com.ekinyaldiz.project.library.menu.generic.MenuName;
import com.ekinyaldiz.project.library.menu.generic.UserMenu;
import com.ekinyaldiz.project.library.repo.user.Customer;
import com.ekinyaldiz.project.library.service.UserService;
import com.ekinyaldiz.project.library.system.SystemContext;

import java.util.Optional;

import static com.ekinyaldiz.project.library.menu.admin.user.ViewUserMenu.USER_ID;

public class UpdateMyProfileMenu extends UserMenu {




        public UpdateMyProfileMenu(UserService userService) {
            super("Update My Profile", userService);
        }

        @Override
        public MenuName execute() {
            printTitle();
            Integer userId = SystemContext.getLoggedInUserId();
            editUser(userId);
            return MenuName.USER_MAIN_MENU;
        }


}

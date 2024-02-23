package com.ekinyaldiz.project.library.menu.customer.profile;

import com.ekinyaldiz.project.library.menu.generic.*;
import com.ekinyaldiz.project.library.repo.user.Customer;
import com.ekinyaldiz.project.library.service.UserService;
import com.ekinyaldiz.project.library.system.SystemContext;

import java.util.List;
import java.util.Optional;




    public class SeeMyProfileMenu extends UserMenu {

        public SeeMyProfileMenu(UserService userService) {
            super("Update My Profile", userService);
            setOptions(List.of(
                    new MenuOption("U", "Update my Profile", MenuName.UPDATE_MY_PROFILE),
                    new MenuOption("R", "Return to My Profile", MenuName.MY_PROFILE)));
        }

        @Override
        public MenuName execute() {
            printTitle();
            Integer userId = SystemContext.getLoggedInUserId();
            viewUser(userId);
            return run();
        }



}

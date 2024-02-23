package com.ekinyaldiz.project.library.service;

import com.ekinyaldiz.project.library.repo.user.Customer;
import com.ekinyaldiz.project.library.repo.user.SystemUser;
import com.ekinyaldiz.project.library.repo.user.UserRepository;
import com.ekinyaldiz.project.library.system.SystemContext;
import lombok.AllArgsConstructor;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final MailService mailService;

    @Override
    public Optional<SystemUser> getByUsernameAndPassword(String username, String password) {
        return userRepository.getByUsernameAndPassword(username,password);

    }


    @Override
    public void createUser(SystemUser user) {
    if(SystemContext.isLoggedInUserAdmin()) {
        userRepository.createUser(user);
        mailService.sendUserCreatedMail(user);
    }else{
            throw new RuntimeException("Only admin can create new user");
        }
    }


    @Override
    public void deleteUserByUserId(Integer userId) {
        if(SystemContext.isLoggedInUserAdmin()) {
            userRepository.deleteUserById(userId);
        }
        else{
            throw new RuntimeException("Only admin can delete user");
        }
    }

    @Override
    public List<Customer> searchUsers(String searchTerm) {
        if(SystemContext.isLoggedInUserAdmin()) {
            return
                    userRepository.searchUsers(searchTerm).stream()
                            .filter(u -> u instanceof Customer)
                            .map(u -> (Customer) u)
                            .toList();
        }
        else{
            throw new RuntimeException("Only admin can search a user");
        }
    }

    @Override
    public Optional<Customer> getById(Integer userId) {

        return userRepository.getById(userId)
                .map(systemUser -> (Customer) systemUser);
    }

    @Override
    public void updateUser(Customer updatedCustomer) {
        userRepository.updateUser(updatedCustomer);
        mailService.sendUserUpdatedMail(updatedCustomer);


    }
}

package com.ekinyaldiz.project.library.service;

import com.ekinyaldiz.project.library.repo.user.Customer;
import com.ekinyaldiz.project.library.repo.user.SystemUser;
import com.ekinyaldiz.project.library.system.SystemContext;

import java.util.List;
import java.util.Optional;

public interface UserService {


    Optional<SystemUser> getByUsernameAndPassword(String username, String password);

    void createUser(SystemUser user);

    void deleteUserByUserId(Integer userId);

    List<Customer> searchUsers(String searchTerm);

    Optional<Customer> getById(Integer userId);

    void updateUser(Customer updatedCustomer);
}



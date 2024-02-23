package com.ekinyaldiz.project.library.repo.user;

import java.util.*;

public class UserRepository {

    private final Map<Integer, SystemUser> users= new HashMap<>();

    public void createUser(SystemUser user){

       Integer maxId = users.keySet().stream().max(Comparator.naturalOrder()).orElse(1);
        Integer newId=maxId+1;

       SystemUser newUser =  switch (user){
           case AdminUser a -> new AdminUser(newId, a.getUsername(),a.getPassword());
           case  Customer c -> new Customer(
                   newId,
                   c.getUsername(),
                   c.getFirstName(),
                   c.getLastName(),
                   c.getPassword(),
                   c.getAddress(),
                   c.getPostcode(),
                   c.getCity(),
                   c.getEmail()

                   );
               default -> throw new RuntimeException("Only customer and admin users may be created");
        };

        users.put(newId, newUser);
    }

    public void deleteUserById(Integer id){

        users.remove(id);
    }
    public Optional<SystemUser> getById(Integer userId) {
        return Optional.ofNullable(users.get(userId));
    }
    public Optional<SystemUser> getByUsernameAndPassword(String username, String password) {
        return users.values().stream()
                .filter(u->u.getUsername().equals(username)&&u.getPassword().equals(password))
                .findFirst();
    }

    public List<SystemUser> searchUsers(String searchTerm) {
        return users.values().stream()
                .filter(u-> searchUser(u, searchTerm))
                .toList();
    }

    private boolean searchUser(SystemUser user, String searchTerm){

        boolean found= findIn(searchTerm,
                user.getId().toString(),
                user.getUsername());

        if (user instanceof Customer customer){
            return found || findIn(searchTerm, customer.getAddress(),
                    customer.getPostcode(), customer.getCity(),
                    customer.getEmail(), customer.getFirstName(), customer.getLastName());
        } else {

            return false;
        }


    }

    private boolean findIn(String searchTerm, String... values){

        return Arrays.stream(values)
                .anyMatch(s->s.contains(searchTerm));

    }


    public void updateUser(Customer updatedCustomer) {
        users.put(updatedCustomer.getId(),updatedCustomer);
    }
}

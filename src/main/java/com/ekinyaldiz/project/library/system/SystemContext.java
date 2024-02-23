package com.ekinyaldiz.project.library.system;

import com.ekinyaldiz.project.library.exceptions.UserLoginException;
import com.ekinyaldiz.project.library.repo.user.AdminUser;
import com.ekinyaldiz.project.library.repo.user.Customer;
import com.ekinyaldiz.project.library.repo.user.SystemUser;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public final class SystemContext {
    private static  Integer loggedInUserId;
    public static boolean isAdmin=false;
    private static final Map<String, String > properties = new HashMap<>();

    public static void addProperty(String name, String value){
        properties.put(name,value);
    }
    public static String getProperty(String name){
       return properties.get(name);
    }
    public static boolean isLoggedInUserAdmin(){
    return isAdmin;

    }

    public static Integer getLoggedInUserId(){
        return Optional
                .ofNullable(loggedInUserId)
                .orElseThrow(()-> new UserLoginException("No log in info available"));

    }
    public  static void logInUser(SystemUser user){
        switch(user){
            case null-> throw new UserLoginException("Log in user is null");
            case Customer customer -> {
                loggedInUserId=user.getId();
                isAdmin=false;
            }
        case AdminUser admin->{
                loggedInUserId=user.getId();
                isAdmin=true;
        }
            default -> throw new UserLoginException("Unsupported user type: " + user);

        }
    }

    public  static  void logOut(){
        loggedInUserId=null;
        isAdmin=false;
    }

    public static void removeProperty(String key) {
        properties.remove(key);
    }
}

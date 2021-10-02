package am.threesmart.cowin.utils;

import java.util.List;

import am.threesmart.cowin.database.Storage;
import am.threesmart.cowin.user.User;

public class Checker {

    public static boolean isUsernameValid(String username) {
        List<User> allUsers = Storage.getAllUsers();
        for (User user : allUsers) {
            if(user.getUsername().equals(username)) {
                return true;
            }
        }
        return false;
    }

    public static boolean isPasswordValid(String password) {
        List<User> allUsers = Storage.getAllUsers();
        for (User user : allUsers) {
            if(user.getPassword().equals(password)) {
                return true;
            }
        }
        return false;
    }

}

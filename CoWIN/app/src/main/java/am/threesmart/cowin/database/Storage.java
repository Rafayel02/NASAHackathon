package am.threesmart.cowin.database;

import java.util.ArrayList;
import java.util.List;

import am.threesmart.cowin.user.User;

public class Storage {
    private static List<User> usersList = new ArrayList<>();

    private Storage() {
    }

    public static void addUser(User user) {

    }

    public static List<User> getAllUsers() {
        return usersList;
    }

}

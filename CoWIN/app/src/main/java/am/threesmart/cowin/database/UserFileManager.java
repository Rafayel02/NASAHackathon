package am.threesmart.cowin.database;

import android.content.Context;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import am.threesmart.cowin.user.User;

public class UserFileManager {

    private static File file = null;

    public static File createFileIfNotExists(Context context) throws IOException {
        File dir = context.getCacheDir();
        File file = new File(dir.toString() + "users.txt");
        if (!file.exists()) {
            file.createNewFile();
            addUser(new User("test", "test"));
        }
        return file;
    }

    public static boolean addUser(User user) {
        List<User> allUsers = getAllUsers();
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            for (User currUser : allUsers) {
                writer.write(currUser.getUsername() + " " + currUser.getPassword());
            }
            writer.write(user.getUsername() + " " + user.getPassword());
            System.out.println("Wrote user into file!");
            return true;
        } catch (IOException e) {
            System.out.println("Writer error!");
            e.printStackTrace();
        }
        return false;
    }

    public static boolean doesUserExist(String username, String password) {
        User user = getUserByUsername(username);
        if(user == null) {
            return false;
        }
        return user.getPassword().equals(password);
    }

    public static User getUserByUsername(String username) {
        List<User> allUsers = getAllUsers();
        for (User user : allUsers) {
            if(user.getUsername().equals(username)) {
                return user;
            }
        }
        return null;
    }

    public static List<User> getAllUsers() {
        final List<User> users = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line = reader.readLine();
            while (line != null) {
                users.add(
                        new User(
                                line.split(" ")[0],
                                line.split(" ")[1]
                        )
                );

                line = reader.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return users;
    }

}

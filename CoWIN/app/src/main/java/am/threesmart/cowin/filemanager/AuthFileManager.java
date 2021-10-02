package am.threesmart.cowin.filemanager;

import android.content.Context;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import am.threesmart.cowin.user.User;

public class AuthFileManager {

    private static File authFile = null;

    public static File createFileIfNotExists(Context context) throws IOException {
        File dir = context.getCacheDir();
        File file = new File(dir.toString() + "auth.txt");
        if (!file.exists()) {
            file.createNewFile();
            authFile = file;
        }
        authFile = file;
        return file;
    }

    public static void setAuthenticated() {
        writeBoolInFile(true);
    }

    public static void removeAuthentication() {
        writeBoolInFile(false);
    }

    public static boolean isAuthentication() {
        boolean bool = false;
        try(BufferedReader reader = new BufferedReader(new FileReader(authFile))) {
            String line = reader.readLine();
            if(line == null) {
                return false;
            }
            bool = Boolean.parseBoolean(line);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bool;
    }

    private static void writeBoolInFile(boolean bool) {
        if(authFile == null) {
            return;
        }
        try(BufferedWriter writer = new BufferedWriter(new FileWriter(authFile))) {
            writer.write(String.valueOf(bool));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}

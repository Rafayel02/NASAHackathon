package am.threesmart.cowin.filemanager;

import android.content.Context;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class InformationFileManager {
    private static File informationFile = null;

    private static int Age;
    private static String BiologicalSex;

    public static File createFileIfNotExists(Context context) throws IOException {
        File dir = context.getCacheDir();
        File file = new File(dir.toString() + "/info.txt");

        if (!file.exists()) {
            file.createNewFile();
            informationFile = file;
        }
        informationFile = file;
        return file;
    }

    //Value by default wil be null.
    public static void addField(String fieldName) throws IOException {
        addFieldAndValue(fieldName, "null");
    }

    //Field and value, value will be set as string, so make object string then set as an argument.
    public static void addFieldAndValue(String fieldName, String value) throws IOException {
        String allFile = readAllFile();
        BufferedWriter writer = new BufferedWriter(new FileWriter(informationFile));
        writer.write(allFile);
        writer.write(fieldName + " " + value + "\n");
        writer.close();
    }

    //Adding value to existing field (changing or writing instead of null).
    public static void addValueToExistingField(String fieldName, String value) throws IOException {
        StringBuilder result = new StringBuilder();
        BufferedReader reader = new BufferedReader(new FileReader(informationFile));
        String line = reader.readLine();
        while (line != null) {
            if (line.split(" ")[0].equals(fieldName)) {
                result.append(fieldName + " " + value + "\n");
            } else {
                result.append(line)
                        .append("\n");
            }
            line = reader.readLine();
        }
        reader.close();

        BufferedWriter writer = new BufferedWriter(new FileWriter(informationFile));
        writer.write(result.toString());
        writer.flush();
        writer.close();
    }

    //Returning value by field name in string format that already has been set by value.toString() method.
    public static String getValueByField(String fieldName) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(informationFile));
        String line = reader.readLine();
        while (line != null) {
            if (line.split(" ")[0].equals(fieldName)) {
                return line.replace(line.split(" ")[0] + " ", "");
            }

            line = reader.readLine();
        }
        reader.close();
        return null;
    }

    //Checks field by name in file.
    public static boolean doesFieldExist(String fieldName) throws IOException {
        return readAllFile().contains(fieldName);
    }


    public static String readAllFile() throws IOException {
        StringBuilder result = new StringBuilder();
        BufferedReader reader = new BufferedReader(new FileReader(informationFile));
        String line = reader.readLine();
        while (line != null) {
            result.append(line)
                    .append("\n");
            line = reader.readLine();
        }
        reader.close();
        return result.toString();
    }


    public static void clearAll() throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(informationFile));
        writer.write("");
        writer.flush();
        writer.close();
    }
}

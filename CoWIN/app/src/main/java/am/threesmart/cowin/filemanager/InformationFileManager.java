package am.threesmart.cowin.filemanager;

import android.content.Context;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class InformationFileManager {
    private static File informationFile = null;

    private static int Age;
    private static String BiologicalSex;
    private static BufferedWriter writer;
    private static BufferedReader reader;

    public static File createFileIfNotExists(Context context) throws IOException {
        System.out.println(1);
        File dir = context.getCacheDir();
        File file = new File(dir.toString() + "/info.txt");
        System.out.println(2);

        if (!file.exists()) {
            System.out.println(3);
            file.createNewFile();
            System.out.println(4);
            informationFile = file;

            writer.close();
            reader.close();
            System.out.println("File of info created!");
        }
        informationFile = file;
        writer = new BufferedWriter(new FileWriter(informationFile));
        reader = new BufferedReader(new FileReader(informationFile));
        return file;
    }

    //Value by default wil be null.
    public static void addField(String fieldName) throws IOException {
        addFieldAndValue(fieldName, "null");
    }

    //Field and value, value will be set as string, so make object string then set as an argument.
    public static void addFieldAndValue(String fieldName, String value) throws IOException {
        if (doesFieldExist(fieldName)) {
            return;
        }
        writer.write(fieldName + " " + value + "\n");
    }

    //Adding value to existing field (changing or writing instead of null).
    public static void addValueToExistingField(String fieldName) throws IOException {
        if (!doesFieldExist(fieldName)) {
            return;
        }
        reader.reset();
        String line = reader.readLine();
        while (line != null) {
            if (line.split(" ")[0].equals(fieldName)) {

                break;
            }
            line = reader.readLine();
        }
    }

    //Returning value by field name in string format that already has been set by value.toString() method.
    public static String getValueByField(String fieldName) {
        return null;
    }

    //Checks field by name in file.
    public static boolean doesFieldExist(String fieldName) {
        return true;
    }


    public static void readAllFile() throws IOException {
        String line = reader.readLine();
        System.out.println("******"+line);
        while (line != null) {
            line = reader.readLine();
            System.out.println("******"+line);
        }
    }


}

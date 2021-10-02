package am.threesmart.cowin.database.filemanager;

import android.content.Context;

import java.io.File;
import java.io.IOException;

public class UserFileManager {

    private File file;

    public static File createFileIfNotExists(Context context) throws IOException {
        File dir = context.getCacheDir();
        File file = new File(dir.toString() + "users.txt");
        if(file.exists()) {
            return file;
        }
        file.createNewFile();
        return file;
    }


}

package am.threesmart.cowin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

import am.threesmart.cowin.filemanager.AuthFileManager;
import am.threesmart.cowin.filemanager.InformationFileManager;
import am.threesmart.cowin.filemanager.UserFileManager;
import am.threesmart.cowin.user.User;

public class SplashScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);


        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                try {
                    UserFileManager.createFileIfNotExists(getApplicationContext());
                    AuthFileManager.createFileIfNotExists(getApplicationContext());
                    if (AuthFileManager.isAuthenticated()) {
                        startActivity(new Intent(getApplicationContext(), HomeActivity.class));
                    } else {
                        startActivity(new Intent(getApplicationContext(), MainActivity.class));
                    }
                    finish();
                    System.out.println("File exists!");
                } catch (IOException e) {
                    System.out.println("File error!");
                    e.printStackTrace();
                }

                for (User allUser : UserFileManager.getAllUsers()) {
                    System.out.println("iiiiiiiiiiiiiiiiiiiiiiii" + allUser);
                }
            }
        }, 3000);

    }
}
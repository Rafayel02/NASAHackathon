package am.threesmart.cowin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.io.IOException;

import am.threesmart.cowin.database.Storage;
import am.threesmart.cowin.database.filemanager.UserFileManager;
import am.threesmart.cowin.utils.Checker;

public class MainActivity extends AppCompatActivity {

    private EditText username;
    private EditText password;
    private Button loginButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        try {
            UserFileManager.createFileIfNotExists(getApplicationContext());
        } catch (IOException e) {
            System.out.println("aaaaaaaa");
            e.printStackTrace();
        }

        //Init objects
        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        loginButton = findViewById(R.id.login_button);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println(Checker.isUsernameValid(username.getText().toString()));
                System.out.println(Checker.isUsernameValid(password.getText().toString()));
                System.out.println(Storage.getAllUsers().size());
                if (Checker.isUsernameValid(username.getText().toString()) &&
                        Checker.isPasswordValid(password.getText().toString())) {
                    Intent intent = new Intent(MainActivity.this, HomeActivity.class);
                    intent.putExtra("username", username.getText().toString());
                    startActivity(intent);
                    finish();
                }
            }
        });

    }

}
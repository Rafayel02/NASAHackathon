package am.threesmart.cowin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.IOException;
import java.util.Objects;

import am.threesmart.cowin.database.UserFileManager;
import am.threesmart.cowin.user.User;

public class MainActivity extends AppCompatActivity {

    private EditText username;
    private EditText password;
    private Button loginButton;
    private Button registerButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        try {
            UserFileManager.createFileIfNotExists(getApplicationContext());
            System.out.println("File exists!");
        } catch (IOException e) {
            System.out.println("File error!");
            e.printStackTrace();
        }

        for (User allUser : UserFileManager.getAllUsers()) {
            System.out.println("iiiiiiiiiiiiiiiiiiiiiiii" + allUser);
        }

        //Init objects
        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        loginButton = findViewById(R.id.login_button);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                User userByUsername = UserFileManager.getUserByUsername(username.getText().toString());
                if (userByUsername != null) {
                    if (Objects.equals(userByUsername.getPassword(), password.getText().toString())) {
                        Intent intent = new Intent(MainActivity.this, HomeActivity.class);
                        intent.putExtra("username", username.getText().toString());
                        startActivity(intent);
                        finish();
                        return;
                    }
                }
                Toast.makeText(getApplicationContext(), "Something went wrong!", Toast.LENGTH_SHORT).show();
            }
        });

        registerButton = findViewById(R.id.register_button);
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });

    }

}
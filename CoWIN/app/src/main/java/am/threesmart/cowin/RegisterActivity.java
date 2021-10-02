package am.threesmart.cowin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Objects;

import am.threesmart.cowin.filemanager.AuthFileManager;
import am.threesmart.cowin.filemanager.UserFileManager;
import am.threesmart.cowin.user.User;

public class RegisterActivity extends AppCompatActivity {

    private EditText username;
    private EditText password;
    private EditText passwordConfirmation;
    private Button registrationButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        username = findViewById(R.id.username_reg);
        password = findViewById(R.id.password1);
        passwordConfirmation = findViewById(R.id.password2);

        registrationButton = findViewById(R.id.register_button);
        registrationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Objects.equals(password.getText().toString(), passwordConfirmation.getText().toString())) {
                    User userByUsername = UserFileManager.getUserByUsername(username.getText().toString());
                    if (userByUsername == null) {
                        UserFileManager.addUser(new User(username.getText().toString(), password.getText().toString()));
                        System.out.println("All is ok");
                        Intent intent = new Intent(RegisterActivity.this, QuestionAnswerActivity.class);
                        startActivity(intent);
                        finish();
                    } else {
                        Toast.makeText(getApplicationContext(), "User already exists!", Toast.LENGTH_SHORT).show();
                        System.out.println("User already exists!");
                    }
                } else {
                    Toast.makeText(getApplicationContext(), "Check passwords!", Toast.LENGTH_SHORT).show();
                    System.out.println("Password equality error");
                }
            }
        });

    }
}
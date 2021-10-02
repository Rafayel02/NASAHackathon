package am.threesmart.cowin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import am.threesmart.cowin.filemanager.AuthFileManager;

public class QuestionAnswerActivity extends AppCompatActivity {

    private Button confirmButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question_answer);

        confirmButton = findViewById(R.id.confirm_button);
        confirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AuthFileManager.setAuthenticated();
                Intent intent = new Intent(QuestionAnswerActivity.this, HomeActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
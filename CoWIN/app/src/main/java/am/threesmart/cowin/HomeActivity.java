package am.threesmart.cowin;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import am.threesmart.cowin.filemanager.AuthFileManager;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
    }
}
package am.threesmart.cowin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import am.threesmart.cowin.filemanager.AuthFileManager;

public class QuestionAnswerActivity extends AppCompatActivity {

    private Button confirmButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question_answer);

        //get the spinner from the xml.
        Spinner spinnerForEthnicity = findViewById(R.id.spinner1);
        Spinner spinnerForDiseases = findViewById(R.id.spinner2);
        //create a list of items for the spinner.
        String[] ethnicities = new String[]{
                "Ethnicity not stated",
                "White British",
                "White Irish",
                "Other White background",
                "White and Black Caribbean mixed",
                "White and Black African mixed",
                "White and Asian mixed",
                "Other mixed or multiple ethnic background",
                "Indian",
                "Pakistani",
                "Bangladeshi",
                "Any other Asian background",
                "Caribbean",
                "Black African",
                "Any other Black/African/Caribbean",
                "Chinese",
                "Other ethnic group including Arab"};

        String[] diseases = new String[]{
                "No",
                "Diabetes",
                "Chronic Respiratory disease",
                "Hypertension",
                "Cancer",
                "Cardiovascular disease"};
        //create an adapter to describe how the items are displayed, adapters are used in several places in android.
        //There are multiple variations of this, but this is the basic variant.
        CustomDropDownAdapter customAdapter = new CustomDropDownAdapter(getApplicationContext(),ethnicities);
        //set the spinners adapter to the previously created one.
        spinnerForEthnicity.setAdapter(customAdapter);

        CustomDropDownAdapter customAdapterForDiseases = new CustomDropDownAdapter(getApplicationContext(),diseases);

        spinnerForDiseases.setAdapter(customAdapterForDiseases);


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
package am.threesmart.cowin;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

import am.threesmart.cowin.filemanager.AuthFileManager;

public class QuestionAnswerActivity extends AppCompatActivity {

    private Button confirmButton;
    private TextView chooseDiseasesTextView;
    boolean[] selectedDiseases;
    ArrayList<Integer> diseasesList = new ArrayList<>();
    String[] diseasesArray = {
            "No",
            "Diabetes",
            "Chronic Respiratory disease",
            "Hypertension",
            "Cancer",
            "Cardiovascular disease"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question_answer);

        //get the spinner from the xml.
        Spinner spinnerForEthnicity = findViewById(R.id.spinner1);
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

        //create an adapter to describe how the items are displayed, adapters are used in several places in android.
        //There are multiple variations of this, but this is the basic variant.
        CustomDropDownAdapter customAdapter = new CustomDropDownAdapter(getApplicationContext(),ethnicities);
        //set the spinners adapter to the previously created one.
        spinnerForEthnicity.setAdapter(customAdapter);

        chooseDiseasesTextView = (TextView) findViewById(R.id.chooseYourDiseases);

        //Initialize selected diseases array
        selectedDiseases = new boolean[diseasesArray.length];

        chooseDiseasesTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Initialize alert dialog
                AlertDialog.Builder builder = new AlertDialog.Builder(
                        QuestionAnswerActivity.this
                );
                //Set title
                builder.setTitle("Select Your Diseases");

                // Set dialog non cancelable
                builder.setCancelable(false);

                builder.setMultiChoiceItems(diseasesArray, selectedDiseases, new DialogInterface.OnMultiChoiceClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i, boolean b) {

                        if(b){

                            // When checkbox selected
                            //Add position in disease list
                            diseasesList.add(i);
                            //Sort disease list
                            Collections.sort(diseasesList);

                        }
                        else{
                            //When checkbox unselected
                            //Remove position from disease list
                            diseasesList.remove(i);
                        }

                    }
                });

                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        // Initialize String builder
                        StringBuilder stringBuilder = new StringBuilder();
                        for (int j = 0; j < diseasesList.size(); j++){
                            //Concat array value
                            stringBuilder.append(diseasesArray[diseasesList.get(j)]);
                            //check condition
                            if(j != diseasesList.size()-1){
                                // Add comma
                                stringBuilder.append(", ");
                            }
                        }
                        String diseases = stringBuilder.toString();

                        if(stringBuilder.length() > 15){
                            diseases = stringBuilder.substring(0,13) + "...";
                        }
                        //Set text on text view
                        chooseDiseasesTextView.setText(diseases);
                    }
                });

                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        //Dismiss dialog
                        dialogInterface.dismiss();
                    }
                });

                builder.setNeutralButton("Clear All", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        for(int j = 0 ; j < selectedDiseases.length; j++){
                            // Remove all Selection
                            selectedDiseases[j] = false;
                            //Clear diseases list
                            diseasesList.clear();
                            //Clear text view value
                            chooseDiseasesTextView.setText("Choose your Chronic Diseases...");
                        }
                    }
                });
                //Show dialog
                builder.show();


            }
        });


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
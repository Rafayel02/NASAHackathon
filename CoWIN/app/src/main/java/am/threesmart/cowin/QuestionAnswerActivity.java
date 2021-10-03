package am.threesmart.cowin;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Objects;

import am.threesmart.cowin.filemanager.AuthFileManager;
import am.threesmart.cowin.filemanager.InformationFileManager;
import am.threesmart.cowin.user.UserInfo;

public class QuestionAnswerActivity extends AppCompatActivity {

    public static UserInfo userInfo = null;
    private Button confirmButton;
    private Button maleButton;
    private Button femaleButton;
    private TextView chooseDiseasesTextView;
    private TextView fullNameTextView;
    private TextView ageTextView;
    private String biologicalSex;
    boolean[] selectedDiseases;
    Spinner spinnerForEthnicity;
    String selected_val;
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

        fullNameTextView = findViewById(R.id.full_name_questions);
        ageTextView = findViewById(R.id.age_questions);


        //get the spinner from the xml.
        spinnerForEthnicity = findViewById(R.id.spinner1);
        //create a list of items for the spinner.
        String[] ethnicities = new String[]{
                "Hispanic/Latino", "American Indian / Alaska Native Non-Hispanic", "Asian Non-Hispanic", "Black Non-Hispanic", "Native Hawaiian / Other Pacific Islander Non-Hispanic", "White Non-Hispanic", "Multiple/Other Non-Hispanic"};

        //create an adapter to describe how the items are displayed, adapters are used in several places in android.
        //There are multiple variations of this, but this is the basic variant.
        CustomDropDownAdapter customAdapter = new CustomDropDownAdapter(getApplicationContext(), ethnicities);
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

                        if (b) {

                            // When checkbox selected
                            //Add position in disease list
                            diseasesList.add(i);
                            //Sort disease list
                            Collections.sort(diseasesList);

                        } else {
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
                        for (int j = 0; j < diseasesList.size(); j++) {
                            //Concat array value
                            stringBuilder.append(diseasesArray[diseasesList.get(j)]);
                            //check condition
                            if (j != diseasesList.size() - 1) {
                                // Add comma
                                stringBuilder.append(", ");
                            }
                        }
                        String diseases = stringBuilder.toString();

                        if (stringBuilder.length() > 30) {
                            diseases = stringBuilder.substring(0, 28) + "...";
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
                        for (int j = 0; j < selectedDiseases.length; j++) {
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

        spinnerForEthnicity.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> arg0, View view, int arg2, long arg3) {

                if (spinnerForEthnicity.getSelectedItem() != null) {
                    selected_val = spinnerForEthnicity.getSelectedItem().toString();
                    Toast.makeText(getApplicationContext(), selected_val,
                            Toast.LENGTH_SHORT).show();
                } else {

                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub

            }
        });


        maleButton = findViewById(R.id.button);
        femaleButton = findViewById(R.id.button2);

        maleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                maleButton.setBackground(ContextCompat.getDrawable(QuestionAnswerActivity.this, R.drawable.ic_asset_male_button_selected));
                maleButton.setTextColor(getResources().getColor(R.color.black));
                femaleButton.setBackground(ContextCompat.getDrawable(QuestionAnswerActivity.this, R.drawable.ic_asset_female_button));
                femaleButton.setTextColor(getResources().getColor(R.color.white));
                biologicalSex = "male";

            }
        });
        femaleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                femaleButton.setBackground(ContextCompat.getDrawable(QuestionAnswerActivity.this, R.drawable.ic_asset_female_button_selected));
                femaleButton.setTextColor(getResources().getColor(R.color.black));
                maleButton.setBackground(ContextCompat.getDrawable(QuestionAnswerActivity.this, R.drawable.ic_asset_male_button));
                maleButton.setTextColor(getResources().getColor(R.color.white));
                biologicalSex = "female";
            }
        });


        confirmButton = findViewById(R.id.confirm_button);
        confirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isAllRequiredFieldsEntered()) {
                    AuthFileManager.setAuthenticated();

                    Intent intent = new Intent(QuestionAnswerActivity.this, HomeActivity.class);
                    startActivity(intent);
                    finish();
                } else {
                    AlertDialog.Builder builder = new AlertDialog.Builder(
                            QuestionAnswerActivity.this
                    );
                    //Set title
                    builder.setTitle("You Must Enter All Required Fields");

                    // Set dialog non cancelable
                    builder.setCancelable(false);

                    builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.dismiss();
                        }
                    });

                    builder.show();
                }

            }
        });


    }

    private boolean isAllRequiredFieldsEntered() {
        String fullName;
        short age;
        String biologicalSexTemp = null;
        String ethnicity;
        ArrayList<String> chronicDiseases = new ArrayList();

        if (fullNameTextView.getText().toString().equals(null) || fullNameTextView.getText().toString().equals("")) {
            return false;
        } else {
            fullName = fullNameTextView.getText().toString();
        }
        if (ageTextView.getText().toString().equals(null) || ageTextView.getText().toString().equals("")) {
            return false;
        } else {
            age = Short.valueOf(ageTextView.getText().toString());
        }
        if (Objects.equals(biologicalSex, null)) {
            return false;
        } else {
            biologicalSexTemp = biologicalSex;
        }
        if (selected_val == null) {
            return false;
        } else {
            ethnicity = selected_val;

        }
        if (diseasesList.size() == 0) {
            return false;
        } else {
            for (int j = 0; j < diseasesList.size(); j++) {
                chronicDiseases.add(diseasesArray[diseasesList.get(j)]);
            }
        }

        try {
            InformationFileManager.clearAll();
            InformationFileManager.addFieldAndValue("FullName", fullName);
            InformationFileManager.addFieldAndValue("Age", String.valueOf(age));
            InformationFileManager.addFieldAndValue("Sex", biologicalSexTemp);
            InformationFileManager.addFieldAndValue("Ethnicity", ethnicity);
            StringBuilder chronicDiseasesString = new StringBuilder();
            for (String chronicDisease : chronicDiseases) {
                chronicDiseasesString.append(chronicDisease).append(" ");
            }
            InformationFileManager.addFieldAndValue("ChronicDiseases", chronicDiseasesString.toString());

        } catch (IOException e) {
            e.printStackTrace();
        }

        userInfo = new UserInfo(fullName, age, biologicalSexTemp, ethnicity, chronicDiseases);

        return true;
    }
}
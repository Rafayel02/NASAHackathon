package am.threesmart.cowin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import am.threesmart.cowin.filemanager.AuthFileManager;
import am.threesmart.cowin.filemanager.InformationFileManager;
import am.threesmart.cowin.user.UserInfo;

public class ProfileActivity extends AppCompatActivity {

    private Button backButton;
    private Button logoutButton;
    private TextView fullNameTextView;
    private TextView ageTextView;
    private TextView biologicalSexTextView;
    private TextView ethnicityTextView;
    private TextView riskOfCatchingCovidTextView;
    private UserInfo userInfo;
    private double ageRatio;
    private double sexRatio;
    private double diseaseRatio = 0;
    private double ethnicityRatio;
    private double risk;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        userInfo = QuestionAnswerActivity.userInfo;
        if(userInfo == null) {
            try {
                String chronicDiseases = InformationFileManager.getValueByField("ChronicDiseases");
                String[] s = chronicDiseases.split(" ");

                ArrayList<String> chronicDiseasesList = new ArrayList<>(Arrays.asList(s));

                userInfo = new UserInfo(
                        InformationFileManager.getValueByField("FullName"),
                        Short.parseShort(InformationFileManager.getValueByField("Age")),
                        InformationFileManager.getValueByField("Sex"),
                        InformationFileManager.getValueByField("Ethnicity"),
                        chronicDiseasesList
                        );
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        backButton = findViewById(R.id.back_button);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });


        logoutButton = findViewById(R.id.logout_button);
        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AuthFileManager.removeAuthentication();
                Intent intent = new Intent(ProfileActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

        fullNameTextView = findViewById(R.id.full_name_textview);
        ageTextView = findViewById(R.id.age_textview);
        biologicalSexTextView = findViewById(R.id.sex_textview);
        ethnicityTextView = findViewById(R.id.ethnicity_textview);
        riskOfCatchingCovidTextView = findViewById(R.id.risk_of_catching_covid_textview);
        fullNameTextView.setText(userInfo.getFullName());
        ageTextView.setText(userInfo.getAge() + "");
        biologicalSexTextView.setText(userInfo.getBiologicalSex());
        ethnicityTextView.setText(userInfo.getEthnicity());

        if (userInfo.getAge() > 80) {
            ageRatio = 14.8;
        } else if (userInfo.getAge() >= 70 && userInfo.getAge() <= 79) {
            ageRatio = 8;
        } else if (userInfo.getAge() >= 60 && userInfo.getAge() <= 69) {
            ageRatio = 3.6;
        } else if (userInfo.getAge() >= 50 && userInfo.getAge() <= 59) {
            ageRatio = 1.3;
        } else if (userInfo.getAge() >= 40 && userInfo.getAge() <= 49) {
            ageRatio = 0.4;
        } else if (userInfo.getAge() >= 20 && userInfo.getAge() <= 39) {
            ageRatio = 0.2;
        } else if (userInfo.getAge() >= 0 && userInfo.getAge() <= 19) {
            ageRatio = 0.05;
        }
        if (userInfo.getBiologicalSex().equals("male")) {
            sexRatio = 2.8;
        } else {
            sexRatio = 1.7;
        }
        for (int i = 0; i < userInfo.getChronicDiseases().size(); ++i) {
            if (userInfo.getChronicDiseases().get(i).equals("Diabetes")) {
                diseaseRatio += 7.3;
            }
            if (userInfo.getChronicDiseases().get(i).equals("Chronic Respiratory disease")) {
                diseaseRatio += 6.3;
            }
            if (userInfo.getChronicDiseases().get(i).equals("Hypertension")) {
                diseaseRatio += 6;
            }
            if (userInfo.getChronicDiseases().get(i).equals("Cancer")) {
                diseaseRatio += 5.6;
            }
            if (userInfo.getChronicDiseases().get(i).equals("Cardiovascular disease")) {
                diseaseRatio += 10.5;
            }

        }
        if (userInfo.getEthnicity().equals("Ethnicity not stated")) {
            ethnicityRatio = 0.1;
        } else if (userInfo.getEthnicity().equals("Hispanic/Latino")) {
            ethnicityRatio = 0.52038835;
        } else if (userInfo.getEthnicity().equals("American Indian / Alaska Native Non-Hispanic")) {
            ethnicityRatio = 0.021359223;
        } else if (userInfo.getEthnicity().equals("Asian Non-Hispanic")) {
            ethnicityRatio = 0.060194175;
        } else if (userInfo.getEthnicity().equals("Black Non-Hispanic")) {
            ethnicityRatio = 0.231067961;
        } else if (userInfo.getEthnicity().equals("Native Hawaiian / Other Pacific Islander Non-Hispanic")) {
            ethnicityRatio = 0.005825243;
        } else if (userInfo.getEthnicity().equals("White Non-Hispanic")) {
            ethnicityRatio = 1;
        } else if (userInfo.getEthnicity().equals("Multiple/Other Non-Hispanic")) {
            ethnicityRatio = 0.102912621;
        }

        risk = 0.95 * ((0.2775 / 14.8) * ageRatio + (0.0525 / 2.8) * sexRatio + (0.67 / 35.7) * diseaseRatio) + 0.05 * ethnicityRatio;

        int riskInInt = (int) Math.round(risk * 4);
        String riskInString = "";
        switch (riskInInt) {
            case 0:
                riskInString = "LOW RISK";
                break;
            case 1:
                riskInString = "LOW/MEDIUM RISK";
                break;

            case 2:
                riskInString = "MEDIUM RISK";
                break;

            case 3:
                riskInString = "MEDIUM/HIGH RISK";
                break;

            case 4:
                riskInString = "HIGH RISK";
                break;
        }
        riskOfCatchingCovidTextView.setText(riskInString);


    }
}
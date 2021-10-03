package am.threesmart.cowin.user;

import java.util.ArrayList;

public class UserInfo {

    private String fullName;
    private short age;
    private String biologicalSex;
    private String ethnicity;
    private String maskType;
    private String riskOfCatchingCovid;
    private ArrayList<String> chronicDiseases;

    public UserInfo() {

    }
    public UserInfo(String fullName, short age, String biologicalSex, String ethnicity , ArrayList<String> chronicDiseases) {
        this.fullName = fullName;
        this.age = age;
        this.biologicalSex = biologicalSex;
        this.ethnicity = ethnicity;
        this.chronicDiseases = chronicDiseases;
    }

    public String getFullName() {
        return fullName;
    }

    public ArrayList<String> getChronicDiseases() {
        return chronicDiseases;
    }

    public void setChronicDiseases(ArrayList<String> chronicDiseases) {
        this.chronicDiseases = chronicDiseases;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public short getAge() {
        return age;
    }

    public void setAge(short age) {
        this.age = age;
    }

    public String getBiologicalSex() {
        return biologicalSex;
    }

    public void setBiologicalSex(String biologicalSex) {
        this.biologicalSex = biologicalSex;
    }

    public String getEthnicity() {
        return ethnicity;
    }

    public void setEthnicity(String ethnicity) {
        this.ethnicity = ethnicity;
    }

    public String getMaskType() {
        return maskType;
    }

    public void setMaskType(String maskType) {
        this.maskType = maskType;
    }

    public String getRiskOfCatchingCovid() {
        return riskOfCatchingCovid;
    }

    public void setRiskOfCatchingCovid(String riskOfCatchingCovid) {
        this.riskOfCatchingCovid = riskOfCatchingCovid;
    }
}

package am.threesmart.cowin.requestmanager.weather;

public class WeatherInfoKeeper {
    public String temp;
    public String humidity;
    public String windSpeed;
    public String pressure;

    public String co;
    public String no;
    public String no2;
    public String o3;
    public String so2;
    public String pm2_5;
    public String pm10;
    public String nh3;

    public String city;

    @Override
    public String toString() {
        return "WeatherInfoKeeper{" +
                "temp='" + temp + '\'' +
                ", humidity='" + humidity + '\'' +
                ", windSpeed='" + windSpeed + '\'' +
                ", pressure='" + pressure + '\'' +
                ", co='" + co + '\'' +
                ", no='" + no + '\'' +
                ", no2='" + no2 + '\'' +
                ", o3='" + o3 + '\'' +
                ", so2='" + so2 + '\'' +
                ", pm2_5='" + pm2_5 + '\'' +
                ", pm10='" + pm10 + '\'' +
                ", nh3='" + nh3 + '\'' +
                ", city='" + city + '\'' +
                '}';
    }
}

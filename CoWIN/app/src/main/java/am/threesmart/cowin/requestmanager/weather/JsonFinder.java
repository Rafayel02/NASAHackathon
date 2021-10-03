package am.threesmart.cowin.requestmanager.weather;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * The Class for getting information in json format from API.
 */
public class JsonFinder {

    public String currentDataUrl;
    public String pollutionDataUrl;
    public URL url;
    public String appID = "06ae7dc77b63d753c503239d6b3210ab";

    public JSONObject[] jsonArr = new JSONObject[2];

    public void readJson(String latitude, String longitude) throws IOException {

        currentDataUrl = "https://api.openweathermap.org/data/2.5/weather?lat="+latitude+"&lon="+longitude+"&appid="+appID+"&units=metric";
        pollutionDataUrl = "https://api.openweathermap.org/data/2.5/air_pollution?lat="+latitude+"&lon="+longitude+"&appid="+appID;

        try {
            //getting current data json
            url = new URL(currentDataUrl);
            URLConnection conn = url.openConnection();
            conn.setConnectTimeout(25000);
            conn.setReadTimeout(25000);
            conn.connect();

            BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            StringBuilder sb = new StringBuilder();
            int cp;
            while((cp = rd.read()) != -1) {
                sb.append((char)cp);
            }
            rd.close();

            jsonArr[0] = new JSONObject(sb.toString());

            //getting pollution data json
            url = new URL(pollutionDataUrl);
            conn = url.openConnection();
            conn.setConnectTimeout(25000);
            conn.setReadTimeout(25000);
            conn.connect();

            rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            sb = new StringBuilder();
            cp = 0;
            while((cp = rd.read()) != -1) {
                sb.append((char)cp);
            }
            rd.close();

            jsonArr[1] = new JSONObject(sb.toString());

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("Throwing in finder");
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void parseJsonArr(WeatherInfoKeeper weather) {
        try{
            JSONObject current = jsonArr[0];
            JSONObject main = current.getJSONObject("main");

            weather.temp = main.getString("temp");
            weather.pressure = main.getString("pressure");
            weather.humidity = main.getString("humidity");
            weather.windSpeed = current.getJSONObject("wind").getString("speed");
            weather.city = current.getString("name");

            JSONObject pollution = jsonArr[1];
            JSONArray list = pollution.getJSONArray("list");
            JSONObject zero = list.getJSONObject(0);
            JSONObject components = zero.getJSONObject("components");
            weather.co = components.getString("co");
            weather.no = components.getString("no");
            weather.no2 = components.getString("no2");
            weather.o3 = components.getString("o3");
            weather.so2 = components.getString("so2");
            weather.pm2_5 = components.getString("pm2_5");
            weather.pm10 = components.getString("pm10");
            weather.nh3 = components.getString("nh3");

            System.out.println(weather.toString());
        } catch (JSONException e){
            e.printStackTrace();
        }
    }

}

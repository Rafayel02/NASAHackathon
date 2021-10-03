package am.threesmart.cowin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.Resources;
import android.os.Bundle;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.TileOverlay;
import com.google.android.gms.maps.model.TileOverlayOptions;
import com.google.maps.android.heatmaps.HeatmapTileProvider;
import com.google.maps.android.heatmaps.WeightedLatLng;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity {

    private static BitmapDescriptor bitmapDescriptor;
    private static Resources resources;
    private static InputStream inputStream;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        //Initialize fragment
        MapFragment fragment = new MapFragment();

//        try {
//            InformationFileManager.createFileIfNotExists(getApplicationContext());
//
//            System.out.println("*************************************************************");
//            InformationFileManager.readAllFile();
//            System.out.println("*************************************************************");
//
//            InformationFileManager.addFieldAndValue("a", "b");
//            System.out.println("*************************************************************");
//
//            InformationFileManager.readAllFile();
//            System.out.println("*************************************************************");
//        } catch (IOException e) {
//            System.out.println("exavch");
//            e.printStackTrace();
//        }

        //Open fragment
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.frame_layout, fragment)
                .commit();

        resources = getApplicationContext().getResources();

        try {
            inputStream = getResources().getAssets().open("yerevan_data.json");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static void onMapReady(final GoogleMap map) {
        try {
            readJsonAndDraw(map);
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    public static void readJsonAndDraw(GoogleMap map) throws JSONException {

        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

        StringBuilder sb = new StringBuilder();
        try {
            String line = reader.readLine();
            while (line != null) {
                sb.append(line);
                line = reader.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        List<WeightedLatLng> list = new ArrayList<>();

        JSONObject parser = new JSONObject(sb.toString());
        for (int i = 0; i <= 1749; i++) {
            JSONObject jsonObject = parser.getJSONObject(String.valueOf(i));

            if(jsonObject.getDouble("w") > 0) {
                list.add(
                        new WeightedLatLng(
                                new LatLng(
                                        jsonObject.getDouble("lat"),
                                        jsonObject.getDouble("long")),
                                jsonObject.getDouble("w")
                        )
                );
            }
        }

        map.addMarker(new MarkerOptions().position(new LatLng(40.140718, 44.585032)));

        HeatmapTileProvider provider = new HeatmapTileProvider.Builder()
                .weightedData(list)
                .build();

        TileOverlay overlay = map.addTileOverlay(new TileOverlayOptions().tileProvider(provider));

    }


}
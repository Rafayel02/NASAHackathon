package am.threesmart.cowin;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.TileOverlay;
import com.google.android.gms.maps.model.TileOverlayOptions;

import java.util.ArrayList;
import java.util.List;

import am.threesmart.cowin.filemanager.AuthFileManager;

public class HomeActivity extends AppCompatActivity {

    private static BitmapDescriptor bitmapDescriptor;
    private static Resources resources;
    private ImageView profileImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        //Initialize fragment
        MapFragment fragment = new MapFragment();

        //Open fragment
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.frame_layout, fragment)
                .commit();

        resources = getApplicationContext().getResources();

        profileImageView = findViewById(R.id.profile_image_view);

        profileImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this, ProfileActivity.class);
                startActivity(intent);
            }
        });
    }


    public static void onMapReady(final GoogleMap map) {
//        Bitmap bitmap = BitmapFactory.decodeResource(
//                resources,
//                R.drawable.l
//        );
//        bitmapDescriptor = BitmapDescriptorFactory.fromBitmap(bitmap);
//        LatLng latLng = new LatLng(10, 10);
//        map.addMarker(new MarkerOptions().position(latLng).title("test").icon(bitmapDescriptor).zIndex(6000).flat(true));

        List<LatLng> latLangs = new ArrayList<>();
        latLangs.add(new LatLng(10, 10));
        latLangs.add(new LatLng(10, 10));
        latLangs.add(new LatLng(10, 10));
        latLangs.add(new LatLng(10, 10));
        latLangs.add(new LatLng(10, 10));
        latLangs.add(new LatLng(10, 11));
        latLangs.add(new LatLng(10, 12));
        latLangs.add(new LatLng(10, 13));
        latLangs.add(new LatLng(10, 14));
        latLangs.add(new LatLng(10, 15));

//        HeatmapTileProvider provider = new HeatmapTileProvider.Builder()
//                .data(latLangs)
//                .build();
//
//        TileOverlay overlay = map.addTileOverlay(new TileOverlayOptions().tileProvider(provider));
    }


}
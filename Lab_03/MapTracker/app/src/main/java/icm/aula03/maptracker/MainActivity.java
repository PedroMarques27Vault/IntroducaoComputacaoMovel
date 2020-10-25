package icm.aula03.maptracker;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;

public class MainActivity extends AppCompatActivity {

    private static final int REQUEST_LOCATION_PERMISSION = 1 ;
    private static final String TAG = "getLocation";
    private FusedLocationProviderClient mFusedLocationClient;
    private TextView mLocationTextView;
    private Location mLastLocation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mLocationTextView= findViewById(R.id.textview_location);
        Button getLocation = findViewById(R.id.button_location);
        mFusedLocationClient= LocationServices.getFusedLocationProviderClient(this);
        getLocation.setOnClickListener(v -> {
            getLocation();
        });

    }
    private void getLocation() {
        if (ActivityCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]
                            {Manifest.permission.ACCESS_FINE_LOCATION},
                    REQUEST_LOCATION_PERMISSION);
        } else {
            Log.i(TAG, "Permission granted");


            mFusedLocationClient.getLastLocation().addOnSuccessListener(this, location -> {
                if (mFusedLocationClient.getLastLocation().isSuccessful())
                    Log.i(TAG, String.valueOf(mFusedLocationClient.getLastLocation().getResult()));
                Log.i(TAG, String.valueOf(location));
                if (location != null) {
                    mLastLocation = location;
                    mLocationTextView.setText(
                            getString(R.string.location_text,
                                    mLastLocation.getLatitude(),
                                    mLastLocation.getLongitude(),
                                    mLastLocation.getTime()));
                } else {
                    mLocationTextView.setText(R.string.no_location);
                }
            });

        }
    }
    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case REQUEST_LOCATION_PERMISSION:
                // If the permission is granted, get the location,
                // otherwise, show a Toast
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    getLocation();
                } else {
                    Toast.makeText(this,
                            R.string.location_permission_denied,
                            Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }


}
package icm.aula03.maptracker;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;

public class MainActivity extends AppCompatActivity implements FetchAddressTask.onTaskCompleted{

    private static final int REQUEST_LOCATION_PERMISSION = 1 ;
    private static final String TAG = "getLocation";

    private TextView mLocationTextView;
    //private Location mLastLocation;           Uncomment to Show Coordinates Instead
    private LocationCallback mLocationCallback;
    private ImageView mAndroidImageView;
    private AnimatorSet mRotateAnim;
    private Boolean mTrackingLocation = false;
    private Button mLocationButton;
    private String TRACKING_LOCATION_KEY = "tracking_location";

    @Override
    protected void onPause() {
        super.onPause();
        if (mTrackingLocation){
            stopTrackingLocation();
            mTrackingLocation=true;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (mTrackingLocation){
            startTrackingLocation();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mLocationTextView= findViewById(R.id.textview_location);
        mLocationButton = findViewById(R.id.button_location);

        mFusedLocationClient= LocationServices.getFusedLocationProviderClient(this);

        //Animation
        mAndroidImageView = (ImageView) findViewById(R.id.imageview_android);
        mRotateAnim = (AnimatorSet) AnimatorInflater.loadAnimator(this, R.animator.rotate);
        mRotateAnim.setTarget(mAndroidImageView);

        mLocationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!mTrackingLocation) {
                    startTrackingLocation();
                } else {
                    stopTrackingLocation();
                }
            }
        });
        if (savedInstanceState != null) {
            mTrackingLocation = savedInstanceState.getBoolean(
                    TRACKING_LOCATION_KEY);
        }



        //Get Location Periodically
        mLocationCallback = new LocationCallback() {
            @Override
            public void onLocationResult(LocationResult locationResult) {
                // If tracking is turned on, reverse geocode into an address
                if (mTrackingLocation) {
                    new FetchAddressTask(MainActivity.this, MainActivity.this).execute(locationResult.getLastLocation());
                }
            }
        };

    }



    private LocationRequest getLocationRequest() {                              //Update Location 
        LocationRequest locationRequest = new LocationRequest();
        locationRequest.setInterval(10000);
        locationRequest.setFastestInterval(5000);
        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        return locationRequest;
    }

    private void startTrackingLocation() {  //Refactored from getLastLocation()
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_LOCATION_PERMISSION);
        } else {
            mTrackingLocation = true;
            mFusedLocationClient.requestLocationUpdates(getLocationRequest(), mLocationCallback,null);


            mLocationTextView.setText(getString(R.string.address_text,getString(R.string.loading), System.currentTimeMillis()));
            mLocationButton.setText(R.string.stop_tracking_location);
            mRotateAnim.start();

            /*      Portion of Code gets Last Known Location
            Log.i(TAG, "Permission granted");

            mFusedLocationClient.getLastLocation().addOnSuccessListener(this, location -> {             //onSuccess callback
                if (mFusedLocationClient.getLastLocation().isSuccessful())
                    Log.i(TAG, String.valueOf(mFusedLocationClient.getLastLocation().getResult()));
                Log.i(TAG, String.valueOf(location));
                if (location != null) {



            //This Portion of Code obtains updates UI with Coordinates
            mLastLocation = location;
            mLocationTextView.setText(getString(R.string.location_text,
                            mLastLocation.getLatitude(),
                            mLastLocation.getLongitude(),
                            mLastLocation.getTime()));

            //This Portion Of Code Updates UI with Address Instead of Coordinates
                    new FetchAddressTask(MainActivity.this,MainActivity.this).execute(location);
                    mLocationTextView.setText(getString(R.string.address_text,
                            getString(R.string.loading),
                            System.currentTimeMillis()));
                } else {
                    mLocationTextView.setText(R.string.no_location);
                }
            });
            */
        }

    }
    private void stopTrackingLocation() {
        if (mTrackingLocation) {
            mTrackingLocation = false;
            mLocationButton.setText(R.string.start_tracking_location);
            mLocationTextView.setText(R.string.textview_hint);
            mRotateAnim.end();
            mFusedLocationClient.removeLocationUpdates(mLocationCallback);
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
                    startTrackingLocation();
                } else {
                    Toast.makeText(this,
                            R.string.location_permission_denied,
                            Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }


    @Override
    public void onTaskCompleted(String result) {
        mLocationTextView.setText(getString(R.string.address_text, result, System.currentTimeMillis()));
    }
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putBoolean(TRACKING_LOCATION_KEY, mTrackingLocation);
        super.onSaveInstanceState(outState);
    }
}
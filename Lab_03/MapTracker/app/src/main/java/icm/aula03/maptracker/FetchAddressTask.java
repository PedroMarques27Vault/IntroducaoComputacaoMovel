package icm.aula03.maptracker;

import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.AsyncTask;
import android.text.TextUtils;
import android.util.Log;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class FetchAddressTask extends AsyncTask<Location, Void, String> {
    private final String TAG = FetchAddressTask.class.getSimpleName();
    private Context mContext;
    private onTaskCompleted mListener;

    FetchAddressTask(Context applicationContext, onTaskCompleted listener) {
        mContext = applicationContext;
        mListener = listener;
    }

    @Override
    protected String doInBackground(Location... locations) {
        Geocoder geocoder = new Geocoder(mContext, Locale.getDefault());
        Location location = locations[0];          //Get parameter 0 from extended class which corresponds to argument 0

        List<Address> addresses = null;         //Will be filled with corresponding addresses
        String resultMessage = "";


        try {
            addresses = geocoder.getFromLocation(location.getLatitude(), location.getLongitude(), 1);

        } catch (IOException e) {
            resultMessage = mContext.getString(R.string.service_not_available);
            Log.e(TAG, resultMessage, e);

        } catch (IllegalArgumentException e) {
            resultMessage = mContext.getString(R.string.invalid_lat_long_used);
            Log.e(TAG, resultMessage + ". Latitude = " + location.getLatitude() + ", Longitude = " + location.getLongitude(), e);
        }

        if (addresses == null || addresses.size() == 0) {
            if (resultMessage.isEmpty()) {
                resultMessage = mContext.getString(R.string.no_addresses_found);
                Log.e(TAG, resultMessage);
            }
        } else {
            Address adr = addresses.get(0);
            ArrayList<String> address_parts = new ArrayList<String>();

            for (int i = 0; i <= adr.getMaxAddressLineIndex(); i++) {
                address_parts.add(adr.getAddressLine(i));
            }

            resultMessage = TextUtils.join("\n", address_parts);

        }
        return resultMessage;
    }
    @Override
    protected void onPostExecute(String address) {
        mListener.onTaskCompleted(address);
        super.onPostExecute(address);
    }
    interface onTaskCompleted{
        void onTaskCompleted(String result);
    }
}


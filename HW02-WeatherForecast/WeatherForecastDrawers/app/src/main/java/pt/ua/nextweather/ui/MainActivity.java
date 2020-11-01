package pt.ua.nextweather.ui;

import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import pt.ua.nextweather.R;
import pt.ua.nextweather.datamodel.City;
import pt.ua.nextweather.datamodel.Weather;
import pt.ua.nextweather.datamodel.WeatherType;
import pt.ua.nextweather.network.CityResultsObserver;
import pt.ua.nextweather.network.ForecastForACityResultsObserver;
import pt.ua.nextweather.network.IpmaWeatherClient;
import pt.ua.nextweather.network.WeatherTypesResultsObserver;

public class MainActivity extends AppCompatActivity {

    private TextView feedback;

    IpmaWeatherClient client = new IpmaWeatherClient();
    public HashMap<String, City> cities = new HashMap<>();
    private ArrayList<String> cities_list = new ArrayList<String>();
    public HashMap<Integer, WeatherType> weatherDescriptions;
    private ArrayList<Weather> forecast5days = new ArrayList<>();
    public String currentCity;
    private String current_fragment = "NONE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getCities();
        getWeatherDescription();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);





    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    private void getWeatherDescription() {
        client.retrieveWeatherConditionsDescriptions(new WeatherTypesResultsObserver() {
            @Override
            public void receiveWeatherTypesList(HashMap<Integer, WeatherType> descriptorsCollection) {
                MainActivity.this.weatherDescriptions = descriptorsCollection;
                for (int k: weatherDescriptions.keySet()){
                    Log.i("getWeatherDescription", String.format("%d -> %s", k, weatherDescriptions.get(k)));
                }
            }
            @Override
            public void onFailure(Throwable cause) {
                Log.e("getWeatherDescription","Failed to get weather conditions!");
            }
        });

    }
    public void getCities() {
        client.retrieveCitiesList(new CityResultsObserver() {
            @Override
            public void receiveCitiesList(HashMap<String, City> citiesCollection) {
                MainActivity.this.cities = citiesCollection;
                Log.i("getCities", "getting cities");
                if (MainActivity.this.cities_list.isEmpty()){
                    for (String str: citiesCollection.keySet()){
                        MainActivity.this.cities_list.add(str);
                    }
                    MainActivity.this.displayFragment();
                }
            }
            @Override
            public void onFailure(Throwable cause) {
                Log.e("getCities", "Failed to get cities list!");
            }
        });
    }



    public void getWeatherForecast(String city) {
        City cityFound = cities.get(city);
        int locationId;
        if( null != cityFound) {
            locationId = cityFound.getGlobalIdLocal();
        } else {
            Log.e("getWeatherForecast", "Unknown City " + city);
            return;
        }
        Log.i("displayFragmentForecast", "Getting Forecast to display for city " + city);
        forecast5days.clear();
        currentCity = city;

        client.retrieveForecastForCity(locationId, new ForecastForACityResultsObserver() {
            @Override
            public void receiveForecastList(List<Weather> forecast) {
                MainActivity.this.forecast5days = (ArrayList<Weather>) forecast;
                displayFragmentForecast(currentCity);
            }
            @Override
            public void onFailure(Throwable cause) {
                Log.i("getWeatherForecast", "Failed to get forecast for 5 days");
            }
        });

    }
    public void displayFragment() {
        Bundle bundle = new Bundle();
        bundle.putSerializable("citiesList", cities_list);
        Log.i("Inflating", "Fragment City List");
        CitiesFragment cf = CitiesFragment.newInstance(this);
        cf.setArguments(bundle);
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager
                .beginTransaction();
        fragmentTransaction.add(R.id.fragment_citiesList,
                cf).commit();
    }
    public void displayFragmentForecast(String city){
        current_fragment = "FORECAST";
        Bundle bundle = new Bundle();
        HashMap<String, Object> data = new HashMap<>();
        City currentCity = cities.get(city);
        data.put("Local",currentCity.getLocal());
        data.put("GlobalID",currentCity.getGlobalIdLocal());
        data.put("Lat",currentCity.getLatitude());
        data.put("Long",currentCity.getLongitude());

        bundle.putSerializable("city", data);
        bundle.putSerializable("forecast5days", forecast5days);
        bundle.putSerializable("weather_descript", weatherDescriptions);
        CityWeatherFragment cwf = CityWeatherFragment.newInstance();
        cwf.setArguments(bundle);
        Log.i("forecast", forecast5days.toString());

        FragmentManager fragmentManager = getSupportFragmentManager();
        findViewById(R.id.fragment_weather).bringToFront();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.fragment_weather, cwf).commit();

    }

    @Override
    public void onBackPressed() {
        if (current_fragment.equals("NONE"))
            finish();
        else{
            findViewById(R.id.fragment_citiesList).bringToFront();
            current_fragment = "NONE";
        }

    }


}

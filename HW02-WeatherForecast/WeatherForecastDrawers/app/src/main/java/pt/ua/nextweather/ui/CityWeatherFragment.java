package pt.ua.nextweather.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.HashMap;

import pt.ua.nextweather.R;
import pt.ua.nextweather.datamodel.City;
import pt.ua.nextweather.datamodel.Weather;
import pt.ua.nextweather.datamodel.WeatherType;

public class CityWeatherFragment extends Fragment {
        private ArrayList<Weather> forecast5days = new ArrayList<>();
        private HashMap<Integer, WeatherType> weatherDescriptions = new HashMap();
        public HashMap<String, Object> selectedCity = null;
        public CityWeatherFragment(){

        }
        public static CityWeatherFragment newInstance() {
            CityWeatherFragment fragment = new CityWeatherFragment();
            return fragment;
        }

        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
            View rootView =  inflater.inflate(R.layout.fragment_forecast, container, false);
            Bundle bundle = this.getArguments();
            selectedCity = (HashMap<String, Object>) bundle.getSerializable("city");
            forecast5days= (ArrayList<Weather>) bundle.getSerializable("forecast5days");
            weatherDescriptions = (HashMap<Integer, WeatherType>) bundle.getSerializable("weather_descript");
            RecyclerView display_forecast = (RecyclerView) rootView.findViewById(R.id.recycler_forecast);

            CityWeatherRecyclerAdapter forecastAdapter = new CityWeatherRecyclerAdapter(getContext(), forecast5days, weatherDescriptions);
            display_forecast.setAdapter(forecastAdapter);
            display_forecast.setLayoutManager(new LinearLayoutManager(getActivity()));


            TextView cityName = (TextView) rootView.findViewById(R.id.city_textView);
            cityName.setText(selectedCity.get("Local").toString());
            TextView cityDescription = (TextView) rootView.findViewById(R.id.city_description_textView);
            String txtDescription = String.format(">%d\n\t(%f, %f)",(int)selectedCity.get("GlobalID"), (double)selectedCity.get("Lat"),(double)selectedCity.get("Long"));
            cityDescription.setText(txtDescription);
            return rootView;
        }





        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);

        }





}

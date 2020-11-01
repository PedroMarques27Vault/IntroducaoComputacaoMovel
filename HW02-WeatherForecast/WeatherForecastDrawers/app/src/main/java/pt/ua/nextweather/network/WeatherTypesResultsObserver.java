package pt.ua.nextweather.network;

import java.util.HashMap;

import pt.ua.nextweather.datamodel.WeatherType;

public interface WeatherTypesResultsObserver {
    public void receiveWeatherTypesList(HashMap<Integer, WeatherType> descriptorsCollection);
    public void onFailure(Throwable cause);
}

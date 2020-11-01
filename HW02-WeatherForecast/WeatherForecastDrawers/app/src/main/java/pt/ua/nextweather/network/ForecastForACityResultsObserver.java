package pt.ua.nextweather.network;

import java.util.HashMap;
import java.util.List;

import pt.ua.nextweather.datamodel.Weather;
import pt.ua.nextweather.datamodel.WeatherType;

public interface ForecastForACityResultsObserver {
    public void receiveForecastList(List<Weather> forecast);
    public void onFailure(Throwable cause);
}

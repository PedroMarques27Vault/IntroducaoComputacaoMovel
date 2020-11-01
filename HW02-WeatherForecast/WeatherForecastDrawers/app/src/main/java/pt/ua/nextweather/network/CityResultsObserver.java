package pt.ua.nextweather.network;

import java.util.HashMap;

import pt.ua.nextweather.datamodel.City;

public interface  CityResultsObserver {
    public void receiveCitiesList(HashMap<String, City> citiesCollection);
    public void onFailure(Throwable cause);
}

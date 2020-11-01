package pt.ua.nextweather.datamodel;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CityGroup {

    @Expose
    @SerializedName("owner")
    private String owner;

    @Expose
    @SerializedName("country")
    private String country;

    @Expose
    @SerializedName( "data")
    private List<City> cities;

    public CityGroup(String owner, String country, List<City> cities) {
        this.owner = owner;
        this.country = country;
        this.cities = cities;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public List<City> getCities() {
        return cities;
    }

    public void setCities(List<City> cities) {
        this.cities = cities;
    }
}

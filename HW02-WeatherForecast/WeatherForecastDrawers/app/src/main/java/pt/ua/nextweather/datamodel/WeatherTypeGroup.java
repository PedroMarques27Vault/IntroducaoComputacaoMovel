package pt.ua.nextweather.datamodel;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;


public class WeatherTypeGroup {

    @Expose
    @SerializedName("owner")
    private String owner;

    @Expose
    @SerializedName("data")
    private List<WeatherType> types;

    public WeatherTypeGroup(String owner, List<WeatherType> types) {
        this.owner = owner;
        this.types = types;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public List<WeatherType> getTypes() {
        return types;
    }

    public void setTypes(List<WeatherType> types) {
        this.types = types;
    }

    @Override
    public String toString() {
        return "WeatherTypeParent{" +
                "owner=" + owner +
                ", types=" + types +
                '}';
    }
}

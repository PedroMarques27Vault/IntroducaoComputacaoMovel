package pt.ua.nextweather.datamodel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class WeatherGroup {

    @Expose
    @SerializedName("globalIdLocal")
    private int globalIdLocal;

    @Expose
    @SerializedName("dataUpdate")
    private String dataUpdate;

    @Expose
    @SerializedName("data")
    private List<Weather> forecasts;

    public WeatherGroup(int globalIdLocal, String dataUpdate, List<Weather> forecasts) {
        this.globalIdLocal = globalIdLocal;
        this.dataUpdate = dataUpdate;
        this.forecasts = forecasts;
    }

    public int getGlobalIdLocal() {
        return globalIdLocal;
    }

    public void setGlobalIdLocal(int globalIdLocal) {
        this.globalIdLocal = globalIdLocal;
    }

    public String getDataUpdate() {
        return dataUpdate;
    }

    public void setDataUpdate(String dataUpdate) {
        this.dataUpdate = dataUpdate;
    }

    public List<Weather> getForecasts() {
        return forecasts;
    }

    public void setForecasts(List<Weather> forecasts) {
        this.forecasts = forecasts;
    }
}

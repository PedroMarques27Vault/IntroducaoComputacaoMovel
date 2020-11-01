package pt.ua.nextweather.ui;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.HashMap;

import pt.ua.nextweather.R;
import pt.ua.nextweather.datamodel.Weather;
import pt.ua.nextweather.datamodel.WeatherType;


class CityWeatherRecyclerAdapter extends RecyclerView.Adapter<CityWeatherRecyclerAdapter.ForecastViewHolder> {
    private LayoutInflater mInflater;
    private ArrayList<Weather> forecast;
    private HashMap<Integer,WeatherType> weatherTypes;
    public CityWeatherRecyclerAdapter(Context mContext,
                                      ArrayList<Weather> f5days, HashMap<Integer, WeatherType> weatherTypes) {
        mInflater = LayoutInflater.from(mContext);
        this.forecast = f5days;
        this.weatherTypes=weatherTypes;
    }


    @NonNull
    @Override
    public ForecastViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View mItemView = mInflater.inflate(R.layout.forecast_row, parent, false);
        return new ForecastViewHolder(mItemView, this);

    }

    @Override
    public void onBindViewHolder(@NonNull ForecastViewHolder holder, int position) {
        Weather cityForecast = forecast.get(position);
        holder.date.setText(cityForecast.getForecastDate());
        holder.windDirection.setText(cityForecast.getPredWindDir());
        holder.tmin.setText(String.valueOf(cityForecast.getTMin()));
        holder.tmax.setText(String.valueOf(cityForecast.getTMax()));
        holder.windSpeed.setText(String.valueOf(cityForecast.getClassWindSpeed()) + "knots");
        holder.probPrecipitation.setText(String.valueOf(cityForecast.getPrecipitaProb())+"%");
        WeatherType wt = weatherTypes.get(cityForecast.getIdWeatherType());
        holder.description.setText(String.valueOf(wt.getDescIdWeatherTypeEN() + "\n" + wt.getDescIdWeatherTypePT()));
    }

    @Override
    public int getItemCount() {
        return forecast.size();
    }


    class ForecastViewHolder extends RecyclerView.ViewHolder {
        public final TextView date, windDirection, tmin, tmax, windSpeed, probPrecipitation, description;
        final CityWeatherRecyclerAdapter mAdapter;

        public ForecastViewHolder(View itemView, CityWeatherRecyclerAdapter adapter) {
            super(itemView);
            date = itemView.findViewById(R.id.date);
            windDirection = itemView.findViewById(R.id.windDirection);
            tmin = itemView.findViewById(R.id.tmin);
            tmax = itemView.findViewById(R.id.tmax);
            windSpeed = itemView.findViewById(R.id.windSpeed);
            probPrecipitation = itemView.findViewById(R.id.probPrecipitation);
            description = itemView.findViewById(R.id.description);
            this.mAdapter = adapter;
        }

    }
}
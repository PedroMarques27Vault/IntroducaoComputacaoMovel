package pt.ua.nextweather.ui;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import pt.ua.nextweather.R;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

class CitiesRecyclerAdapter extends RecyclerView.Adapter<CitiesRecyclerAdapter.CitiesViewHolder> {
    private LayoutInflater mInflater;
    private ArrayList<String> cities = new ArrayList<>();
    public String selectedCity = null;
    private MainActivity mainActivity;

    public CitiesRecyclerAdapter(Context mContext, MainActivity mainActivity,
                                 ArrayList<String> cities) {
        mInflater = LayoutInflater.from(mContext);
        this.mainActivity = mainActivity;
        this.cities = cities;
    }


    @NonNull
    @Override
    public CitiesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View mItemView = mInflater.inflate(R.layout.city_row, parent, false);
        return new CitiesViewHolder(mItemView, this);

    }

    @Override
    public void onBindViewHolder(@NonNull CitiesRecyclerAdapter.CitiesViewHolder holder, int position) {
        String city_name = cities.get(position);
        holder.cityName.setText(city_name);

    }

    @Override
    public int getItemCount() {
        return cities.size();
    }


    class CitiesViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public final TextView cityName;
        final CitiesRecyclerAdapter mAdapter;

        public CitiesViewHolder(View itemView, CitiesRecyclerAdapter adapter) {
            super(itemView);
            cityName = itemView.findViewById(R.id.cityName);
            this.mAdapter = adapter;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            int mPosition = getLayoutPosition();
            String element = cities.get(mPosition);
            mainActivity.getWeatherForecast(element);

        }
    }
}
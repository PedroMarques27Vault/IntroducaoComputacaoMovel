package pt.ua.nextweather.ui;


import pt.ua.nextweather.R;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CitiesFragment extends Fragment {
    private ArrayList<String> cities = new ArrayList<>();
    public String selectedCity = null;
    private static MainActivity mainActivity;
    public CitiesFragment(){

    }
    public static CitiesFragment newInstance(MainActivity main) {
        CitiesFragment fragment = new CitiesFragment();
        mainActivity = main;
        return fragment;
    }
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View rootView =  inflater.inflate(R.layout.fragment_cities, container, false);
        Bundle bundle = this.getArguments();
        cities = (ArrayList<String>) bundle.getSerializable("citiesList");

        CitiesRecyclerAdapter citiesAdapter = new CitiesRecyclerAdapter(getContext(), mainActivity , cities);
        RecyclerView display_cities = (RecyclerView) rootView.findViewById(R.id.recycler_cities);
        display_cities.setAdapter(citiesAdapter);
        display_cities.setLayoutManager(new LinearLayoutManager(getActivity()));

        return rootView;
    }





    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

}

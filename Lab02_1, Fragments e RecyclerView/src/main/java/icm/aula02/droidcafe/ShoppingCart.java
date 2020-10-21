package icm.aula02.droidcafe;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.Dictionary;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ShoppingCart#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ShoppingCart extends Fragment {

    private static ArrayList<Food> shoppingCart;


    public ShoppingCart() {
        // Required empty public constructor
    }


    public static ShoppingCart newInstance(ArrayList<Food> sh_cart) {
        ShoppingCart fragment = new ShoppingCart();
        shoppingCart=sh_cart;
        System.out.println(shoppingCart.toString());
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView =  inflater.inflate(R.layout.fragment_shopping_cart, container, false);
        LinkedList<Food> linkedlist =new LinkedList<>();
        linkedlist.addAll(shoppingCart);
        ShoppingCartAdapter sca = new ShoppingCartAdapter(getActivity(), linkedlist);

        RecyclerView dispFood = (RecyclerView) rootView.findViewById(R.id.foodlist);
        dispFood.setAdapter(sca);
        dispFood.setLayoutManager(new LinearLayoutManager(getActivity()));
        return rootView;
    }
}
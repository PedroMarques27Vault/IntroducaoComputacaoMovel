package icm.aula02.droidcafe;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.View;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Dictionary;
import java.util.HashMap;
import java.util.LinkedList;

public class MainActivity extends AppCompatActivity {
    public ArrayList<Food> shopping_list = new ArrayList<Food>();

    private Boolean isShoppingListOpen = false;
    static final String SHOP_CAR_FRAGMENT = "STATE_OF_SC_FRAGMENT";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        FloatingActionButton fab = findViewById(R.id.fab);
        Food donut = new Food("Donut",1.5);
        Food froyo = new Food("Froyo",2.4);
        Food iceCream = new Food("IceCream",1.8);
        shopping_list.add(iceCream);
        shopping_list.add(froyo);
        shopping_list.add(donut);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!isShoppingListOpen)
                    displayFragment();
                else
                    closeFragment();
            }
        });
        if (savedInstanceState != null) {
            isShoppingListOpen =
                    savedInstanceState.getBoolean(SHOP_CAR_FRAGMENT);
            if (isShoppingListOpen) {
                // If the fragment is displayed, change button to "close".
                closeFragment();
            }
        }
    }
    public void onSaveInstanceState(Bundle savedInstanceState) {
        // Save the state of the fragment (true=open, false=closed).
        savedInstanceState.putBoolean(SHOP_CAR_FRAGMENT, isShoppingListOpen);
        super.onSaveInstanceState(savedInstanceState);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    public void displayToast(String message){
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }

    public void showDonutOrder(View view){
        displayToast(getString(R.string.donut_order_message));
        for (Food elem: shopping_list)
            if (elem.getName().equals("Donut")){
                elem.incAmount();
            }
    }

    public void showIceCreamOrder(View view){
        displayToast(getString(R.string.ice_cream_order_message));
        for (Food elem: shopping_list)
            if (elem.getName().equals("IceCream")){
                elem.incAmount();
            }


    }

    public void showFroyoOrder(View view){
        displayToast(getString(R.string.froyo_order_message));
        for (Food elem: shopping_list)
            if (elem.getName().equals("Froyo")){
                elem.incAmount();
            }
    }
    public void displayFragment() {
        ShoppingCart sh_cart = ShoppingCart.newInstance(shopping_list);
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager
                .beginTransaction();
        fragmentTransaction.add(R.id.fragment_shoppingCart_Container,
                sh_cart).addToBackStack(null).commit();
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setImageResource(R.drawable.ic_close);
        isShoppingListOpen = true;
    }
    public void closeFragment() {
        // Get the FragmentManager.
        FragmentManager fragmentManager = getSupportFragmentManager();
        // Check to see if the fragment is already showing.
        ShoppingCart sh_cart = (ShoppingCart) fragmentManager
                .findFragmentById(R.id.fragment_shoppingCart_Container);
        if (sh_cart != null) {
            // Create and commit the transaction to remove the fragment.
            FragmentTransaction fragmentTransaction =
                    fragmentManager.beginTransaction();
            fragmentTransaction.remove(sh_cart).commit();
        }
        // Update the Button text.
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setImageResource(R.drawable.ic_shopping_cart);
        // Set boolean flag to indicate fragment is closed.
        isShoppingListOpen = false;
    }
}
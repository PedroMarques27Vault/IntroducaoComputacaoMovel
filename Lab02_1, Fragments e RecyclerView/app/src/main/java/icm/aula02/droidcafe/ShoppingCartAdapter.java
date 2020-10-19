package icm.aula02.droidcafe;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.LinkedList;

class ShoppingCartAdapter extends RecyclerView.Adapter<ShoppingCartAdapter.FoodViewHolder>  {
    private LayoutInflater mInflater;
    private LinkedList<Food> shopping_list;
    public ShoppingCartAdapter(Context context,
                               LinkedList<Food> foodList) {
        mInflater = LayoutInflater.from(context);
        this.shopping_list = foodList;
    }


    @NonNull
    @Override
    public FoodViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View mItemView = mInflater.inflate(R.layout.food_element, parent, false);
        return new FoodViewHolder(mItemView, this);

    }

    @Override
    public void onBindViewHolder(@NonNull ShoppingCartAdapter.FoodViewHolder holder, int position) {
        Food mCurrentFood = shopping_list.get(position);
        holder.foodItemView.setText(mCurrentFood.getName());
    }
    
    @Override
    public int getItemCount() {
        return shopping_list.size();
    }


    class FoodViewHolder extends RecyclerView.ViewHolder {
        public final TextView foodItemView;
        final ShoppingCartAdapter mAdapter;
        public FoodViewHolder(View itemView, ShoppingCartAdapter adapter) {
            super(itemView);
            foodItemView = itemView.findViewById(R.id.nameText);
            this.mAdapter = adapter;
        }
    }
}

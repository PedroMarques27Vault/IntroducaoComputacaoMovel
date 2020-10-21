package icm.aula02.droidcafe;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
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
        holder.foodImage.setImageResource(mCurrentFood.getImage());
        String value = String.valueOf(mCurrentFood.getPrice());
        holder.priceView.setText(value);
        holder.amount_view.setText(String.valueOf(mCurrentFood.getAmount()));
        holder.descriptionView.setText(mCurrentFood.getDescription());
    }
    
    @Override
    public int getItemCount() {
        return shopping_list.size();
    }


    class FoodViewHolder extends RecyclerView.ViewHolder {
        public final TextView foodItemView, priceView, amount_view, descriptionView;
        public final ImageView foodImage;
        final ShoppingCartAdapter mAdapter;

        public FoodViewHolder(View itemView, ShoppingCartAdapter adapter) {
            super(itemView);
            foodItemView = itemView.findViewById(R.id.nameText);
            foodImage = (ImageView) itemView.findViewById(R.id.foodImage);
            priceView = itemView.findViewById(R.id.price);
            amount_view = (TextView) itemView.findViewById(R.id.amount);
            descriptionView = itemView.findViewById(R.id.description_text);
            this.mAdapter = adapter;
        }
    }
}

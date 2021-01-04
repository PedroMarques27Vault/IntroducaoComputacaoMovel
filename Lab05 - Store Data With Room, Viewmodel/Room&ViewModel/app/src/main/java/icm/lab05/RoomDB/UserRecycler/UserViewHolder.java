package icm.lab05.RoomDB.UserRecycler;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import icm.lab05.RoomDB.R;
import icm.lab05.RoomDB.entity.User;

public class UserViewHolder extends RecyclerView.ViewHolder {
        private final TextView usernameItemView, passwordItemView, emailItemView;

        private UserViewHolder(View itemView) {
            super(itemView);
            usernameItemView = itemView.findViewById(R.id.usernameView);
            passwordItemView = itemView.findViewById(R.id.passwordView);
            emailItemView = itemView.findViewById(R.id.emailView);
        }

        public void bind(User user) {
            usernameItemView.setText(user.getUsername());
            passwordItemView.setText(user.getPassword());
            emailItemView.setText(user.getEmail());
        }

        static UserViewHolder create(ViewGroup parent) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.recyclerview_item, parent, false);
            return new UserViewHolder(view);
        }
    }


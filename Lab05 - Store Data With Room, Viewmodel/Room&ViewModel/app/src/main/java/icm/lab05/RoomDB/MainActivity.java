package icm.lab05.RoomDB;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import icm.lab05.RoomDB.UserRecycler.UserListAdapter;
import icm.lab05.RoomDB.ViewModel.UserViewModel;
import icm.lab05.RoomDB.entity.User;

public class MainActivity extends AppCompatActivity {
    private UserViewModel mUserViewModel;
    public static final int NEW_USER_ACTIVITY_REQUEST_CODE = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RecyclerView recyclerView = findViewById(R.id.recyclerview);
        final UserListAdapter adapter = new UserListAdapter(new UserListAdapter.UserDiff());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        mUserViewModel = new UserViewModel(this.getApplication());

        mUserViewModel.getAllUsers().observe(this, users -> {
            // Update the cached copy of the words in the adapter.
            adapter.submitList(users);
        });

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener( view -> {
            Intent intent = new Intent(MainActivity.this, NewUserActivity.class);
            startActivityForResult(intent, NEW_USER_ACTIVITY_REQUEST_CODE);
        });

    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == NEW_USER_ACTIVITY_REQUEST_CODE && resultCode == RESULT_OK) {
            Bundle bundle = data.getBundleExtra(NewUserActivity.EXTRA_REPLY);

            User user = new User(bundle.getString("EMAIL"), bundle.getString("PASSWORD"), bundle.getString("USERNAME"));
            mUserViewModel.insert(user);
        } else {
            Toast.makeText(
                    getApplicationContext(),
                    R.string.empty_not_saved,
                    Toast.LENGTH_LONG).show();
        }
    }
}
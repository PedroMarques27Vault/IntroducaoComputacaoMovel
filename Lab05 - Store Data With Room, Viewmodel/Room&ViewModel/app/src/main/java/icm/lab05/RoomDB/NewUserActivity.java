package icm.lab05.RoomDB;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;

import icm.lab05.RoomDB.entity.User;

public class NewUserActivity extends AppCompatActivity {

    public static final String EXTRA_REPLY = "com.example.android.wordlistsql.REPLY";

    private EditText mEditUsername, mEditEmail, mEditPassword;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_user);
        mEditUsername = findViewById(R.id.edit_username);
        mEditEmail = findViewById(R.id.edit_email);
        mEditPassword = findViewById(R.id.edit_password);

        final Button button = findViewById(R.id.button_save);
        button.setOnClickListener(view -> {
            Intent replyIntent = new Intent();
            if (TextUtils.isEmpty(mEditEmail.getText())) {
                setResult(RESULT_CANCELED, replyIntent);
            } else {
                String email = mEditEmail.getText().toString();
                String username = mEditUsername.getText().toString();
                String password = mEditPassword.getText().toString();
                Bundle bundle = new Bundle();
                bundle.putString("USERNAME",username);
                bundle.putString("EMAIL",email);
                bundle.putString("PASSWORD",password);
                replyIntent.putExtra(EXTRA_REPLY, bundle);
                setResult(RESULT_OK, replyIntent);
            }
            finish();
        });
    }
}
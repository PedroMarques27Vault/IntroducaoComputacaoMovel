package ICM.Aula01.phonedialer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.io.Serializable;

public class add_contact extends AppCompatActivity {
    Button set;
    private String speedDial;
    private Contact contact;
    Intent it;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        it = getIntent();
        contact = new Contact();
        speedDial = it.getStringExtra("Cont");
        contact.setNumber(it.getStringExtra("Pn"));
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_contact);
        set = findViewById(R.id.button);
    }

    public void set(View v){
        contact.setName(findViewById(R.id.editTextPersonName).toString());
        contact.setNumber(findViewById(R.id.editTextPhone).toString());

        Intent returnIntent = new Intent();

        returnIntent.putExtra("SpDial", speedDial);
        returnIntent.putExtra("Contact_name", contact.getName());
        returnIntent.putExtra("Contact_number", contact.getNumber());

        if (contact.getName().length()>0 && contact.getNumber().length()>0){
            setResult(RESULT_OK,returnIntent);
            finish();
        }

    }
}
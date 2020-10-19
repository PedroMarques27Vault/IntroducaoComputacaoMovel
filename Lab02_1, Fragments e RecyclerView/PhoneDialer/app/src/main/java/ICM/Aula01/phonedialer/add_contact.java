package ICM.Aula01.phonedialer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
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
        contact.setNumber(it.getStringExtra("Phone_Number"));
        contact.setName(it.getStringExtra("Name"));



        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_contact);
        set = findViewById(R.id.button);
        EditText nme = (EditText) findViewById(R.id.editTextPersonName);
        EditText pn = (EditText) findViewById(R.id.editTextPhone);
        nme.setText(contact.getName());
        pn.setText(contact.getNumber());
    }

    public void set(View v){

        EditText name = (EditText)findViewById(R.id.editTextPersonName);
        contact.setName(name.getText().toString());
        EditText phone = (EditText)findViewById(R.id.editTextPhone);
        contact.setNumber(phone.getText().toString());


        Intent returnIntent = new Intent();

        returnIntent.putExtra("SpDial", speedDial);
        returnIntent.putExtra("Contact_name", contact.getName());
        returnIntent.putExtra("Contact_number", contact.getNumber());
        Log.w("ACTIVITY_RESULT", "Contact: " + returnIntent.getExtras());
        if (contact.getName().length()>0 && contact.getNumber().length()>0){
            setResult(RESULT_OK,returnIntent);
            finish();
        }

    }
}
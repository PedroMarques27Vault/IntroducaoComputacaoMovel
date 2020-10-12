package ICM.Aula01.phonedialer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    private Button one,two,three,four,five,six,seven,eight,nine,zero;
    public Button cont1,cont2,cont3;
    public Contact cone,ctwo,cthree;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        EditText pn = (EditText) findViewById(R.id.editTextPhone);
        pn.setText("");
        one = findViewById(R.id.One);
        two = findViewById(R.id.Two);
        three = findViewById(R.id.Three);
        four = findViewById(R.id.four);
        five = findViewById(R.id.five);
        six = findViewById(R.id.six);
        seven = findViewById(R.id.seven);
        eight = findViewById(R.id.eight);
        nine = findViewById(R.id.nine);
        zero = findViewById(R.id.zero);

        //Speed Dial
        cont1=findViewById(R.id.Cont1);
        cont2=findViewById(R.id.Cont2);
        cont3=findViewById(R.id.Cont3);
        cone = new Contact();
        ctwo = new Contact();
        cthree= new Contact();




        View.OnLongClickListener spdial = new Button.OnLongClickListener(){
                @Override
                public boolean onLongClick(View v) {
                    addSpeedDial(v);
                    return true;
                }

        };

        cont1.setOnLongClickListener(spdial);
        cont2.setOnLongClickListener(spdial);
        cont3.setOnLongClickListener(spdial);
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.w("ON_ACTIVITY_RESULT", "Activity Result: " + data.getStringExtra("Contact_name"));
        if (requestCode == 1) {

            if (resultCode == RESULT_OK) {
                String spDial = data.getStringExtra("SpDial");
                String name = data.getStringExtra("Contact_name");
                String number = data.getStringExtra("Contact_number");
                if (spDial.equals("Cont1")) {
                    cone.setNumber(number);
                    cone.setName(name);
                    cont1.setText(name);
                } else if (spDial.equals("Cont2")) {
                    ctwo.setNumber(number);
                    ctwo.setName(name);
                    cont2.setText(name);
                } else {
                    cthree.setNumber(number);
                    cthree.setName(name);
                    cont3.setText(name);
                }
            }
            if (resultCode == RESULT_CANCELED) {

            }
        }
    }//onActivityResult

    public void addNumber(View id){
        EditText pn = (EditText) findViewById(R.id.editTextPhone);
        String phoneNumber = pn.getText().toString();
        if (id == one){
            phoneNumber = phoneNumber + "1";
        }else  if (id == two){
            phoneNumber = phoneNumber + "2";
        }else  if (id == three){
            phoneNumber = phoneNumber + "3";
        }else  if (id == four){
            phoneNumber = phoneNumber + "4";
        }else  if (id == five){
            phoneNumber = phoneNumber + "5";
        }else  if (id == six){
            phoneNumber = phoneNumber + "6";
        }else  if (id == seven){
            phoneNumber = phoneNumber + "7";
        }else  if (id == eight){
            phoneNumber = phoneNumber + "8";
        }else  if (id == nine){
            phoneNumber = phoneNumber + "9";
        }else  if (id == zero){
            phoneNumber = phoneNumber + "0";
        }
        CharSequence cs = phoneNumber;
        pn.setText(cs);
    }

    public void delDigit(View v){
        EditText pn = (EditText) findViewById(R.id.editTextPhone);
        String phoneNumber = pn.getText().toString();
        if (phoneNumber.length()>0){
            pn.setText(phoneNumber.substring(0, phoneNumber.length() - 1));
        }


    }

    public void SpeedDial(View id){
        EditText pn = (EditText) findViewById(R.id.editTextPhone);
        if ( id == cont1 ){
            pn.setText(cone.getNumber());
        }else if( id == cont2 ){
            pn.setText(ctwo.getNumber());
        }else if (id == cont3 ){
            pn.setText(cthree.getNumber());
        }
    }


    public void addSpeedDial(View id){
        Intent it = new Intent(this, add_contact.class);
        EditText pn = (EditText) findViewById(R.id.editTextPhone);
        String phoneNumber = pn.getText().toString();


        if ( id == cont1 ){
            it.putExtra("Cont", "Cont1");
            it.putExtra("Name",cone.getName());
            if (!cone.getName().equals("Name")){
                it.putExtra("Phone_Number", cone.getNumber());
            }else if (phoneNumber.length()!=0){
                it.putExtra("Phone_Number", phoneNumber);
            }else{
                it.putExtra("Phone_Number", cone.getNumber());
            }
        }else if( id == cont2 ){
            it.putExtra("Cont", "Cont2");
            it.putExtra("Name",ctwo.getName());
            if (!ctwo.getName().equals("Name")){
                it.putExtra("Phone_Number", ctwo.getNumber());
            }else if (phoneNumber.length()!=0){
                it.putExtra("Phone_Number", phoneNumber);
            }else{
                it.putExtra("Phone_Number", ctwo.getNumber());
            }
        }else if (id == cont3 ){
            it.putExtra("Cont", "Cont3");
            it.putExtra("Name",cthree.getName());
            if (!ctwo.getName().equals("Name")){
                it.putExtra("Phone_Number", cthree.getNumber());
            }else if (phoneNumber.length()!=0){
                it.putExtra("Phone_Number", phoneNumber);
            }else{
                it.putExtra("Phone_Number", cthree.getNumber());
            }
        }

        startActivityForResult(it, 1);
    }

    public void dial(View v){
        EditText pn = (EditText) findViewById(R.id.editTextPhone);
        String phoneNumber = pn.getText().toString();
        if (phoneNumber.length()>0){
            Uri uri = Uri.parse("tel:" + phoneNumber);
            Intent intent = new Intent(Intent.ACTION_CALL, uri);

            startActivity(intent);
        }

    }

}
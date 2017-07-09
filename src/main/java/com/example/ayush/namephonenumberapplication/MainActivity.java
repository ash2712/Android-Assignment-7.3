package com.example.ayush.namephonenumberapplication;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.provider.ContactsContract;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ListView mListView;
    private ArrayList<Data> InfoArray;
    private TextView tv_phoneNumber;
    private CustomAdapter mCustomAdapter;
    private ArrayList<String> phoneNumber;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mListView = (ListView) findViewById(R.id.myList);
        InfoArray = new ArrayList<Data>();

        ArrayList<String> names = new ArrayList<>();
        names.add("Aaditya");
        names.add("Rahul");
        names.add("Karan");
        //items to be added as contacts

        phoneNumber = new ArrayList<>();
        phoneNumber.add("9876546774");
        phoneNumber.add("9876546775");
        phoneNumber.add("9876546776");
        //their phone numbers

        for (int i = 0; i < 3; i++) {
            Data data = new Data();
            data.setName(names.get(i));
            data.setPhoneNumber(phoneNumber.get(i));
            InfoArray.add(data);

        }

        mCustomAdapter = new CustomAdapter(MainActivity.this, InfoArray);
        //attaches listview to Array
        mListView.setAdapter(mCustomAdapter);
        registerForContextMenu(mListView);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        menu.setHeaderTitle("Share through");
        menu.add(0, 100, 1, "Call");
        menu.add(0, 101, 2, "SMS");
        //items added in context menu
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {

        Intent intent;
        tv_phoneNumber = (TextView) findViewById(R.id.tv_phoneNumber);
        String number = tv_phoneNumber.getText().toString();
        Uri personNumber = Uri.parse("tel:" + number);
        //stores the phone number as a uri


        if (item.getItemId() == 100) {

                    intent = new Intent(Intent.ACTION_DIAL, personNumber);
                    startActivity(intent);
            //allows the user to call the desired number
                }
        if(item.getItemId() == 101) {
                    intent = new Intent(Intent.ACTION_VIEW,personNumber);
            //brings up the sms page
                    startActivity(intent);

                }



        else{
                Toast.makeText(MainActivity.this, "not formed", Toast.LENGTH_SHORT).show();
            }
        return super.onContextItemSelected(item);


    }
}

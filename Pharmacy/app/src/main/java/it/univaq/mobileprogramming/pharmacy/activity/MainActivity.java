package it.univaq.mobileprogramming.pharmacy.activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import android.content.IntentFilter;
import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;


import java.util.ArrayList;
import java.util.List;


import it.univaq.mobileprogramming.pharmacy.R;
import it.univaq.mobileprogramming.pharmacy.database.Database;
import it.univaq.mobileprogramming.pharmacy.model.Pharmacy;
import it.univaq.mobileprogramming.pharmacy.utility.RequestService;
import it.univaq.mobileprogramming.pharmacy.utility.Settings;

public class MainActivity extends AppCompatActivity {
    private EditText editTextpharmacy;
    private Button buttonSearch;
    private Button buttonUpdate;


    BroadcastReceiver receiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {

            Toast.makeText(getApplicationContext(),"Ok DB Update",Toast.LENGTH_LONG).show();
        }
    };

    private List<Pharmacy> pharmacies = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextpharmacy = findViewById(R.id.text_pharmacy);
       buttonSearch = findViewById(R.id.button_confirm);
        buttonUpdate = findViewById(R.id.button_update);


    }
    public void update(View view) {

        clearDataFromDB();

        downloadData();
    }
    @Override
    protected void onStop() {
        super.onStop();

        LocalBroadcastManager.getInstance(getApplicationContext()).unregisterReceiver(receiver);
    }

    @Override
    protected void onResume() {
        super.onResume();

        LocalBroadcastManager.getInstance(getApplicationContext()).registerReceiver(receiver, new IntentFilter(RequestService.FILTER_REQUEST_DOWNLOAD) );

        // If is the first time you open the app, do a HTTP request to download the data
        if (Settings.loadBoolean(getApplicationContext(), Settings.FIRST_TIME, true)) {

            clearDataFromDB();

            downloadData();

        } else {

        }
        Settings.save(getApplicationContext(), Settings.FIRST_TIME, false);
    }




    private void downloadData() {

        System.out.println("sono qui");

        Intent intent = new Intent(getApplicationContext(), RequestService.class);
        intent.putExtra(RequestService.REQUEST_ACTION, RequestService.REQUEST_DOWNLOAD);
        startService(intent);


    }



    /**
     * Clear data from database. The preference defines if you use SQLiteOpenHelper or RoomDatabase
     */
    private void clearDataFromDB() {

        pharmacies.clear();
       // Delete by RoomDatabase

        new Thread(new Runnable() {
            @Override
            public void run() {
                Database.getInstance(getApplicationContext())
                        .getPharmacyDao().deleteAll();
            }
        }).start();
    }

    public void search(View view) {

         View parentView = (View) view.getParent();
        EditText text_pharmacy = parentView.findViewById(R.id.text_pharmacy);
        String query = text_pharmacy.getText().toString();
        if (query != "") {
            Intent intent = new Intent(MainActivity.this, DataActivity.class);
            intent.putExtra("query", query);
            startActivity(intent);
        }


    }




}
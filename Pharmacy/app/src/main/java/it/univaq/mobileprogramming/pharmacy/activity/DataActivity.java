package it.univaq.mobileprogramming.pharmacy.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

import it.univaq.mobileprogramming.pharmacy.R;
import it.univaq.mobileprogramming.pharmacy.activity.Adapter.AdapterRecycler;
import it.univaq.mobileprogramming.pharmacy.database.Database;
import it.univaq.mobileprogramming.pharmacy.model.Pharmacy;

public class DataActivity extends AppCompatActivity {


    private List<Pharmacy> pharmacies = new ArrayList<>();
    private AdapterRecycler adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data);

        adapter = new AdapterRecycler(pharmacies);
        RecyclerView list = findViewById(R.id.text_pharmacy);

        list.setLayoutManager(new LinearLayoutManager(this));
        list.setAdapter(adapter);

        final String descrCom = getIntent().getStringExtra("query");

        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("Prima della query");
                List<Pharmacy> data = Database.getInstance(getApplicationContext()).getPharmacyDao().getAllPharmacies(descrCom);
                pharmacies.addAll(data);

                System.out.println(data);

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (adapter != null) {
                            adapter.notifyDataSetChanged();
                        }
                    }
                });
            }


        }).start();
    }
}

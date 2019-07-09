package it.univaq.mobileprogramming.pharmacy.activity;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;


import it.univaq.mobileprogramming.pharmacy.R;

public class DetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        String Address = getIntent().getStringExtra("address");
        String Provence = getIntent().getStringExtra("Provence");
        String Cap = getIntent().getStringExtra("cap");
        String Region = getIntent().getStringExtra("region");
        String CodAsl = getIntent().getStringExtra("Cod Asl");


        TextView textaddress = findViewById(R.id.details_address);
        TextView textProvence = findViewById(R.id.details_prov);
        TextView textcap = findViewById(R.id.details_cap);
        TextView textregion = findViewById(R.id.details_region);
        TextView textCodAsl = findViewById(R.id.details_codasl);


        textaddress.setText(Address);
        textProvence.setText(Provence);
        textcap.setText(Cap);
        textregion.setText(Region);
        textCodAsl.setText(CodAsl);


    }

    public void onClick(View view) {

        String Lat = getIntent().getStringExtra("Lat");
        String Lon = getIntent().getStringExtra("Lon");
        String NamePharmacy = getIntent().getStringExtra("name");
        String Address = getIntent().getStringExtra("address");

        if(Lat.equals("-") || Lon.equals("-"))
        {
            Lat = "0,0";
            Lon = "0,0";
        }


        Intent intent = new Intent(view.getContext(),MapsActivity.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.putExtra("Lat", Lat);
        intent.putExtra("Lon",Lon);
        intent.putExtra("NamePharmacy",NamePharmacy);
        intent.putExtra("Adsress",Address);
        startActivity(intent);

    }
}
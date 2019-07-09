package it.univaq.mobileprogramming.pharmacy.utility;

import android.app.IntentService;
import android.content.Intent;
import androidx.annotation.Nullable;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import it.univaq.mobileprogramming.pharmacy.database.Database;
import it.univaq.mobileprogramming.pharmacy.model.Pharmacy;


public class RequestService extends IntentService {

    public static final int REQUEST_DOWNLOAD = 0;

    public static final String REQUEST_ACTION = "action";
    public static final String FILTER_REQUEST_DOWNLOAD = "filter_request_download";

    public RequestService() {
        super("RequestService");
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {

        if (intent == null) return;
        int action = intent.getIntExtra(REQUEST_ACTION, -1);
        switch (action) {
            case REQUEST_DOWNLOAD:
                downloadData();
                break;
        }
    }

    private void downloadData() {

        String address = "http://www.dati.salute.gov.it/imgs/C_17_dataset_5_download_itemDownload0_upFile.CSV";

        // Check if the address is an URL
        URL url = null;
        try {
            url = new URL(address);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        if (url == null) return;

        // Do the GET Request
        HttpURLConnection connection = null;
        try {
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            // Read the response from the right stream
            int code = connection.getResponseCode();
            if (code == HttpURLConnection.HTTP_OK) {

                InputStream is = connection.getInputStream();

                List<Pharmacy> pharmacies = new ArrayList<>();
                BufferedReader br = new BufferedReader(new InputStreamReader(is));
                String line = null;
                while((line = br.readLine()) != null){
                    parseLine(line,pharmacies);

                }
                //saveDataInDB(pharmacies);
                Database.getInstance(getApplicationContext())
                        .getPharmacyDao().save(pharmacies);
                System.out.println("COMPLETATO");
                // Send the response in broadcast to all components of only this application.
                Intent intent = new Intent(FILTER_REQUEST_DOWNLOAD);
                LocalBroadcastManager.getInstance(getApplicationContext())
                        .sendBroadcast(intent);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // Remember to disconnect the connection
            if (connection != null) connection.disconnect();
        }
    }

    private void parseLine(String line, List<Pharmacy> pharmacies) {
        try {
            String[] columns = line.split(";");

            if (columns[15].equals("-")) {
                Pharmacy pharmacy = new Pharmacy();


                pharmacy.setCodasl(columns[1]);
                pharmacy.setAddress(columns[2]);
                pharmacy.setDescr(columns[3]);
                pharmacy.setPiva(columns[4]);
                pharmacy.setCap(columns[5]);
                pharmacy.setCodcom(columns[6]);
                pharmacy.setDescrCom(columns[7]);
                pharmacy.setFrazione(columns[8]);
                pharmacy.setCodprov(columns[9]);
                pharmacy.setSiglaProv(columns[10]);
                pharmacy.setProv(columns[11]);
                pharmacy.setCodReg(columns[12]);
                pharmacy.setDescrReg(columns[13]);
                pharmacy.setDateStart(columns[14]);
                pharmacy.setDateEnd(columns[15]);
                pharmacy.setType(columns[16]);
                pharmacy.setCodType(columns[17]);
                pharmacy.setLatitude(columns[18]);
                pharmacy.setLongitude(columns[19]);
                pharmacy.setLocalize(columns[20]);
                pharmacies.add(pharmacy);

            }
        } catch (Exception e){
            System.out.println(line);
            e.printStackTrace();
        }
    }

    private void saveDataInDB(final List<Pharmacy> pharmacy) {


        // Save by RoomDatabase
        new Thread(new Runnable() {
            @Override
            public void run() {
                Database.getInstance(getApplicationContext())
                        .getPharmacyDao().save(pharmacy);
            }
        }).start();
    }
}

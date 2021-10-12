package com.example.a42;

import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;


import androidx.appcompat.app.AppCompatActivity;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;

import android.os.AsyncTask;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import javax.net.ssl.HttpsURLConnection;

public class Depart extends AppCompatActivity {

    ListView lw1;
    TextView tv1;
    AdapterDep arad;
    public static double VALUTE;
    @Override
    public void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.depart);
        lw1 = (ListView)findViewById(R.id.listview2);
        arad = new AdapterDep(getApplicationContext());

        new Depart.getDepart().execute();


        lw1.setAdapter(arad);
    }
    public void CloseFormDepart(View view){
    finish();
    }

    private class getDepart extends AsyncTask<Void,Void,String>{


        @Override
        protected String doInBackground(Void... voids) {
            try {
          URL url =new URL("https://ayayay-ay.ru/wsr_banks/bankomats/");
                HttpsURLConnection connection =(HttpsURLConnection)url.openConnection();
                BufferedReader reader= new BufferedReader(new InputStreamReader(connection.getInputStream()));
                StringBuilder result  = new StringBuilder();
                String line ="";

                while ((line = reader.readLine()) != null){


                        result.append(line);

                }


                return result.toString();
            }
            catch(Exception ex)
            {

                Log.e("kek",ex.toString());
                return null;
            }
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            try {
              //  s = s.substring(2,s.length()-2);
                s =  "{ \"mass\": " + s +" }";

                JSONObject obj = new JSONObject(s);
                JSONArray tempArray = obj.getJSONArray("mass");
               for(int i =0; i< tempArray.length(); i++){
                    JSONObject hotelJson = tempArray.getJSONObject(i);
                   // Iterator<String> keys = hotelJson.keys();
                   // while(keys.hasNext()) {
                    //    String key = keys.next();
                       // if (hotelJson.get(key) instanceof JSONObject) {
                           // JSONObject kekl = (JSONObject) hotelJson.get(key);
                           DepNote tempnote = new  DepNote(hotelJson.getString("addr"),
                                   hotelJson.getInt("t"),
                                   hotelJson.getString("strt"),
                                   hotelJson.getString("fin"));
                            arad.notes.add(tempnote);
                            arad.notifyDataSetChanged();
                      //  }


                    //}
                   // break;
                }

            }
            catch(Exception ex){
                Log.e("kek2",ex.toString());
            }
        }
    }
}
package com.example.a42;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;

import javax.net.ssl.HttpsURLConnection;

public class Main extends AppCompatActivity {
    DialogMeow kekl;
    TextView tv1,usd,euro;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout);
        kekl = new DialogMeow();
        tv1  =(TextView)findViewById(R.id.mainData);
        usd = (TextView)findViewById(R.id.usd);
        euro = (TextView)findViewById(R.id.euro);
        tv1.setText(new SimpleDateFormat("dd.MM.yyyy").format(new Date()));
        new getUSD_EUR().execute();

    }

public void Money(View view){
    Intent intent = new Intent(this, money.class);
    startActivity(intent);
}

    public void Departmenter(View view){
        Intent intent = new Intent(this, Depart.class);
        startActivity(intent);
    }
    public void Enter(View view){
        FragmentManager manager = getSupportFragmentManager();
        DialogMeow myDialogFragment = new DialogMeow();
        myDialogFragment.show(manager, "myDialog");
    }

    private class getUSD_EUR  extends AsyncTask<Void,Void,String> {
        public double valut;
        @Override
        protected String doInBackground(Void... voids) {
            try {
                URL url =new URL("https://www.cbr-xml-daily.ru/daily_json.js");
                HttpsURLConnection connection =(HttpsURLConnection)url.openConnection();
                BufferedReader reader= new BufferedReader(new InputStreamReader(connection.getInputStream()));
                StringBuilder result  = new StringBuilder();
                String line ="";
                Integer i =0;
                while ((line = reader.readLine()) != null){

                    if(i>4 && i != 311) {
                        result.append(line);
                    }
                    i++;
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
                s = s.substring(4,s.length()-1);
                s =  "{ " + s +" } ] }";
                s =  s.substring(0,11) + " [ " + s.substring(11,s.length());
                JSONObject obj = new JSONObject(s);
                JSONArray tempArray = obj.getJSONArray("Valute");
                for(int i =0; i< tempArray.length(); i++){
                    JSONObject hotelJson = tempArray.getJSONObject(i);
                    Iterator<String> keys = hotelJson.keys();
                    while(keys.hasNext()) {
                        String key = keys.next();
                        if (hotelJson.get(key) instanceof JSONObject) {
                            JSONObject kekl = (JSONObject) hotelJson.get(key);
                            if(key.equals("USD")){
                                usd.setText(String.format("%.2f",kekl.getDouble("Value")));
                            }else if(key.equals("EUR")){
                                euro.setText(String.format("%.2f",kekl.getDouble("Value")));
                            }
                        }

                    }
                    break;
                }

            }
            catch(Exception ex){
                Log.e("kek2",ex.toString());
            }
        }
    }
}

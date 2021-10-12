package com.example.a42;

import android.os.AsyncTask;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;

import javax.net.ssl.HttpsURLConnection;

public class money extends AppCompatActivity {

    ListView lw1;
    TextView tv1;
    Adapter arad;
    public static double VALUTE;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.money);
        lw1 = (ListView)findViewById(R.id.listview1);
        tv1  =(TextView)findViewById(R.id.tvData);
        tv1.setText(new SimpleDateFormat("dd.MM.yyyy").format(new Date()));
        arad = new Adapter(getApplicationContext());

        new getMoney().execute();


        lw1.setAdapter(arad);
    }

    public void CloseForm(View view){
        finish();
    }
private class getMoney extends AsyncTask<Void,Void,String>{

    public Double valut;
    @Override
    protected String doInBackground(Void... voids) {
        try {

            URL urlu =new URL("https://ayayay-ay.ru/wsr_banks/valute/");
            HttpsURLConnection connectionu =(HttpsURLConnection)urlu.openConnection();
            BufferedReader readeru= new BufferedReader(new InputStreamReader(connectionu.getInputStream()));
            StringBuilder resultu  = new StringBuilder();
            String lineu ="";
            while ((lineu = readeru.readLine()) != null){
                resultu.append(lineu);
            }
            String lol = resultu.toString();
            lol = lol.substring(2,lol.length()-2);
            lol = "{ " + lol + " }";
            JSONObject obj = new JSONObject(lol);

            valut = obj.getDouble("coef");



            URL url =new URL("https://www.cbr-xml-daily.ru/daily_json.js");
            HttpsURLConnection connection =(HttpsURLConnection)url.openConnection();
            BufferedReader reader= new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuilder result  = new StringBuilder();
            String line ="";
            Integer i =0;
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

            JSONObject obj = new JSONObject(s).getJSONObject("Valute");
            Iterator<String> keys = obj.keys();

                while(keys.hasNext()) {
                    String key = keys.next();
                    if (obj.get(key) instanceof JSONObject) {
                        JSONObject kekl = obj.getJSONObject(key);
                        Note tempnote = new Note(kekl.getString("CharCode"),
                                kekl.getInt("Nominal"),
                                kekl.getString("Name"),
                                kekl.getDouble("Value"),
                                kekl.getDouble("Previous"),valut);
                        arad.notes.add(tempnote);
                        arad.notifyDataSetChanged();
                    }

 /*               */
                }

        }
        catch(Exception ex){
            Log.e("kek2",ex.toString());
        }
    }
}

}

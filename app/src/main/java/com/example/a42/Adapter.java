package com.example.a42;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;



public class Adapter extends BaseAdapter {

   private Context context;
   protected final List<Note> notes = new ArrayList<>();

   public Adapter(Context context){
       this.context = context;
   }

    @Override
    public int getCount() {
        return notes.size();
    }

    @Override
    public Object getItem(int position) {
        return notes.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(context);
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.dollar, parent, false);
        }
        TextView text = (TextView) convertView.findViewById(R.id.moneyShort);
        text.setText(notes.get(position).getCharCode());


        ImageView IMG = (ImageView)convertView.findViewById(R.id.IMGKEK);
        String found = text.getText().toString().substring(0,2).toLowerCase();

        try {
            // получаем входной поток
            InputStream ims = context.getAssets().open(found+".png");
            // загружаем как Drawable
            Drawable d = Drawable.createFromStream(ims, null);
            // выводим картинку в ImageView
            IMG.setImageDrawable(d);
        }
        catch( Exception ex) {

        }





        TextView longtext = (TextView) convertView.findViewById(R.id.moneyName);
        longtext.setText(notes.get(position).getName());

        TextView buy = (TextView) convertView.findViewById(R.id.moneyBuy);
        buy.setText(String.valueOf(notes.get(position).getValue()));
        TextView sell = (TextView) convertView.findViewById(R.id.moneySell);
        double coef = notes.get(position).getValue() + notes.get(position).getCoef() * notes.get(position).getValue();
        String cc = String.valueOf(coef);
        sell.setText(cc.substring(0,7));
        if(notes.get(position).getValue() > notes.get(position).getPrevious())
        {
            ImageView buyimg = (ImageView)convertView.findViewById(R.id.buyImage);
            buyimg.setImageResource(R.drawable.greenarrow);

            ImageView sellimg = (ImageView)convertView.findViewById(R.id.sellImage);
            sellimg.setImageResource(R.drawable.greenarrow);
        }
        else
        {
            ImageView buyimg = (ImageView)convertView.findViewById(R.id.buyImage);
            buyimg.setImageResource(R.drawable.redarrow);

            ImageView sellimg = (ImageView)convertView.findViewById(R.id.sellImage);
            sellimg.setImageResource(R.drawable.redarrow);
        }

        return convertView;
    }
}

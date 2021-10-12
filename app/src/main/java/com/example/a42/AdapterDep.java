package com.example.a42;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.InputStream;
import java.sql.Time;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AdapterDep extends BaseAdapter {
    private Context context;
    protected final List<DepNote> notes = new ArrayList<>();

    public AdapterDep(Context context){
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
            convertView = inflater.inflate(R.layout.address, parent, false);
        }

        TextView text = (TextView) convertView.findViewById(R.id.AddressName);
        text.setText(notes.get(position).getAddress());

        TextView bank = (TextView) convertView.findViewById(R.id.bankomat);
        if(notes.get(position).getStatus() == 1) {
            bank.setText("Банкомат");
        }
        else{
            bank.setText("Отделение");
        }
        TextView timer = (TextView) convertView.findViewById(R.id.STATUS);
        timer.setText("Работает");
        DateFormat format = new SimpleDateFormat("hh:mm");
        Time now = null;
        Time timeStart = null;
        Time timeEnd = null;
        try {
           now = new Time(format.parse(format.format(new Date())).getTime());
            timeStart =  new Time(format.parse(notes.get(position).getWorkStart()).getTime());
            timeEnd =  new Time(format.parse(notes.get(position).getWorkClose()).getTime());


        } catch (ParseException e) {
           Log.e("kek3",e.getMessage());
            e.printStackTrace();
        }

        if(now.after(timeStart) && now.before(timeEnd))
        {
            timer.setText("Работает");
             timer.setTextColor(context.getResources().getColor(R.color.sald));
        }
        else
        {
            timer.setText("Закрыто");
             timer.setTextColor(context.getResources().getColor(R.color.red));
        }
        TextView time = (TextView) convertView.findViewById(R.id.WorkTime);
        time.setText(notes.get(position).getWorkStart() + "-" +notes.get(position).getWorkClose());
        return convertView;
    }
}

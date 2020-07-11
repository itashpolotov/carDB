package com.example.musicshop.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.musicshop.R;
import com.example.musicshop.models.Order;

import java.util.List;

public class OrderAdapter extends ArrayAdapter<Order> {

    Context context;
    int resource;


    public OrderAdapter(@NonNull Context context, int resource, @NonNull List<Order> objects) {
        super(context, resource, objects);
        this.context=context;
        this.resource=resource;

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        String goodName = getItem(position).getGoodName();
        int goodQuantity=getItem(position).getGoodQuantity();
        double goodPrice=getItem(position).getGoodPrice();
        String goodUser=getItem(position).getUserName();

        //передача данных на новый layout для отображения списка

        LayoutInflater layoutInflater=LayoutInflater.from(context);
        convertView = layoutInflater.inflate(resource,parent,false);//converted to listview

        TextView tvGoodName=convertView.findViewById(R.id.tvGoodName);
        TextView tvGoodUser=convertView.findViewById(R.id.tvGoodUser);
        TextView tvGoodQuantity=convertView.findViewById(R.id.tvGoodQuantity);
        TextView tvGoodPrice=convertView.findViewById(R.id.tvGoodPrice);
        ImageView goodImage=convertView.findViewById(R.id.goodImage);

        tvGoodName.setText(goodName);
        tvGoodQuantity.setText(String.valueOf(goodQuantity));
        tvGoodPrice.setText(String.valueOf(goodPrice));
        tvGoodUser.setText(goodUser);

        switch (goodName){
            case "guitar":
                goodImage.setImageResource(R.drawable.guitar);
                break;
            case "keyboard":
                goodImage.setImageResource(R.drawable.keyboard);
                break;
            case "drums":
                goodImage.setImageResource(R.drawable.drums);
                break;
            case "rock":
                goodImage.setImageResource(R.drawable.rock);
                break;
        }
        return convertView;

    }
}

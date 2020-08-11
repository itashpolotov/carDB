package com.example.carsql;


import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class CAdapter extends RecyclerView.Adapter<CAdapter.Holder> {

    List<Car> carList;
    Context context;
    RecyclerOnClickListener listener;

    public CAdapter(Context context, List<Car> carList) {
        this.carList = carList;
        this.context = context;
    }
    public void setOnItemClickListener(RecyclerOnClickListener listener) {
        this.listener = listener;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {  //связь с макетом
        View view = LayoutInflater.from(context).inflate(R.layout.list_item, parent, false);

        return new Holder(view,listener);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {

        holder.carName.setText(carList.get(position).getCarName());

        holder.carPrice.setText(String.valueOf(carList.get(position).getCarPrice()));
        Log.i("MYTAG", String.valueOf(position));
        Log.i("MYTAG", carList.get(0).getCarName());
    }


    @Override
    public int getItemCount() {

        return carList.size();
    }

    public static class Holder extends RecyclerView.ViewHolder { //we need to create holder and then refer to it in definition of newly created class CarAdapter


        TextView carName;

        TextView carPrice;

        public Holder(@NonNull View itemView, final RecyclerOnClickListener listener) {
            super(itemView);

            carName = itemView.findViewById(R.id.carName);

            carPrice = itemView.findViewById(R.id.carPrice);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    listener.onItemClick(position);

                }
            });
        }




    }

    public interface RecyclerOnClickListener {
        void onItemClick(int position);




    }
}

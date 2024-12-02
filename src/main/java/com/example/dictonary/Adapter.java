package com.example.dictonary;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.MyHolder> {
    @NonNull
    List<Datalist>datalist;
    Context context;
    LayoutInflater layoutInflater;

    public Adapter(@NonNull List<Datalist> datalist, Context context) {
        this.datalist = datalist;
        this.context = context;
        this.layoutInflater =LayoutInflater.from(context);
    }


    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view=layoutInflater.inflate(R.layout.layout,parent,false);
        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder holder, int position) {

        holder.meaning.setText(datalist.get(position).getMeaning());
        holder.example.setText(datalist.get(position).getExample());
        holder.partsofspeech.setText(datalist.get(position).getPartsofspeech());

    }

    @Override
    public int getItemCount() {
        return datalist.size();
    }

    public class MyHolder extends RecyclerView.ViewHolder{

        TextView meaning,example,partsofspeech;

        public MyHolder(@NonNull View itemView) {
            super(itemView);

            meaning=itemView.findViewById(R.id.meaning);
            example=itemView.findViewById(R.id.example);
            partsofspeech=itemView.findViewById(R.id.partsofspeech);

        }
    }
}


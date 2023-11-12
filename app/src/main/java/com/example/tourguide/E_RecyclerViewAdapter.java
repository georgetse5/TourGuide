package com.example.tourguide;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class E_RecyclerViewAdapter extends RecyclerView.Adapter<E_RecyclerViewAdapter.MyViewHolder>{
    Context context;
    ArrayList <ExampleModel> exampleModels;


    public E_RecyclerViewAdapter(Context context, ArrayList<ExampleModel> exampleModels){
    this.context = context;
    this.exampleModels = exampleModels;
    }
    @NonNull
    @Override
    public E_RecyclerViewAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       //   This is where you inflate the layout (Giving a look to our rows)
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.recycler_view_row, parent,false);

        return new E_RecyclerViewAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull E_RecyclerViewAdapter.MyViewHolder holder, int position) {
        // assigning values to the views we created in the recycler_view_row layout file
        //based on the position of the recycler view
        holder.tvName.setText(exampleModels.get(position).getExamplename());
        holder.tv3Letter.setText(exampleModels.get(position).getExampleAbbreviation());
        holder.tv1Letter.setText(exampleModels.get(position).getExampleAbbreviation());
        holder.imageView.setImageResource(exampleModels.get(position).getImage());
    }

    @Override
    public int getItemCount() {
        // the recycler view just wants to know the number of items you want displayed
        return exampleModels.size();
    }


    public static class MyViewHolder extends RecyclerView.ViewHolder{
//grabbing the views from our recycler_view_row layout file
        //kinda like in the onCreate method


        ImageView imageView;
        TextView tvName, tv3Letter, tv1Letter;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.imageView);
            tvName = itemView.findViewById(R.id.textView);
            tv3Letter = itemView.findViewById(R.id.textView2);
            tv1Letter = itemView.findViewById(R.id.textView3);

        }
    }
}

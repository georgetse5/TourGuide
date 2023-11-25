package com.example.tourguide;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
    public class CustomAdapter extends RecyclerView.Adapter {
        ArrayList personNames;
        ArrayList personImages;
        Context context;
        public CustomAdapter(Context context, ArrayList personNames, ArrayList personImages) {
            this.context = context;
            this.personNames = personNames;
            this.personImages = personImages;
        }
        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            // infalte the item Layout
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.rowlayout, parent, false);
            // set the view's size, margins, paddings and layout parameters
            MyViewHolder vh = new MyViewHolder(v); // pass the view to View Holder
            return vh;
        }

        @Override
        public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        }

        @Override
        public void onBindViewHolder(MyViewHolder holder, @SuppressLint("RecyclerView") int position) {
            // set the data in items
            int pos = holder.getAdapterPosition();
            holder.name.setText((CharSequence) personNames.get(pos));
            holder.image.setImageResource(personImages.get(pos));
            // implement setOnClickListener event on item view.
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    // display a toast with person name on item click
                    Toast.makeText(context, (CharSequence) personNames.get(position), Toast.LENGTH_SHORT).show();
                }
            });
        }
        @Override
        public int getItemCount() {
            return personNames.size();
        }
        public class MyViewHolder extends RecyclerView.ViewHolder {
            // init the item view's
            TextView name;
            ImageView image;
            public MyViewHolder(View itemView) {
                super(itemView);
                // get the reference of item view's
                name = (TextView) itemView.findViewById(R.id.name);
                image = (ImageView) itemView.findViewById(R.id.image);
            }
        }
    }

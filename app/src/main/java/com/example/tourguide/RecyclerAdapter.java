package com.example.tourguide;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static final int TYPE = 1;
    private final Context context;
    private final List<Object> listRecyclerItem;

    public RecyclerAdapter(Context context, List<Object> listRecyclerItem) {
        this.context = context;
        this.listRecyclerItem = listRecyclerItem;
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder {

        private TextView name;
        private TextView vicinity;
        private TextView type;
        private TextView rating;
        private TextView image;

        public ItemViewHolder(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name);
            vicinity = itemView.findViewById(R.id.vicinity);
            rating = itemView.findViewById(R.id.rating);
            type = itemView.findViewById(R.id.type);
        }

    }
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        switch (i) {
            case TYPE:

            default:

                View layoutView = LayoutInflater.from(viewGroup.getContext()).inflate(
                        R.layout.recycler_layout, viewGroup, false);

                return new ItemViewHolder((layoutView));
        }

    }
    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {

        int viewType = getItemViewType(i);

        switch (viewType) {
            case TYPE:
            default:

                ItemViewHolder itemViewHolder = (ItemViewHolder) viewHolder;
                Sights sight = (Sights) listRecyclerItem.get(i);

                itemViewHolder.name.setText(sight.getName());
                itemViewHolder.rating.setText(sight.getRating());
                itemViewHolder.vicinity.setText(sight.getVicinity());
                itemViewHolder.type.setText(sight.getType());
        }

    }

    @Override
    public int getItemCount() {

        return listRecyclerItem.size();
    }

}


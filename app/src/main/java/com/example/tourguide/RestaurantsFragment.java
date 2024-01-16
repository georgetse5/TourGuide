package com.example.tourguide;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

public class RestaurantsFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View inflate = inflater.inflate(R.layout.activity_two, container, false);
        RecyclerView recyclerView = inflate.findViewById(R.id.recyclerview);

        List<Item> items = new ArrayList<Item>();
        items.add(new Item("Ελλήνων Γέυσεις","2321 098808",R.drawable.restaurant));
        items.add(new Item("Μαντάμ","2321 401366",R.drawable.restaurant));
        items.add(new Item("Κάππαρη","2321 020066",R.drawable.restaurant));
        items.add(new Item("Αντάμωμα","2321 071533",R.drawable.restaurant));
        items.add(new Item("Το καπηλειό του Κωστή","2321 058288",R.drawable.restaurant));
        items.add(new Item("Καθ'οδον","2321 023290",R.drawable.restaurant));
        items.add(new Item("Souvlaki Vlaxos"," 2321 058039",R.drawable.restaurant));
        items.add(new Item("Σουβλάκι Σώτος","2321 056211",R.drawable.restaurant));
        items.add(new Item("Family Kitchen","2321 022858",R.drawable.restaurant));
        items.add(new Item("Κουζίνα","2321 181000",R.drawable.restaurant));


        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));

        Log.d("ActivityTwo", "Number of items in the list: " + items.size());


        recyclerView.setAdapter(new MyAdapter(requireContext(), items));
        return inflate;
    }

}
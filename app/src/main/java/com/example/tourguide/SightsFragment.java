package com.example.tourguide;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

public class SightsFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View inflate = inflater.inflate(R.layout.activity_three, container, false);

        RecyclerView recyclerView = inflate.findViewById(R.id.recyclerview);

        List<Item> items = new ArrayList<Item>();
        items.add(new Item("Αυτοκινητοδρόμιο Σερρών","Πίστα αγώνων",R.drawable.sights));
        items.add(new Item("Αρχαιολογικό Μουσείο Σερρών (Μπεζεστένι)","Μουσείο",R.drawable.sights));
        items.add(new Item("Λαογραφικό μουσείο Καρακατσάνων","Μουσείο",R.drawable.sights));
        items.add(new Item("Ζιντζιρλί Τζαμί","Τζαμί",R.drawable.sights));
        items.add(new Item("Κοιλάδα Αγίων Αναργύρων","Αξιοθέατο",R.drawable.sights));
        items.add(new Item("Χιονοδρομικό κέντρο Λαϊλιά","Αξιοθέατο",R.drawable.sights));
        items.add(new Item("Ακρόπολη Σερρών (Cityzen)","Αξιοθέατο",R.drawable.sights));
        items.add(new Item("Ιερά Μονή Τιμίου Προδρόμου","Εκκλησία",R.drawable.sights));
        items.add(new Item("Γήπεδο Πανσερραϊκού","Αξιοθέατο",R.drawable.sights));
        items.add(new Item("Λουτρά σιδηροκάστρου","Ιαματικά Λουτρά",R.drawable.sights));

        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));

        Log.d("ActivityThree", "Number of items in the list: " + items.size());


        recyclerView.setAdapter(new MyAdapter(requireContext(), items));

        return inflate;

    }

}
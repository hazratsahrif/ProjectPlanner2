package com.example.projectplanner2.presentation.tabs;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.projectplanner2.databinding.FragmentHomeBinding;
import com.example.projectplanner2.presentation.tabs.adapter.HomeAdapter;
import com.example.projectplanner2.presentation.tabs.model.HomeItem;

import java.util.ArrayList;

public class HomeFragment extends Fragment {

    FragmentHomeBinding binding;
    ArrayList<HomeItem> itemsList;
    HomeAdapter homeAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(inflater,container,false);
        itemsList = new ArrayList<>();
        initData();
        View view = binding.getRoot();
        return  view;
    }

    private void initData() {
        itemsList.add(new HomeItem("Jhon","2/2/23","100"));
        itemsList.add(new HomeItem("Jhon","2/2/23","200"));
        itemsList.add(new HomeItem("Jhon","2/2/23","400"));
        itemsList.add(new HomeItem("Jhon","2/2/23","600"));
        itemsList.add(new HomeItem("Jhon","2/2/23","100"));
        homeAdapter = new HomeAdapter(itemsList);

        binding.rv.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false));
        binding.rv.setHasFixedSize(true);
        binding.rv.setAdapter(homeAdapter);
    }
}
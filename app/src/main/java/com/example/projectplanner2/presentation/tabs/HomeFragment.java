package com.example.projectplanner2.presentation.tabs;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import com.example.projectplanner2.R;
import com.example.projectplanner2.Room.QuoteDataDao;
import com.example.projectplanner2.Room.QuoteDatabase;
import com.example.projectplanner2.TabFragmentDirections;
import com.example.projectplanner2.databinding.FragmentHomeBinding;
import com.example.projectplanner2.presentation.tabs.adapter.HomeAdapter;


import com.example.projectplanner2.presentation.tabs.model.HomeItem;
import com.example.projectplanner2.presentation.tabs.model.InsertHomeItemModel;

import java.util.ArrayList;

public class HomeFragment extends Fragment implements HomeAdapter.OnItemClick {

    FragmentHomeBinding binding;
    ArrayList<InsertHomeItemModel> itemsList;
    HomeAdapter homeAdapter;
    double totalCost=0;
    QuoteDatabase quoteDatabase;
    QuoteDataDao dataDao;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(inflater,container,false);
        itemsList = new ArrayList<>();
        quoteDatabase = QuoteDatabase.getInstance(getContext());
        dataDao = quoteDatabase.getDao();
        initData();
        View view = binding.getRoot();
        return  view;
    }

    private void initData() {
        totalCost = 0;
        itemsList.addAll(dataDao.getAllData());
        for (InsertHomeItemModel item : itemsList) {
            totalCost += Float.valueOf(item.getTotalCost());
        }
        binding.tvCost.setText(String.valueOf(totalCost));
        binding.tvDate.setText(itemsList.size() +" job");
        homeAdapter = new HomeAdapter(this,itemsList);
        binding.rv.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false));
        binding.rv.setHasFixedSize(true);
        binding.rv.setAdapter(homeAdapter);
    }


    @Override
    public void onClickListener(InsertHomeItemModel model) {
        NavController navController = NavHostFragment.findNavController(HomeFragment.this);
        NavDirections action = TabFragmentDirections.actionTabFragmentToCustomerInformationFragment(model);
        navController.navigate(action);

    }
}
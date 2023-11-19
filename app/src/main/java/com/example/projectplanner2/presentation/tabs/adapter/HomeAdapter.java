package com.example.projectplanner2.presentation.tabs.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projectplanner2.databinding.HomeLayoutBinding;
import com.example.projectplanner2.presentation.tabs.model.HomeItem;

import java.util.ArrayList;


public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.ViewHolder> {
    public HomeAdapter(ArrayList<HomeItem> listItem) {
        this.listItem = listItem;
    }

    HomeLayoutBinding binding;
    ArrayList<HomeItem> listItem;
    @NonNull
    @Override
    public HomeAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        binding = HomeLayoutBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull HomeAdapter.ViewHolder holder, int position) {

        HomeItem item = listItem.get(position);
        holder.binding.tvCost.setText(item.getCost());
        holder.binding.tvDate.setText(item.getDate());
        holder.binding.tvName.setText(item.getName());

    }

    @Override
    public int getItemCount() {
        return listItem.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        HomeLayoutBinding binding;


        public ViewHolder(HomeLayoutBinding itemView) {
            super(itemView.getRoot());
            binding = itemView;
        }
    }
}
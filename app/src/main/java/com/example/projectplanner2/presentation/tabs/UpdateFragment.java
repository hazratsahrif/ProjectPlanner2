package com.example.projectplanner2.presentation.tabs;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.projectplanner2.databinding.FragmentUpdateBinding;

public class UpdateFragment extends Fragment {
    FragmentUpdateBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentUpdateBinding.inflate(inflater,container,false);
        View view = binding.getRoot();
        return  view;
    }
}
package com.example.projectplanner2.presentation;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.projectplanner2.R;
import com.example.projectplanner2.Room.InsertQuoteData;
import com.example.projectplanner2.Room.QuoteDataDao;
import com.example.projectplanner2.Room.QuoteDatabase;
import com.example.projectplanner2.databinding.FragmentOrderBinding;
import com.example.projectplanner2.presentation.tabs.model.InsertHomeItemModel;
import com.example.projectplanner2.shared.DatePickerHelper;

public class OrderFragment extends Fragment {
     FragmentOrderBinding binding;
     DatePickerHelper datePickerHelper;

     private QuoteDatabase quoteDatabase;
     private QuoteDataDao quoteDataDao;
    InsertHomeItemModel argumentModel;


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
         argumentModel = OrderFragmentArgs.fromBundle(getArguments()).getArg();
        if (argumentModel != null) {
            binding.tvCost.setText("Total Cost: $"+argumentModel.getTotalCost());
        }
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        binding = FragmentOrderBinding.inflate(inflater,container,false);
        datePickerHelper = new DatePickerHelper(requireContext(),binding.tvEndDate);

        quoteDatabase = QuoteDatabase.getInstance(getContext());
        quoteDataDao = quoteDatabase.getDao();
        binding.btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(requireView()).navigateUp();
            }
        });
        binding.btnDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                datePickerHelper.showDatePicker();
            }
        });

        binding.btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isValidate()){

                    argumentModel.setDate(binding.tvEndDate.getText().toString());
                    argumentModel.setName(binding.tvName.getText().toString());
                    argumentModel.setPhoneNo(binding.tvPhone.getText().toString());

                    quoteDataDao.insert(argumentModel);
                    Toast.makeText(getContext(), "Data inserted", Toast.LENGTH_SHORT).show();
                    binding.tvEndDate.setText("");
                    binding.tvPhone.setText("");
                    binding.tvName.setText("");


                }

            }
        });
        return binding.getRoot();
    }

    private boolean isValidate() {
        if(binding.tvName.getText().toString().isEmpty()){
            binding.tvName.setError("This field is required");
            return false;
        }
        else if(binding.tvName.getText().toString().isEmpty()){
            binding.tvPhone.setError("This field is required");
            return false;
        } else if (binding.tvEndDate.getText().toString().isEmpty()) {

            Toast.makeText(getContext(), "Please select date", Toast.LENGTH_SHORT).show();
            return  false;
        }
        else {
            return  true;
        }
    }
}
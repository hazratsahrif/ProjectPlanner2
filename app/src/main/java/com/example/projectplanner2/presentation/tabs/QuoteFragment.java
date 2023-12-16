package com.example.projectplanner2.presentation.tabs;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.NavDirections;
import androidx.navigation.fragment.NavHostFragment;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.example.projectplanner2.Room.QuoteDatabase;
import com.example.projectplanner2.Room.UpdateDataDao;
import com.example.projectplanner2.TabFragmentDirections;
import com.example.projectplanner2.databinding.FragmentQuoteBinding;
import com.example.projectplanner2.presentation.invoice.model.DropDownModel;
import com.example.projectplanner2.presentation.invoice.model.InsertUpdateDataModel;
import com.example.projectplanner2.presentation.tabs.model.InsertHomeItemModel;
import com.example.projectplanner2.shared.Converters;
import com.example.projectplanner2.shared.GetPrice;
import com.google.android.material.slider.Slider;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class QuoteFragment extends Fragment {
    FragmentQuoteBinding binding;
    InsertHomeItemModel model;
    String selectedTypeofPaint="Washable";
    String selectedTypeofColor="White";
    String cost="$35";
    GetPrice getPrice;
    float seekBarValue=0;
    QuoteDatabase quoteDatabase;
    float totalCost =0;
    UpdateDataDao updateDao;
    float addedValue = 0;
    float defaultValue = 100;
    float previousValue = 0;
    float listerCost = 0;
    float finalPaintCostPerLiter = 0;
    int price =0;
    int labourCost = 10;
    int spinnerPosition = 0;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentQuoteBinding.inflate(inflater,container,false);

        getPrice = new GetPrice();
        quoteDatabase = QuoteDatabase.getInstance(getContext());
        updateDao = quoteDatabase.getUpdateDao();
        addDropDownData();
        getUpdateData();

        binding.spinnerTypeOfPaint.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                selectedTypeofPaint = adapterView.getItemAtPosition(position).toString();
//                String selectedOption = myArray[position];
//                price = getPrice.getDropDownList();
                finalPaintCostPerLiter = price * listerCost;

            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        binding.etPercentage.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                defaultValue = Float.parseFloat(s.toString());
            }
            @Override
            public void afterTextChanged(Editable s) {
            }
        });
        binding.btnHired.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(model!=null){
                    NavController navController = NavHostFragment.findNavController(QuoteFragment.this);
                    NavDirections action = TabFragmentDirections.actionTabFragmentToOrderFragment2(model);
                    navController.navigate(action);
                }
            }
        });
        View view = binding.getRoot();
        binding.seekBar.addOnChangeListener(new Slider.OnChangeListener() {
            @Override
            public void onValueChange(@NonNull Slider slider, float value, boolean fromUser) {
                seekBarValue = value;
                // Check if the new value is greater or lesser than the previous value
                if (seekBarValue > previousValue) {
                    // Increasing value
                    addedValue = 25; // Adjusted logic for increasing
                    binding.etPercentage.setText(String.valueOf(defaultValue + addedValue));
                } else {
                    // Decreasing value
                    addedValue = 25; // Adjusted logic for decreasing
                    binding.etPercentage.setText(String.valueOf(defaultValue - addedValue));
                }

                previousValue = seekBarValue;

            }
        });
        binding.spinnerColor.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                 selectedTypeofColor = adapterView.getItemAtPosition(position).toString();
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });
        binding.btnGenerate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isValidate()){

                    setTextData();
                }

            }
        });
        return  view;
    }

    private void addDropDownData() {

        List<DropDownModel> listOfDropDown = new ArrayList<>();
        listOfDropDown = getPrice.getDropDownList();


        Toast.makeText(requireContext(), "List"+listOfDropDown.size(), Toast.LENGTH_SHORT).show();
        if(listOfDropDown.size() > 0){
            InsertUpdateDataModel data = new InsertUpdateDataModel(
                    1,
                    "10",
                    "10",
                    0,
                    listOfDropDown
            );
            updateDao.insert(data);
        }

    }

    @Override
    public void onResume() {
        super.onResume();
        quoteDatabase = QuoteDatabase.getInstance(getContext());
        updateDao = quoteDatabase.getUpdateDao();

        getUpdateData();
    }

    private void getUpdateData() {
        List<InsertUpdateDataModel> dataItem = updateDao.getUpdateData(1);

        if(dataItem.isEmpty()){
        }
        else {
            Toast.makeText(requireContext(), "Price"+dataItem.get(0).getCostOfPaint(), Toast.LENGTH_SHORT).show();
            binding.spinnerTypeOfPaint.setSelection(dataItem.get(0).getSpinnerPosition());
            price = Integer.valueOf(dataItem.get(0).getCostOfPaint());
            labourCost = Integer.valueOf(dataItem.get(0).getCostPerManHour());
            ArrayAdapter<DropDownModel> adapter = new ArrayAdapter<>(requireContext(),
                    android.R.layout.simple_spinner_item, dataItem.get(0).getDownModelList());
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            binding.spinnerTypeOfPaint.setAdapter(adapter);

        }
    }

    private void setTextData() {

        float paintArea= Integer.valueOf(binding.etPaint.getText().toString());
        int totalHour = Integer.valueOf(binding.etTimeRequired.getText().toString());
        float percentage = Float.valueOf(binding.etPercentage.getText().toString());
        float finalPercentage = percentage/100;
         int hourAndLabourCost = (totalHour * labourCost);
         float priceOfPaintAndPaintArea = price * paintArea;
         totalCost = ((priceOfPaintAndPaintArea) + (hourAndLabourCost)) * finalPercentage;
         binding.tvPaintCost.setText(String.valueOf("Paint cost= " + price));

        model = new InsertHomeItemModel(
                0,
                "",
                "",
                "",
                binding.etPaint.getText().toString().trim(),
                selectedTypeofPaint,
                selectedTypeofColor,
                binding.etTimeRequired.getText().toString().trim(),
                finalPercentage,
                String.valueOf(totalCost).toString().trim(),
                String.valueOf(labourCost).toString().trim(),
                String.valueOf(priceOfPaintAndPaintArea).toString().trim()
        );
        binding.tvCost.setText("Total Cost:" + totalCost);


    }
    private boolean isValidate() {
         if(binding.etPaint.getText().toString().isEmpty()){
             binding.etPaint.setError("This field is required");
             return  false;
         }
        else if(binding.etTimeRequired.getText().toString().isEmpty()){
             binding.etTimeRequired.setError("This field is required");
             return  false;
         }

        else {
            return  true;
         }


    }
}
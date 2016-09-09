package com.shiz.flighttime.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.shiz.flighttime.R;
import com.shiz.flighttime.listener.TypeFlightPickerListener;


public class TypeFlightFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    // TODO: Rename and change types of parameters
    private boolean isDayParam = true;

    private RadioGroup radioGroup;
    private TypeFlightPickerListener typeFlightPickerListener;


    public TypeFlightFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @return A new instance of fragment TypeFlightFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static TypeFlightFragment newInstance(Boolean param1) {
        TypeFlightFragment fragment = new TypeFlightFragment();
        Bundle args = new Bundle();
        args.putBoolean(ARG_PARAM1, param1);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
             isDayParam = getArguments().getBoolean(ARG_PARAM1);
          }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_type_flight, container, false);
        radioGroup = (RadioGroup) view.findViewById(R.id.typeRadioGroup);
        if (isDayParam) {
            radioGroup.check(R.id.dayRadioButton);
            typeFlightPickerListener.onTypeFlightSelected(true);
        } else {
            radioGroup.check(R.id.nightRadioButton);
            typeFlightPickerListener.onTypeFlightSelected(false);
        }
       radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.dayRadioButton) {
                    typeFlightPickerListener.onTypeFlightSelected(true);
                } else
                    typeFlightPickerListener.onTypeFlightSelected(false);
            }
        });

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    public void addTypeFlightPickerListener(TypeFlightPickerListener listener) {
        this.typeFlightPickerListener = listener;
    }

}
package com.shiz.flighttime.fragment;

import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.shiz.flighttime.R;
import com.google.android.gms.common.api.Status;
import com.shiz.flighttime.listener.CityChangeListener;
import com.shiz.flighttime.listener.OnBackPressedListener;


public class CityNameFragment extends BaseNameFragment implements TextWatcher, CityNameView, OnBackPressedListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String TAG = CityNameFragment.class.getSimpleName();
    private String oldNameCity = null;
    private CityChangeListener cityChangeListener;
    private EditText locationTextView;
    private TextInputLayout nameLayout;
    private View view;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @return A new instance of fragment CityNameFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CityNameFragment newInstance(String param1) {
        CityNameFragment fragment = new CityNameFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);

        if (getArguments() != null) {
            oldNameCity = getArguments().getString(ARG_PARAM1);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_city_name, container, false);
        nameLayout = (TextInputLayout) view.findViewById(R.id.til);
        locationTextView = (EditText) view.findViewById(R.id.txt_location);
        locationTextView.addTextChangedListener(this);
        locationTextView.setFocusable(true);
        if (oldNameCity != null)
            locationTextView.setText(oldNameCity);
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    @Override
    public void onDestroyView() {
        cityChangeListener = null;
        super.onDestroyView();
    }

    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }

    @Override
    public void onPlaceSelected(String place) {
        if (cityChangeListener != null)
            cityChangeListener.onNameCityChange(place);
    }

    @Override
    public void onErrorPlaceSelection(Status status) {
        Log.e(TAG, "onError: Status = " + status.toString());
        Toast.makeText(getContext(), "Place selection failed: " + status.getStatusMessage(),
                Toast.LENGTH_SHORT).show();

    }

    public void addCityNameChangedListener(CityChangeListener cityChangeListener) {
        this.cityChangeListener = cityChangeListener;
    }

    @Override
    public void onBackPressed() {

    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
    }

    @Override
    public void afterTextChanged(Editable s) {
        Log.d(TAG, "afterTextChanged = " + s.toString());
        oldNameCity = s.toString();
        if (s.length() < 3) {
            nameLayout.setError(getString(R.string.min_three_symbol_error));
            nameLayout.setErrorEnabled(true);
        } else {
            nameLayout.setErrorEnabled(false);
        }
        if (cityChangeListener != null)
            cityChangeListener.onNameCityChange(s.toString());
}}


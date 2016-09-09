package com.shiz.flighttime.fragment;

import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TimePicker;

import com.shiz.flighttime.R;
import com.shiz.flighttime.listener.DatePickerListener;
import com.shiz.flighttime.listener.TimePickerListener;
import com.shiz.flighttime.utils.Constants;
import com.shiz.flighttime.utils.Formatter;

import java.util.Calendar;
import java.util.Date;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link android.support.v4.app.Fragment.InstantiationException} interface
 * to handle interaction events.
 * Use the {@link DateFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DateFragment extends Fragment implements View.OnClickListener, android.app.DatePickerDialog.OnDateSetListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String previousDate = "date";

    // TODO: Rename and change types of parameters
    private int datePickerType;
    private Date prevDate;
    private Calendar calendar;
    private Button editDate;
    private DatePickerListener datePickerListener;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @return A new instance of fragment DateFragment.
     */

    public static DateFragment newInstance(Date param1) {
        DateFragment fragment = new DateFragment();
        Bundle args = new Bundle();
        args.putSerializable(previousDate, param1);
        fragment.setArguments(args);
        return fragment;
    }

    public void addDatePickerListener(DatePickerListener listener) {
        this.datePickerListener = listener;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            prevDate = (Date) getArguments().getSerializable(previousDate);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_date, container, false);
        editDate = (Button) view.findViewById(R.id.button);
        calendar = Calendar.getInstance();
        if (prevDate != null)
            editDate.setText(Formatter.getDateFormat(prevDate));
        else
            editDate.setText(Formatter.getDateFormat(calendar));

        editDate.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View v) {
            Dialog picker = new android.app.DatePickerDialog(getActivity(), R.style.MyDatePickerDialogTheme, this,
                    calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
            picker.setTitle(getResources().getString(R.string.choose_flight_date));
            picker.show();
    }

    @Override
    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
        calendar.set(year, monthOfYear, dayOfMonth);
        editDate.setText(Formatter.getDateFormat(calendar));
        datePickerListener.onSelectDate(calendar);
    }
}

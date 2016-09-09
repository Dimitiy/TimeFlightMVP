package com.shiz.flighttime.fragment;

import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TimePicker;

import com.shiz.flighttime.R;
import com.shiz.flighttime.listener.TimePickerListener;
import com.shiz.flighttime.utils.Constants;
import com.shiz.flighttime.utils.Formatter;

import java.util.Calendar;

/**
 * Created by oldman on 05.09.16.
 */
public class DurationFragment extends Fragment implements View.OnClickListener, android.app.TimePickerDialog.OnTimeSetListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String previousDuration = "duration";
    // TODO: Rename and change types of parameters
    private long prevDuration = 0L;
    private Calendar calendar;
    private Button editDuration;
    private TimePickerListener timePickerListener;


    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @return A new instance of fragment DateFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static DurationFragment newInstance(long param1) {
        DurationFragment fragment = new DurationFragment();
        Bundle args = new Bundle();
        args.putLong(previousDuration, param1);
        fragment.setArguments(args);
        return fragment;
    }


    public void addTimePickerListener(TimePickerListener listener) {
        this.timePickerListener = listener;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            prevDuration = getArguments().getLong(previousDuration);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_date, container, false);
        editDuration = (Button) view.findViewById(R.id.button);
        calendar = Calendar.getInstance();
        if (prevDuration == 0L) {
            calendar.set(Calendar.HOUR_OF_DAY, Constants.BEGIN_COUNT_TIME_FLIGHT);
            calendar.set(Calendar.MINUTE, Constants.BEGIN_COUNT_TIME_FLIGHT);
            editDuration.setText(Formatter.getFormatDurationForFlight(getContext(), Constants.BEGIN_COUNT_TIME_FLIGHT, Constants.BEGIN_COUNT_TIME_FLIGHT));
        } else
            editDuration.setText(Formatter.getFormatDuration(getContext(), prevDuration));
        editDuration.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View v) {
        Dialog timePickerDialog = new android.app.TimePickerDialog(getActivity(), R.style.MyDatePickerDialogTheme, this, Constants.BEGIN_COUNT_TIME_FLIGHT, Constants.BEGIN_COUNT_TIME_FLIGHT,  true);
        timePickerDialog.show();
    }


    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        calendar.set(Calendar.HOUR_OF_DAY, hourOfDay);
        calendar.set(Calendar.MINUTE, minute);
        Log.d("DurationFragment", "hour" + hourOfDay + " " + minute);
        editDuration.setText(Formatter.getFormatDurationForFlight(getContext(),hourOfDay, minute));
        timePickerListener.onSelectTimeCount(Formatter.getCountMillis(hourOfDay, minute));
    }
}

package com.shiz.flighttime.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.shiz.flighttime.MyApplication;
import com.shiz.flighttime.R;
import com.shiz.flighttime.data.YearEntity;
import com.shiz.flighttime.holder.YearsViewHolder;
import com.shiz.flighttime.utils.Formatter;

import java.util.ArrayList;

/**
 * Created by oldman on 24.08.16.
 */
public class YearsCoverFlowAdapter extends RecyclerView.Adapter<YearsViewHolder> {

    private ArrayList<YearEntity> mData = new ArrayList<>(0);
    private TextView year,countHoursInYear, countDayHoursInYear, countNightHoursInYear;
    private Context context;

    public YearsCoverFlowAdapter(ArrayList<YearEntity> data) {
        mData = data;
        this.context = MyApplication.getContext();
    }

    public void swap(ArrayList<YearEntity> data) {
        if (mData != null) {
            mData.clear();
            mData.addAll(data);
            notifyDataSetChanged();
        }
    }

    @Override
    public YearsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        final View v = inflater.inflate(R.layout.item_year_view, parent, false);
        return new YearsViewHolder(v);
    }

    @Override
    public void onBindViewHolder(YearsViewHolder holder, int position) {
        year = holder.year;
        countHoursInYear = holder.countHoursInYear;
//        countDayHoursInYear =  holder.countDayHoursInYear;
//        countNightHoursInYear =  holder.countNightHoursInYear;

        year.setText(mData.get(position).getYears());
        countHoursInYear.setText(Formatter.getFormatDuration(context, mData.get(position).getCountHoursInYear()));
//        countDayHoursInYear.setText(String.format(context.getString(R.string.day_count_formater), Formatter.getFormatDuration(context, mData.get(position).getCountDayHours())));
//        countNightHoursInYear.setText(String.format(context.getString(R.string.night_count_formater), Formatter.getFormatDuration(context, mData.get(position).getCountNightHours())));
    }

    @Override
    public long getItemId(int pos) {
        return pos;
    }

    public YearEntity getItem(int pos) {
        return mData.get(pos);
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public String getYear() {
        return year.toString();
    }

    public TextView getCountHoursInYear() {
        return countHoursInYear;
    }
}

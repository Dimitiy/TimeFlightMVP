package com.shiz.flighttime.data;

import org.parceler.Parcel;

/**
 * Created by oldman on 24.08.16.
 */
@Parcel
public class YearEntity {
    String years;
    long countHoursInYear;
    long countDayHours;
    long countNightHours;

    public long getCountDayHours() {
        return countDayHours;
    }

    public void setCountDayHours(long countDayHours) {
        this.countDayHours = countDayHours;
    }

    public long getCountNightHours() {
        return countNightHours;
    }

    public void setCountNightHours(long countNightHours) {
        this.countNightHours = countNightHours;
    }

    public YearEntity(String years, long countHoursInYear, long countDayHours, long countNightHours) {
        this.years = years;
        this.countHoursInYear = countHoursInYear;
        this.countDayHours = countDayHours;
        this.countNightHours = countNightHours;
    }

    public YearEntity() {

    }

    public String getYears() {
        return years;
    }

    public long getCountHoursInYear() {
        return countHoursInYear;
    }

    public void setCountHoursInYear(long countHoursInYear) {
        this.countHoursInYear = countHoursInYear;
    }
}

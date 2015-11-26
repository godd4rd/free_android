package uk.co.twentytwonorth.utils.components.calendar;


import android.util.Pair;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by colinlight on 24/11/15.
 */
public class Bookings implements CalendarDataSource.CalendarPeriod {

    private Date mDate;
    private int mSelectedDay = 0;
    private ArrayList<CalendarDataSource.ICalendarItem> mCalendarItems;

    public Bookings(Date date){
        mDate = date;
        mCalendarItems = new ArrayList<CalendarDataSource.ICalendarItem>();
        mCalendarItems.add( new DayHeader("BOOKINGS") );
    }

    /**
     *  CalendarDataSource.CalendarPeriod methods
     */
    private CalendarDataSource.CalendarPeriodDelegate mDelegate;

    public void setDelegate( CalendarDataSource.CalendarPeriodDelegate delegate ){
        mDelegate = delegate;
    }
    public CalendarDataSource.CalendarPeriodDelegate getDelegate(){
        return mDelegate;
    }


    public ArrayList<CalendarDataSource.ICalendarItem> getCalendarIems(){
        return mCalendarItems;
    }

    public String getPeriodTitle(){
        return "Bookings";
    }

    public CalendarDataSource.IDay selectedDay(){
        return null;
    }


    public float contentHeight(){
        return 50.0f;
    }

    public int getNumberOfDaysPerColumn(){
        return 1;
    }



}

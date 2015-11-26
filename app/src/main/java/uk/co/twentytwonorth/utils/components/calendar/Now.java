package uk.co.twentytwonorth.utils.components.calendar;


import android.util.Pair;
import android.util.TypedValue;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by colinlight on 24/11/15.
 */
public class Now implements CalendarDataSource.CalendarPeriod {

    private Date mDate;
    private int mSelectedDay = 0;
    private ArrayList<CalendarDataSource.ICalendarItem> mCalendarItems;

    public Now(Date date){
        mDate = date;
        DateFormat formatter = new SimpleDateFormat("EEEE, MMMM dd");
        String title = "Today - " + formatter.format( mDate );
        mCalendarItems = new ArrayList<CalendarDataSource.ICalendarItem>();
        mCalendarItems.add( new DayHeader(title) );
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
        return "Now";
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

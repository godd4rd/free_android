package uk.co.twentytwonorth.utils.components.calendar;


import android.util.Pair;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by colinlight on 24/11/15.
 */
public class Week implements CalendarDataSource.CalendarPeriod {

    private Date mDate;
    private int mSelectedDay;
    private ArrayList<CalendarDataSource.ICalendarItem> mCalendarItems;

    public Week(Date date){
        mDate = date;

        Pair<ArrayList<CalendarDataSource.IDay>, Integer> daysIndex =
                CalendarDataSource.daysForTimeframe(mDate, true, false, Calendar.DAY_OF_WEEK);

        Pair<ArrayList<CalendarDataSource.ICalendarItem>, Integer> itemsIndex =
                CalendarDataSource.addDayHeaders(daysIndex.first, daysIndex.second);

        mCalendarItems = itemsIndex.first;
        mSelectedDay = itemsIndex.second;
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
        return "Week";
    }


    public CalendarDataSource.IDay selectedDay(){
        return null;
    }


    public Float contentHeight(){
        return 1000.0f;
    }

    public int getNumberOfDaysPerColumn(){
        return 7;
    }



}

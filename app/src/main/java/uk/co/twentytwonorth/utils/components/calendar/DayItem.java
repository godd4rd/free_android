package uk.co.twentytwonorth.utils.components.calendar;

/**
 * Created by colinlight on 18/11/15.
 */

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 DayItem is a concrete class to represent Day data items on a calendar view
 */
class DayItem implements CalendarDataSource.IDay{

    private String mTitle;
    /**
     * ICalendarViewItem props
     */
    public String getTitle(){
        return mTitle;
    }

    /**
     * IDay items props
     */
    private Date mDate;

    public void setDate( Date date ){
        this.mDate = date;
    }

    public Date getDate(){
        return mDate;
    }


    private Object mData;

    public void setData( Object data ){
        this.mData = data;
    }
    public Object getData(){
        return this.mData;
    }


    private Boolean mIsCurrentMonth;

    public void setIsCurrentMonth( Boolean state ){
        this.mIsCurrentMonth = state;
    }

    public Boolean getIsCurrentMonth(){
        return this.mIsCurrentMonth;
    }


    private Boolean mIsCurrentDay;

    public void setIsCurrentDay( Boolean state ){
        this.mIsCurrentDay = state;
    }

    public Boolean getIsCurrentDay(){
        return this.mIsCurrentDay;
    }

    public DayItem( Date date, Object data, Boolean currentDay, Boolean currentMonth ){
        this.mDate = date;
        this.mData = data;
        this.mIsCurrentDay = currentDay;
        this.mIsCurrentMonth = currentMonth;

        DateFormat mFormatter = new SimpleDateFormat("dd");
        mTitle = mFormatter.format( mDate );
    }
}
package uk.co.twentytwonorth.utils.components.calendar;

/**
 * Created by colinlight on 20/11/15.
 */
public class DayHeader implements CalendarDataSource.ICalendarViewItem {

    private String mTitle;

    public DayHeader( String title ){
        mTitle = title;
    }

    public String getTitle(){
        return mTitle;
    }
}

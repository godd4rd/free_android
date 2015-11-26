package uk.co.twentytwonorth.utils.components.calendar;


import android.util.Pair;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

/**
 * Created by colinlight on 18/11/15.
 */
public class CalendarDataSource {

    /**
     Types adopting the 'ICalendarItem' interface can be used to represent an item in the
     calendar view, this could be a day or a day header
     */
    public interface ICalendarItem{
        public String getTitle();
    }

    /**
     Types adopting the 'Day' interface can be used to represent day information on a calendar view
     */
    public interface IDay extends ICalendarItem{

        public void setDate( Date date );
        public Date getDate();

        public void setData( Object data );
        public Object getData();

        public void setIsCurrentMonth( Boolean state );
        public Boolean getIsCurrentMonth();

        public void setIsCurrentDay( Boolean state );
        public Boolean getIsCurrentDay();
    }

    /**
     Types adopting the 'CalendarPeriod' interface can be used to represent a period of days on a calendar e.g.
     day, week, month etc
     */
    public interface CalendarPeriod{
        public void setDelegate( CalendarPeriodDelegate delegate );
        public CalendarPeriodDelegate getDelegate();

        public ArrayList<ICalendarItem> getCalendarIems();
        public String getPeriodTitle();
        public IDay selectedDay();
        public float contentHeight();

        public int getNumberOfDaysPerColumn();

    }


    /**
     Types adopting the 'CalendarPeriodDelegate' interface can be used to receive update information when
     a period changes e.g. scroll from month to month or week to week
     */
    public interface CalendarPeriodDelegate{
        public void periodDidChange( CalendarPeriod period );
        public void selectedINdexChanged( CalendarPeriod period );
    }

    /**
     * Returns a list of days based on a specific date and field passed
     * @param date
     * @param isToday
     * @param isCurrentMonth :
     * @param calendarField : the period the data range should be
     * @return
     */
    public static Pair<ArrayList<IDay>, Integer>daysForTimeframe( Date date, Boolean isToday,
                                                                  Boolean isCurrentMonth,
                                                                  int calendarField ){
        ArrayList<IDay> dates = new ArrayList<IDay>();

        Calendar currentCalendar = Calendar.getInstance(TimeZone.getDefault());
        currentCalendar.setTime( date );

        //MONTH
        int totalDaysRange = currentCalendar.getActualMaximum( calendarField );
        int currentDayIndex = currentCalendar.get( calendarField );

        int end = totalDaysRange - currentDayIndex;
        int start = end - ( totalDaysRange - 1 );

        int index = start;
        while ( index <= end){
            Date offsetDate = CalendarDataSource.dateWithOffSetDays(date, index);
            Boolean isCurrentDay = (isToday == true && index == 0) ? true : false;
            //Date date, Object data, Boolean currentDay, Boolean currentMonth
            dates.add( new DayItem( offsetDate, null, isCurrentDay, isCurrentMonth) );
            index += 1;
        }
        int currentDay = currentDayIndex - 1;
        return  Pair.create(dates, currentDay);
    }


    /**
     * Add days to the start and end of the month. e.g. The 1st on the month starts on a Wednesday,
     * because the calendar displays whole weeks we must include Monday to and Thursday of the
     * previous month
     * @param monthDays
     * @param curentDayIndex
     * @return
     */
    public static Pair<ArrayList<IDay>, Integer>addMonthOverflowDays( ArrayList<IDay> monthDays,
                                                                      int curentDayIndex){
        Date startDate = monthDays.get(0).getDate();
        Date endDate = monthDays.get( monthDays.size() -1 ).getDate();

        Pair<ArrayList<CalendarDataSource.IDay>, Integer> startWeek =
                CalendarDataSource.daysForTimeframe(startDate, false, false, Calendar.DAY_OF_WEEK);

        //filter out any dates < startDate
        ArrayList<CalendarDataSource.IDay> startDays = new ArrayList<IDay>();
        for (IDay day:startWeek.first){
            if ( day.getDate().getTime() < startDate.getTime() ){
                startDays.add(day);
            }
        }

        startDays.addAll(monthDays);
        //get current day index
        int dayIndex = curentDayIndex + ( startDays.size() - monthDays.size() );

        Pair<ArrayList<CalendarDataSource.IDay>, Integer> endWeek =
                CalendarDataSource.daysForTimeframe(endDate, false, false, Calendar.DAY_OF_WEEK);
        //filter out any dates > endData
        for (IDay day:endWeek.first){
            if ( day.getDate().getTime() > endDate.getTime() ){
                startDays.add( day );
            }
        }

        return  Pair.create( startDays , dayIndex);
    }


    /**
     * Add days headers to an array of days e.g. MONDAY-SUNDAY
     * @param days
     * @param curentDayIndex
     * @return
     */
    public static Pair<ArrayList<ICalendarItem>, Integer> addDayHeaders(ArrayList<IDay> days, int curentDayIndex){

        ArrayList<ICalendarItem> weekHeader = new ArrayList<ICalendarItem>();
        if ( days.size() < 7){
            return Pair.create(  CalendarDataSource.addDaysToHeaderItems( weekHeader, days ) ,
                    curentDayIndex);
        }

        int index = 0;
        while ( index < 7){
            IDay day = (IDay)days.get(index);
            Calendar currentCalendar = Calendar.getInstance(TimeZone.getDefault());
            currentCalendar.setTime(day.getDate());
            String weekName = currentCalendar.getDisplayName(Calendar.DAY_OF_WEEK, Calendar.SHORT,
                    Locale.getDefault());
            weekHeader.add( new DayHeader( weekName ) );
            index++;
        }
        curentDayIndex+=7;
        return Pair.create(  CalendarDataSource.addDaysToHeaderItems( weekHeader, days ) ,
                curentDayIndex);
    }


    /**
     * Add IDay items to the header list casting them as ICalendarItems
     * @param headerItems
     * @param days
     * @return
     */
    private static ArrayList<ICalendarItem> addDaysToHeaderItems( ArrayList<ICalendarItem> headerItems,
                                                                  ArrayList<IDay> days ){
        for ( IDay day:days){
           headerItems.add( (ICalendarItem)day );
        }
        return headerItems;
    }


    /**
     * Return a date n number of days offset from a reference date supplied
     * @param referenceDate
     * @param offSet
     * @return
     */
    public static Date dateWithOffSetDays( Date referenceDate, int offSet ){
        Calendar currentCalendar = Calendar.getInstance(TimeZone.getDefault());
        currentCalendar.setTime( referenceDate );
        currentCalendar.add(Calendar.DAY_OF_MONTH, offSet);
        Date offsetDate = currentCalendar.getTime();
        return offsetDate;
    }

}

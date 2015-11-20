package uk.co.twentytwonorth.utils.components.calendar;


import android.util.Pair;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

/**
 * Created by colinlight on 18/11/15.
 */
public class CalendarDataSource {

    /**
     Types adopting the 'ICalendarViewItem' interface can be used to represent a view item in the
     calendar view
     */
    public interface ICalendarViewItem{
        public String getTitle();
    }

    /**
     Types adopting the 'Day' interface can be used to represent day information on a calendar view
     */
    public interface IDay extends ICalendarViewItem{

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

        public String getPeriodTitle();
        public IDay selectedDay();
        public Float contentHeight();

        public int getNumberOfDaysPerColumn();

    }


    /**
     Types adopting the 'CalendarPeriodDelegate' interface can be used to receive update information form when
     a period changes e.g. scroll from month to month or week to week
     */
    public interface CalendarPeriodDelegate{
        public void periodDidChange( CalendarPeriod period );
        public void selectedINdexChanged( CalendarPeriod period );
    }

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

    public static Pair<ArrayList<IDay>, Integer>addDayHeaders( ArrayList<IDay> days, int curentDayIndex){

        if ( days.size() < 7){
            Pair.create( days , curentDayIndex);
        }

        int index = 0;
        ArrayList<IDay> weekHeader = new ArrayList<IDay>();
        while ( index <= 7){
            Date date = days.get(index).getDate();
            date.
            index++;
        }

    }


    public static Date dateWithOffSetDays( Date referenceDate, int offSet ){
        Calendar currentCalendar = Calendar.getInstance(TimeZone.getDefault());
        currentCalendar.setTime( referenceDate );
        currentCalendar.add(Calendar.DAY_OF_MONTH, offSet);
        Date offsetDate = currentCalendar.getTime();
        return offsetDate;
    }

}

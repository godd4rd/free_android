package uk.co.twentytwonorth.free.model;

import java.util.Date;

import uk.co.twentytwonorth.utils.components.calendar.Bookings;
import uk.co.twentytwonorth.utils.components.calendar.CalendarDataSource;
import uk.co.twentytwonorth.utils.components.calendar.Month;
import uk.co.twentytwonorth.utils.components.calendar.Now;
import uk.co.twentytwonorth.utils.components.calendar.Week;

/**
 * Created by colinlight on 25/11/15.
 */
public enum Period {
    NOW, WEEK, MONTH, BOOKINGS;

    public static CalendarDataSource.CalendarPeriod calendarPeriod( Period period, Date date ){
        switch (period){
            case NOW:
                return new Now( date );
            case WEEK:
                return new Week( date );
            case MONTH:
                return new Month( date );
            case BOOKINGS:
                return new Bookings( date );
        }
        return null;
    }
}

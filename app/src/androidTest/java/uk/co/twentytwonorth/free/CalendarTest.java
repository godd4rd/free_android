package uk.co.twentytwonorth.free;

import android.util.Pair;

import junit.framework.Assert;
import junit.framework.TestCase;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import uk.co.twentytwonorth.utils.components.calendar.CalendarDataSource;

/**
 * Created by colinlight on 18/11/15.
 */
public class CalendarTest extends TestCase {

    CalendarDataSource mCalendarDataSource;
    Date mNow;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        this.mCalendarDataSource = new CalendarDataSource();
        this.mNow = new Date();
    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }

    //dateWithOffSetDays
    public void test_negative_offset_days_are_before_today(){
        Date offsetDateBefore = mCalendarDataSource.dateWithOffSetDays(mNow, -1 );
        Assert.assertTrue("testDateWithOffSetDays : offsetDate should be before now", offsetDateBefore.before(mNow));
    }

    public void test_zero_offset_days_equal_today(){

        Date offsetDateEqual = mCalendarDataSource.dateWithOffSetDays(mNow, 0 );
        Assert.assertTrue("testDateWithOffSetDays : offsetDate should equal now", offsetDateEqual.equals(mNow));
    }

    public void test_postive_offset_days_are_after_today(){

        Date offsetDateAfter = mCalendarDataSource.dateWithOffSetDays(mNow, 1 );
        Assert.assertTrue("testDateWithOffSetDays : offsetDate should be after now", offsetDateAfter.after(mNow));
    }

    public void test_daysForTimeframe_returns_correct_dates_for_month(){
        //Calendar.DAY_OF_MONTH ( field for month )
        Pair<ArrayList<CalendarDataSource.IDay>, Integer> result =
                this.mCalendarDataSource.daysForTimeframe(mNow, true, true, Calendar.DAY_OF_MONTH);

        Assert.assertNotNull( result.first );
        Assert.assertNotNull( result.second );
        Assert.assertTrue( result.first.size() > 28 );
    }

    public void test_daysForTimeframe_returns_seven_dates_for_a_week(){
        //Calendar.DAY_OF_WEEK ( field for week )
        Pair<ArrayList<CalendarDataSource.IDay>, Integer> weekResult =
                this.mCalendarDataSource.daysForTimeframe(mNow, true, true, Calendar.DAY_OF_WEEK);

        Assert.assertTrue( weekResult.first.size() == 7 );
    }

    public void test_overflow_days_adds_days_at_start_end_of_month_where_weeks_overlap_other_months(){
        Pair<ArrayList<CalendarDataSource.IDay>, Integer> result =
                this.mCalendarDataSource.daysForTimeframe(mNow, true, true, Calendar.DAY_OF_MONTH);

        Pair<ArrayList<CalendarDataSource.IDay>, Integer> overflowResult =
                CalendarDataSource.addMonthOverflowDays(result.first, result.second);

    }
}

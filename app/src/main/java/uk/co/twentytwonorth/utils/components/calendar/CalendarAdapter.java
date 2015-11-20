package uk.co.twentytwonorth.utils.components.calendar;

import android.content.Context;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import uk.co.twentytwonorth.free.R;

/**
 * Created by colinlight on 20/11/15.
 */
public class CalendarAdapter extends BaseAdapter implements CalendarDataSource.CalendarPeriod {

    private Context mContext;
    private Date mDate;
    private ArrayList<CalendarDataSource.IDay> mDays;
    private int mCurrentDayIndex;
    private LayoutInflater mInflator;


    public CalendarAdapter( Context context, Date date ){
        mContext = context;
        mInflator = LayoutInflater.from(mContext);
        mDate = date;
        Pair<ArrayList<CalendarDataSource.IDay>, Integer> daysIndex =
                CalendarDataSource.daysForTimeframe(mDate, true, false, Calendar.DAY_OF_MONTH);
        daysIndex = CalendarDataSource.addMonthOverflowDays(daysIndex.first, daysIndex.second);
        mDays = daysIndex.first;
        mCurrentDayIndex = daysIndex.second;
    }

    public int getCount(){
        return mDays.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public boolean isEnabled(int position)
    {
        return false;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        CalendarDataSource.IDay day = mDays.get(position);

        if ( convertView == null ){
            convertView = mInflator.inflate(R.layout.calendar_day, null);
        }

        final TextView titleView = (TextView)convertView.findViewById(R.id.calendarDayTextView);
        titleView.setText( day.getTitle() );
        convertView.setEnabled(false);
        return convertView;
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

    public String getPeriodTitle(){
        return "period title";
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

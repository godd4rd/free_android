package uk.co.twentytwonorth.utils.components.calendar;

import android.content.Context;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import uk.co.twentytwonorth.free.R;

/**
 * Created by colinlight on 20/11/15.
 */
public class CalendarAdapter extends BaseAdapter{

    protected Context mContext;
    protected CalendarDataSource.CalendarPeriod mPeriod;
    protected LayoutInflater mInflator;

    public CalendarAdapter(Context context,  CalendarDataSource.CalendarPeriod period){
        mContext = context;
        mInflator = LayoutInflater.from(mContext);
        mPeriod = period;
    }


    public void setPeriod( CalendarDataSource.CalendarPeriod period ){
        mPeriod = period;
        this.notifyDataSetInvalidated();
    }

    public CalendarDataSource.CalendarPeriod getPeriod(){
        return mPeriod;
    }

    public int getCount(){
        return mPeriod.getCalendarIems().size();
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
        CalendarDataSource.ICalendarItem item =  mPeriod.getCalendarIems().get(position);
        if ( item instanceof CalendarDataSource.IDay ){
            return true;
        }
        return false;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        CalendarDataSource.ICalendarItem item =  mPeriod.getCalendarIems().get(position);
        if ( convertView == null ){
            convertView = mInflator.inflate(R.layout.calendar_day, null);
        }

        final TextView titleView = (TextView)convertView.findViewById(R.id.calendarDayTextView);
        titleView.setText( item.getTitle() );
        convertView.setEnabled(false);
        return convertView;

    }


}

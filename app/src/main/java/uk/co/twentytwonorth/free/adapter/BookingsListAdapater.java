package uk.co.twentytwonorth.free.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.BaseAdapter;
import android.widget.GridView;

import java.util.Date;

import uk.co.twentytwonorth.free.R;
import uk.co.twentytwonorth.utils.components.calendar.CalendarDataSource;
import uk.co.twentytwonorth.utils.components.calendar.MonthAdapter;

/**
 * Created by colinlight on 23/11/15.
 */

interface BookingListItem{

}

public class BookingsListAdapater extends BaseAdapter {

    private Context mContext;
    private LayoutInflater mInflator;
    private BaseAdapter mCalendarAdapater;



    public BookingsListAdapater( Context context ){
        mContext = context;
        mInflator = LayoutInflater.from(context);

        Date now = new Date();
        mCalendarAdapater = new MonthAdapter( mContext, now);
    }

    public int getCount(){
        return 1;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    public View getView(int position, View convertView, ViewGroup parent ){

        if ( convertView == null ){
           convertView = mInflator.inflate(R.layout.booking_header, null);
        }

        final GridView calendarGrid = (GridView)convertView.findViewById(R.id.calendarGridView);
        CalendarDataSource.CalendarPeriod period = (CalendarDataSource.CalendarPeriod)mCalendarAdapater;
        calendarGrid.setNumColumns( period.getNumberOfDaysPerColumn() );
        calendarGrid.setAdapter(mCalendarAdapater);

        return convertView;
    }



}

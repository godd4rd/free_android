package uk.co.twentytwonorth.free.adapter;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;

import java.util.Date;

import uk.co.twentytwonorth.free.R;
import uk.co.twentytwonorth.utils.components.calendar.CalendarDataSource;
import uk.co.twentytwonorth.utils.components.calendar.MonthAdapter;
import uk.co.twentytwonorth.utils.components.calendar.WeekAdapater;

/**
 * Created by colinlight on 23/11/15.
 */

interface BookingListItem{

}

public class BookingsListAdapater extends BaseAdapter {

    private static final int HEADER_INDEX = 0;

    private Context mContext;
    private LayoutInflater mInflator;
    private BaseAdapter mCalendarAdapater;
    private ViewHolder mViewHolder;

    static class ViewHolder{
        GridView gridView;
    }


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

        if ( position == HEADER_INDEX ){
            return getHeaderView(convertView, parent);
        }

        if ( convertView == null ){
            convertView = mInflator.inflate(R.layout.booking_header, null);
        }

        return convertView;
    }

    public void setPeriod(){

        mCalendarAdapater = new WeekAdapater( mContext, new Date() );

        new AsyncTask<GridView, Void,  CalendarDataSource.CalendarPeriod>(){
            private  GridView calendarGrid;

            @Override
            protected CalendarDataSource.CalendarPeriod doInBackground(GridView... params) {
                calendarGrid = params[0];
                CalendarDataSource.CalendarPeriod period = (CalendarDataSource.CalendarPeriod)mCalendarAdapater;
                return period;
            }

            @Override
            protected void onPostExecute( CalendarDataSource.CalendarPeriod period) {
                super.onPostExecute(period);
                calendarGrid.setNumColumns(period.getNumberOfDaysPerColumn());
                calendarGrid.setAdapter(mCalendarAdapater);
                calendarGrid.setAlpha(0.0f);
                calendarGrid.setVisibility(View.VISIBLE);
                calendarGrid.animate().alpha(1.0f);
            }
        }.execute( mViewHolder.gridView );

        this.notifyDataSetInvalidated();
    }


    private View getHeaderView( View convertView, ViewGroup parent ){
        ViewHolder viewHolder;

        if ( convertView == null ){
            convertView = mInflator.inflate(R.layout.booking_header, null);

            viewHolder = new ViewHolder();
            convertView.setTag(viewHolder);
            mViewHolder = viewHolder;

            final GridView calendarGrid = (GridView)convertView.findViewById(R.id.calendarGridView);
            viewHolder.gridView = calendarGrid;
            calendarGrid.setVisibility(View.INVISIBLE);

            new AsyncTask<GridView, Void,  CalendarDataSource.CalendarPeriod>(){
                private  GridView calendarGrid;

                @Override
                protected CalendarDataSource.CalendarPeriod doInBackground(GridView... params) {
                    calendarGrid = params[0];
                    CalendarDataSource.CalendarPeriod period = (CalendarDataSource.CalendarPeriod)mCalendarAdapater;
                    return period;
                }

                @Override
                protected void onPostExecute( CalendarDataSource.CalendarPeriod period) {
                    super.onPostExecute(period);
                    calendarGrid.setNumColumns(period.getNumberOfDaysPerColumn());
                    calendarGrid.setAdapter(mCalendarAdapater);
                    calendarGrid.setAlpha(0.0f);
                    calendarGrid.setVisibility(View.VISIBLE);
                    calendarGrid.animate().alpha(1.0f);
                }
            }.execute(calendarGrid);

            this.createTabButtonListener((Button) convertView.findViewById(R.id.nowTabButton));
            this.createTabButtonListener((Button) convertView.findViewById(R.id.weekTabButton) );
            this.createTabButtonListener((Button) convertView.findViewById(R.id.monthTabButton) );
            this.createTabButtonListener((Button) convertView.findViewById(R.id.bookingsTabButton) );

            this.createTabButtonListener((Button) convertView.findViewById(R.id.roomsTabButton));
            this.createTabButtonListener((Button) convertView.findViewById(R.id.deskTabButton) );

        }
        return convertView;
    }

    private void createTabButtonListener( Button tabButton ){
        tabButton.setTag(this);
        tabButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("BookingsListAdapater", "Button Selected: " + v.getId() + " : " + R.id.weekTabButton);

                BookingsListAdapater adapater = (BookingsListAdapater)v.getTag();
                adapater.setPeriod();

//                switch ( v.getId() ){
//                    case R.id.nowTabButton:
//
//
//                }

            }
        });
    }



}

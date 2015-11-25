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
import uk.co.twentytwonorth.free.model.Period;
import uk.co.twentytwonorth.utils.components.calendar.CalendarDataSource;
import uk.co.twentytwonorth.utils.components.calendar.CalendarAdapter;
import uk.co.twentytwonorth.utils.components.calendar.Month;
import uk.co.twentytwonorth.utils.components.calendar.Week;

/**
 * Created by colinlight on 23/11/15.
 */

interface BookingListItem{

}

public class BookingsListAdapater extends BaseAdapter {

    private static final int HEADER_INDEX = 0;

    private Context mContext;
    private LayoutInflater mInflator;
    private CalendarAdapter mCalendarAdapater;
    private ViewHolder mViewHolder;

    static class ViewHolder{
        GridView gridView;
    }


    public BookingsListAdapater( Context context ){
        mContext = context;
        mInflator = LayoutInflater.from(context);

        Date now = new Date();
        mCalendarAdapater = new CalendarAdapter( mContext, new Week(now) );
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

    public static class PeriodParams{
        Period period;
        Date date;
        public PeriodParams(  Period period, Date date){
            this.period = period;
            this.date = date;
        }
    }


    public void setPeriod( Period period ){

        new AsyncTask<PeriodParams, Void, CalendarDataSource.CalendarPeriod>(){
            private Period period;
            private  Date date;

            @Override
            protected CalendarDataSource.CalendarPeriod doInBackground(PeriodParams... params) {
                period = params[0].period;
                date = params[0].date;
                return  Period.calendarPeriod( period, date );
            }

            @Override
            protected void onPostExecute( CalendarDataSource.CalendarPeriod period) {
                super.onPostExecute(period);
                mCalendarAdapater.setPeriod(period);
                mViewHolder.gridView.setNumColumns(mCalendarAdapater.getPeriod().getNumberOfDaysPerColumn());
                mViewHolder.gridView.setAlpha(0.0f);
                mViewHolder.gridView.setVisibility(View.VISIBLE);
                mViewHolder.gridView.animate().alpha(1.0f);
            }
        }.execute( new PeriodParams(period, new Date() ) );

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

            //create the adapater
            new AsyncTask<GridView, Void,  CalendarAdapter>(){
                private  GridView calendarGrid;

                @Override
                protected CalendarAdapter doInBackground(GridView... params) {
                    calendarGrid = params[0];
                    CalendarAdapter adapater = new CalendarAdapter( mContext, new Month( new Date() ) );
                    return adapater;
                }

                @Override
                protected void onPostExecute( CalendarAdapter adapater) {
                    super.onPostExecute(adapater);
                    mCalendarAdapater = adapater;
                    calendarGrid.setNumColumns( adapater.getPeriod().getNumberOfDaysPerColumn() );
                    calendarGrid.setAdapter(mCalendarAdapater);
                    calendarGrid.setAlpha(0.0f);
                    calendarGrid.setVisibility(View.VISIBLE);
                    calendarGrid.animate().alpha(1.0f);
                }
            }.execute(calendarGrid);

            this.createTabButtonListener((Button) convertView.findViewById(R.id.nowTabButton), Period.NOW);
            this.createTabButtonListener((Button) convertView.findViewById(R.id.weekTabButton), Period.WEEK );
            this.createTabButtonListener((Button) convertView.findViewById(R.id.monthTabButton), Period.MONTH );
            this.createTabButtonListener((Button) convertView.findViewById(R.id.bookingsTabButton), Period.BOOKINGS );

            //this.createTabButtonListener((Button) convertView.findViewById(R.id.roomsTabButton));
            ///this.createTabButtonListener((Button) convertView.findViewById(R.id.deskTabButton) );

        }
        return convertView;
    }

    private void createTabButtonListener( Button tabButton, Period period ){
        tabButton.setTag(period);
        tabButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("BookingsListAdapater", "Button Selected: " + v.getId() + " : " + R.id.weekTabButton);
                setPeriod((Period) v.getTag());
            }
        });
    }


}

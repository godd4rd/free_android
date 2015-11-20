package uk.co.twentytwonorth.free.controller;


import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import java.util.Date;

import uk.co.twentytwonorth.free.R;
import uk.co.twentytwonorth.utils.components.calendar.CalendarAdapter;
import uk.co.twentytwonorth.utils.components.calendar.CalendarDataSource;


public class BookingsFragment extends Fragment {




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle saveInstance ){
        View view = inflater.inflate(R.layout.fragment_bookings, container, false);

        Date now = new Date();

        GridView calendarGrid = (GridView)view.findViewById(R.id.calendarGridView);
        CalendarAdapter adapter = new CalendarAdapter( this.getActivity().getApplicationContext(), now);
        CalendarDataSource.CalendarPeriod period = (CalendarDataSource.CalendarPeriod)adapter;
        calendarGrid.setNumColumns( period.getNumberOfDaysPerColumn() );
        calendarGrid.setAdapter(adapter);

        return view;
    }
}

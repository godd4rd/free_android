package uk.co.twentytwonorth.free.controller;


import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TabHost;


import uk.co.twentytwonorth.free.R;
import uk.co.twentytwonorth.free.adapter.BookingsListAdapater;



public class BookingsFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle saveInstance ){
        View view = inflater.inflate(R.layout.fragment_bookings, container, false);

        ListView bookingsList = (ListView)view.findViewById(R.id.bookingsView);
        BookingsListAdapater adapter = new BookingsListAdapater( this.getActivity().getApplicationContext() );
        bookingsList.setAdapter( adapter );

        return view;
    }
}

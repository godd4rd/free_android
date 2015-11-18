package twentytwonorth.co.uk.free.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import twentytwonorth.co.uk.free.controller.NavigationDrawerFragment;

/**
 * Created by colinlight on 18/11/15.
 */
public class OptionMenuAdapter extends ArrayAdapter<NavigationDrawerFragment.OptionMenuItem> {

    private LayoutInflater mInflator;

    public OptionMenuAdapter( Context context, List<NavigationDrawerFragment.OptionMenuItem> model){

        super(context,  android.R.layout.simple_list_item_activated_1, model);

        mInflator = LayoutInflater.from(context);
    }

    public View getView(int position, View convertView, ViewGroup parent ){

        final NavigationDrawerFragment.OptionMenuItem item = this.getItem(position);

        final TextView titleTextView;

        if ( convertView == null ){
            convertView = mInflator.inflate(android.R.layout.simple_list_item_activated_1, null);
        }

        titleTextView = (TextView)convertView.findViewById(android.R.id.text1);
        titleTextView.setText( item.getTitle() );

        return convertView;
    }
}

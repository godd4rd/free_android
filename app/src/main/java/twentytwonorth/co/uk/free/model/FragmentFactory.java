package twentytwonorth.co.uk.free.model;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import twentytwonorth.co.uk.free.R;
import twentytwonorth.co.uk.free.controller.BookingsFragment;
import twentytwonorth.co.uk.free.controller.RootActivity;


/**
 * Created by colinlight on 06/05/15.
 */
public class FragmentFactory {

    public static final String FREE = "free";
    public static final String FEEDBACK = "feedback";
    public static final String HELP = "help";


    public static Fragment fragment( String section, int sectionNumber ){

        if ( section == null ){
            return PlaceholderFragment.newInstance( sectionNumber );
        }

        switch (section){
            case FREE:
                return new BookingsFragment();
            default:
                return PlaceholderFragment.newInstance( sectionNumber );
        }
    }



    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_main, container, false);
            return rootView;
        }

        @Override
        public void onAttach(Activity activity) {
            super.onAttach(activity);
            ((RootActivity) activity).onSectionAttached(
                    getArguments().getInt(ARG_SECTION_NUMBER));
        }
    }
}

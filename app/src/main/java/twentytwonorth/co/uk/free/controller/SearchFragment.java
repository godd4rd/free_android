package twentytwonorth.co.uk.free.controller;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import twentytwonorth.co.uk.free.R;


/**
 * Created by colinlight on 06/05/15.
 */
public class SearchFragment extends Fragment {

    private ListView mSearchViewList;
    private SwipeRefreshLayout mRefreshLayout;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle saveInstance ){
        View view = inflater.inflate(R.layout.search_view, container, false);

        mSearchViewList = (ListView)view.findViewById(R.id.searchList);
        mRefreshLayout = (SwipeRefreshLayout)view.findViewById(R.id.searchListSwipe);
        return view;
    }

}

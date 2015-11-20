package uk.co.twentytwonorth.free.controller;

import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;

import uk.co.twentytwonorth.free.R;
import uk.co.twentytwonorth.free.model.FragmentFactory;
import uk.co.twentytwonorth.free.model.MenuItemFactory;

public class RootActivity extends AppCompatActivity
        implements NavigationDrawerFragment.NavigationDrawerCallbacks {

    public static final String TAG = "RootActivity";

    /**
     * Fragment managing the behaviors, interactions and presentation of the navigation drawer.
     */
    private NavigationDrawerFragment mNavigationDrawerFragment;

    private ArrayList<NavigationDrawerFragment.OptionMenuItem> mMenuData;

    /**
     * Used to store the last screen title. For use in {@link #restoreActionBar()}.
     */
    private CharSequence mTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_root);

        mNavigationDrawerFragment = (NavigationDrawerFragment)
                getSupportFragmentManager().findFragmentById(R.id.navigation_drawer);

        mTitle = getTitle();
        mMenuData =  MenuItemFactory.createMenuData(this.getApplicationContext());

        // Set up the drawer.
        mNavigationDrawerFragment.setUp(
                R.id.navigation_drawer,
                (DrawerLayout) findViewById(R.id.drawer_layout), mMenuData);

        this.onNavigationDrawerItemSelected( 0 );
    }

    @Override
    public void onNavigationDrawerItemSelected(int position) {

        if ( mMenuData != null ){

            NavigationDrawerFragment.OptionMenuItem item = mMenuData.get(position);
            mTitle = item.getTitle();
            if ( item instanceof NavigationDrawerFragment.OptionMenuNavigation ){
                NavigationDrawerFragment.OptionMenuNavigation navItem =
                        (NavigationDrawerFragment.OptionMenuNavigation)item;

                Fragment next =  FragmentFactory.fragment(navItem.getSectionIdentifier(), position + 1);

                replaceFragmentView( next );
            }
        }else{
            Fragment next =  FragmentFactory.fragment(null, position+1);
            replaceFragmentView( next );
        }

    }

    private void replaceFragmentView( Fragment fragment ){

        // update the main content by replacing fragments
        FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.container, fragment)
                .commit();
    }

    public void onSectionAttached(int number) {
        NavigationDrawerFragment.OptionMenuItem selectedItem = mMenuData.get(number - 1);
        mTitle = selectedItem.getTitle();
    }

    public void restoreActionBar() {
        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        //actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
        actionBar.setDisplayShowTitleEnabled(true);
        actionBar.setTitle(mTitle);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (!mNavigationDrawerFragment.isDrawerOpen()) {
            // Only show items in the action bar relevant to this screen
            // if the drawer is not showing. Otherwise, let the drawer
            // decide what to show in the action bar.
            getMenuInflater().inflate(R.menu.main, menu);
            restoreActionBar();
            return true;
        }
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}


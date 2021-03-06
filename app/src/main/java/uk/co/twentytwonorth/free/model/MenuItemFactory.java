package uk.co.twentytwonorth.free.model;


import android.content.Context;
import java.util.ArrayList;


import uk.co.twentytwonorth.free.R;
import uk.co.twentytwonorth.free.controller.NavigationDrawerFragment;

/**
 * Created by colinlight on 30/04/15.
 */
public class MenuItemFactory {


    public static ArrayList<NavigationDrawerFragment.OptionMenuItem> createMenuData( Context context ){

        ArrayList<NavigationDrawerFragment.OptionMenuItem> items =
                new ArrayList<NavigationDrawerFragment.OptionMenuItem>();

        items.add( new MenuNavigationItem( context.getString(R.string.freeTitle) , "test", FragmentFactory.FREE ));
        items.add( new MenuNavigationItem( context.getString(R.string.feedbackTitle) , "test", ""));
        items.add( new MenuNavigationItem( context.getString(R.string.helpTitle), "test", ""));

        return items;
    }
}


/**
 * Base navigation item
 */
class BaseMenuItem implements NavigationDrawerFragment.OptionMenuItem{

    private String mTitle;

    private String mRowId;

    public BaseMenuItem( String title, String rowId ){
        mTitle = title;
        mRowId = rowId;
    }

    public int rowIDForItem(Context context){
        return context.getResources().getIdentifier(mRowId, "id", context.getPackageName());
    }

    public String getTitle(){
        return mTitle;
    }

}


/**
 * Navigation menu item
 */
class MenuNavigationItem extends BaseMenuItem implements NavigationDrawerFragment.OptionMenuNavigation{

    public String mSectionID;

    public MenuNavigationItem( String title, String rowId, String sectionId ){
        super( title, rowId );
        this.mSectionID = sectionId;
    }

    public String getSectionIdentifier(){
        return mSectionID;
    }

}
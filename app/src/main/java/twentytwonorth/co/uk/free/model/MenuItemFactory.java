package twentytwonorth.co.uk.free.model;


import android.content.Context;
import java.util.ArrayList;


import twentytwonorth.co.uk.free.R;
import twentytwonorth.co.uk.free.controller.NavigationDrawerFragment;

/**
 * Created by colinlight on 30/04/15.
 */
public class MenuItemFactory {


    public static ArrayList<NavigationDrawerFragment.OptionMenuItem> createMenuData( Context context ){


        ArrayList<NavigationDrawerFragment.OptionMenuItem> items =
                new ArrayList<NavigationDrawerFragment.OptionMenuItem>();

        items.add( new MenuNavigationItem( context.getString(R.string.searchTitle) , "test", FragmentFactory.SEARCH ));
        items.add( new MenuNavigationItem( context.getString(R.string.settingsTitle) , "test", ""));
        items.add( new MenuNavigationItem( context.getString(R.string.favoritesTitle), "test", ""));
        items.add( new MenuNavigationItem( context.getString(R.string.helpTitle), "test", ""));
        items.add( new MenuNavigationItem( context.getString(R.string.feedbackTitle), "test", ""));
        items.add( new MenuNavigationItem("Logout", "test", ""));

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
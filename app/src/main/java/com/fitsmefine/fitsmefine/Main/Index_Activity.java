package com.fitsmefine.fitsmefine.Main;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import com.fitsmefine.fitsmefine.DialogFragments.Select_Best_Fitting_Vendors;
import com.fitsmefine.fitsmefine.R;

public class Index_Activity extends AppCompatActivity {
//  Activity Lifecycle:
// 	onCreate() - classes must implement this callback to initialize the essential components of the activity,
//	onStart() - the activity is visible but not yet interactive,
//	onResume() - the activity is ready for user interaction,
//	onPause() - called when the user leaves the activity or the app enters background. Activity may still be visible to user,
//	onStop() - called when activity is no longer visible to user,
//	onDestroy() - called before an activity is destroyed. Final place to ensure activity resources are released.

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_index_);

        // fmf_toolbar is defined in the layout file
        Toolbar myChildToolbar = (Toolbar) findViewById(R.id.fmf_toolbar);
        setSupportActionBar(myChildToolbar);

        // create floating action button find the xml that goes with it
        FloatingActionButton Index_Fab = (FloatingActionButton) findViewById(R.id.fab);
        Index_Fab.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Create and show the dialog - Dialog Fragement that does the function when you click the FAB - Best Fitting Vendors
                // dialogfragmentName              variablename  = new dialogfragmentName
                Select_Best_Fitting_Vendors dialogFragment = new Select_Best_Fitting_Vendors();
                dialogFragment.show(getFragmentManager(), "SelectBFVendors");
                //dialogFragment.show(getSupportFragmentManager(), "SelectBFVendors");      cannot resolve getSupportFragmentManager
            }   // end of onClick
        });   // end of setOnClickListener
        }    // end of OnCreate function

    @Override
    public boolean onCreateOptionsMenu (Menu menu) {
        // inflate my menu as a menu
        getMenuInflater().inflate(R.menu.indexmenu, menu);

        // look for id in the menu and set it to invisible
        menu.findItem(R.id.action_home).setVisible(false);

        // https://developer.android.com/guide/topics/search/search-dialog.html
        // Get the SearchView and set the searchable configuration
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView = (SearchView) menu.findItem(R.id.action_search_bar).getActionView();

        // Assumes current activity is the searchable activity
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        // searchView.setIconifiedByDefault(false); // Do not iconify the widget; expand it by default

        return true;
    }



    // An Activity represents a single screen in an app.
    // You can start a new instance of an Activity by passing an Intent to startActivity().
    // The Intent describes the activity to start and carries any necessary data.
    // If you want to receive a result from the activity when it finishes, call startActivityForResult().
    // Your activity receives the result as a separate Intent object in your activity's onActivityResult() callback.
    // Intent main = new Intent(this, Index_Activity.class);
    // startActivity(main);

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_home:
                // User chose the index item, show the search UI...
                Intent home = new Intent(this, Index_Activity.class);
                startActivity(home);
                return true;
            case R.id.action_login:
                // User chose the "login" item, show the login UI...
                Intent login = new Intent(this, LoginActivity.class);
                startActivity(login);
                return true;
            case R.id.action_about:
                // User chose the "about" item, show the about UI...
                Intent about = new Intent(this, AboutActivity.class);
                startActivity(about);
                return true;
            case R.id.action_display_categories:
                // User chose the "display categories" action, display the fashion categories
                Intent dc = new Intent(this, DisplayCategories.class);
                startActivity(dc);
                return true;
            case R.id.action_maps:
                // User chose the "maps" action, display the map UI
                Intent map = new Intent(this, MapsActivity.class);
                startActivity(map);
                return true;
            case R.id.action_pants_results_list:
                // User chose the "pants results list" action, display the pants list
                Intent pr = new Intent(this, Pants_Results_List.class);
                startActivity(pr);
                return true;
            default:
                // The user's action was not recognized. Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);
        }
    }
}  // end of IndexActivity Class

package com.fitsmefine.fitsmefine.Main;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;

import com.fitsmefine.fitsmefine.R;

public class Search_Results_Activity extends Index_Activity {

    private static final String TAG = Search_Results_Activity.class.getSimpleName();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_results);

        // fmf_toolbar is defined in the layout file
        Toolbar myChildToolbar = (Toolbar) findViewById(R.id.fmf_toolbar);
        setSupportActionBar(myChildToolbar);
        Log.d(TAG, "*****************************************************************************************");
        Log.d(TAG, "******************* Toolbar created - DisplayCategories.java ***************************");
        Log.d(TAG, "*****************************************************************************************");

        // Get a support ActionBar corresponding to this toolbar
        ActionBar ab = getSupportActionBar();
        // Enable the Up button
        ab.setDisplayHomeAsUpEnabled(true);

        //  call handle intent to get intent and get the search data (sending data from one screen to another)
        handleIntent(getIntent());
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        Log.d(TAG, "*****************************************************************************************");
        Log.d(TAG, "**************** Toolbar Menu start of OnCreateOptionsMenu - Search_Results_Activity.java ****");
        Log.d(TAG, "*****************************************************************************************");

        // inflate my menu as a menu
        getMenuInflater().inflate(R.menu.indexmenu, menu);

        // look for id in the menu and set it to invisible
        menu.findItem(R.id.action_about).setVisible(false);

        // https://developer.android.com/guide/topics/search/search-dialog.html
        // Get the SearchView and set the searchable configuration
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);

        SearchView searchView = (SearchView) menu.findItem(R.id.action_search_bar).getActionView();
        // Assumes current activity is the searchable activity
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        searchView.setIconifiedByDefault(false); // Do not iconify the widget; expand it by default

        return true;
    }

    @Override
    protected void onNewIntent(Intent intent){
        handleIntent(intent);
    }

    private void handleIntent(Intent intent) {
        if(Intent.ACTION_SEARCH.equals(intent.getAction())) {
            String search = intent.getStringExtra(SearchManager.QUERY);
            Log.d(TAG, "***********************************");
            Log.d(TAG,search);
            Log.d(TAG, "***********************************");

        }
    }
}
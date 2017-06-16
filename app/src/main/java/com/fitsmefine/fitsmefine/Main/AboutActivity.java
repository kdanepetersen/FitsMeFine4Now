package com.fitsmefine.fitsmefine.Main;

import android.app.SearchManager;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;

import com.fitsmefine.fitsmefine.R;

public class AboutActivity extends Index_Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        // fmf_toolbar is defined in the layout file
        Toolbar myChildToolbar = (Toolbar) findViewById(R.id.fmf_toolbar);
        setSupportActionBar(myChildToolbar);
    }

    @Override
    public boolean onCreateOptionsMenu (Menu menu) {
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
}

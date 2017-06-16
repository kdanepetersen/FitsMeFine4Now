package com.fitsmefine.fitsmefine.Main;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.fitsmefine.fitsmefine.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        // fmf_toolbar is defined in the layout file
//        Toolbar myChildToolbar = (Toolbar) findViewById(R.id.fmf_toolbar);
//        setSupportActionBar(myChildToolbar);

        // Get a support ActionBar corresponding to this toolbar
//        ActionBar ab = getSupportActionBar();

        // Enable the Up button
//        ab.setDisplayHomeAsUpEnabled(true);


        // look for id in the menu and set it to invisible
//        menu.findItem(R.id.action_display_categories).setVisible(false);


        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);






    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(-34, 151);
        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
    }


    @Override
    public boolean onCreateOptionsMenu (Menu menu) {
        // inflate my menu as a menu
        getMenuInflater().inflate(R.menu.indexmenu, menu);

        // look for id in the menu and set it to invisible
        menu.findItem(R.id.action_maps).setVisible(false);

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
}

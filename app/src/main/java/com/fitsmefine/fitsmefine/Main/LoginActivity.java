package com.fitsmefine.fitsmefine.Main;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;

import com.fitsmefine.fitsmefine.R;

public class LoginActivity extends Index_Activity {
        public static final String EXTRA_MESSAGE = "com.example.myfirstapp.MESSAGE";
        private static final String TAG = LoginActivity.class.getSimpleName();
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_login);

            // fmf_toolbar is defined in the layout file
            Toolbar myChildToolbar = (Toolbar) findViewById(R.id.fmf_toolbar);
            setSupportActionBar(myChildToolbar);

            // Get a support ActionBar corresponding to this toolbar
            ActionBar ab = getSupportActionBar();

            // Enable the Up button
            ab.setDisplayHomeAsUpEnabled(true);

            Log.d(TAG, "onCreate() method started");
        }

// logging for debugging
        protected void onStart() {
            super.onStart();
            Log.d(TAG, "onStart() method started");
        }

        protected void onResume() {
            super.onResume();
            Log.d(TAG, "onResume() method started");
        }

        protected void onPause() {
            super.onPause();
            Log.d(TAG, "onPause() method started");
        }

        protected void onStop() {
            super.onStop();
            Log.d(TAG, "onStop() method started");
        }

        protected void onRestart() {
            super.onRestart();
            Log.d(TAG, "onRestart() method started");
        }
        protected void onDestroy() {
            super.onDestroy();
            Log.d(TAG, "onDestroy() method started");
        }


        /** Called when the user taps the What's Up button */
        public void sendMessage(View view) {
            // The Intent constructor takes two parameters
            // A Context as its first parm (this is used because the Activity class is a subclass of Context
            //The Class of the app component to which the system should deliver the Intent
            //   (in this case, the activity that shoulc be started)
            Intent intent = new Intent(LoginActivity.this, DisplayCategories.class);
            // assign java variable to edittext xml
            EditText etLoginID = (EditText) findViewById(R.id.etLoginID);
            // get the login text from the text field and put it into idName variable
            String idName = etLoginID.getText().toString();

            // The putExtra method add the EditText's value to the intent.
            // An intent can carry data types as key-value pairs called extras.
            // key is a public constant EXTRA_MESSAGE because the next activity uses the key to retrieve
            // the text value.
            // Note:  good practice to define keys for intent extras using app's package name as a prefix
            // to ensure the keys are unique in case this app interacts with other apps.
            intent.putExtra(EXTRA_MESSAGE, idName);

            // The startActivity() method starts an instance of the DisplayQuestionsActivity specified by the intent
            startActivity(intent);
        }

    @Override
    public boolean onCreateOptionsMenu (Menu menu) {
        // inflate my menu as a menu
        getMenuInflater().inflate(R.menu.indexmenu, menu);

        // look for id in the menu and set it to invisible
        menu.findItem(R.id.action_login).setVisible(false);

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

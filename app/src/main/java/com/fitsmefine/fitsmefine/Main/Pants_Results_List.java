package com.fitsmefine.fitsmefine.Main;

        import android.app.SearchManager;
        import android.content.ClipData;
        import android.content.Context;
        import android.net.ConnectivityManager;
        import android.net.NetworkInfo;
        import android.os.Bundle;
        import android.support.v7.app.ActionBar;
        import android.support.v7.app.AppCompatActivity;
        import android.support.v7.widget.LinearLayoutManager;
        import android.support.v7.widget.RecyclerView;
        import android.support.v7.widget.SearchView;
        import android.support.v7.widget.Toolbar;
        import android.util.Log;
        import android.view.Menu;
        import android.widget.Toast;

        import com.android.volley.Request;
        import com.android.volley.Response;
        import com.android.volley.VolleyError;
        import com.android.volley.toolbox.JsonArrayRequest;
        import com.fitsmefine.fitsmefine.Adapters.Items;
        import com.fitsmefine.fitsmefine.Adapters.JsonParse;
        import com.fitsmefine.fitsmefine.Adapters.RecyclerViewAdapter;
        import com.fitsmefine.fitsmefine.R;

        import org.json.JSONArray;

        import java.util.ArrayList;
        import java.util.List;

/**
 * Created by Dane on 5/11/2017.
 */

public class Pants_Results_List extends Index_Activity {
    private static final String TAG = Pants_Results_List.class.getSimpleName();

    RecyclerView recyclerView;
    Context context;
    //RelativeLayout relativeLayout;

    // declare my recyclerViewAdapter variable
    RecyclerView.Adapter recyclerViewAdapter;

    // declaring how the item in the recyclerview will be layed out
    RecyclerView.LayoutManager recylerViewLayoutManager;

    // create the list of items to display in the Image Adapter List - creates the itemList array private to this class
    private List<Items> itemList = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // create the activity (oncreate) and save the state of what is being displayed
        super.onCreate(savedInstanceState);

        // setContentView displays what is being passed
        setContentView(R.layout.activity_pants_results_list);

        // fmf_toolbar is defined in the layout file
        Toolbar myChildToolbar = (Toolbar) findViewById(R.id.fmf_toolbar);
        setSupportActionBar(myChildToolbar);
        Log.d(TAG, "*****************************************************************************************");
        Log.d(TAG, "******************* Toolbar created - Pants Results List.java ***************************");
        Log.d(TAG, "*****************************************************************************************");

        // Get a support ActionBar corresponding to this toolbar
        ActionBar ab = getSupportActionBar();
        // Enable the Up button
        ab.setDisplayHomeAsUpEnabled(true);


        // context (interface to the global information about your application) is your app, and is what connects to the other classes.  Get application context gets all the back end connection information
        context = getApplicationContext();
        // create the itemList array
        // createList();
        Log.d(TAG, "*******************createList called***************************");
        // set the reecyclerView variable to the RecyclerView data type - look in xml files to find recyclerview layout to be able to display the data in the recycler view
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        Log.d(TAG, "************************set the reecyclerView variable to the RecyclerView data type - look in xml files to find recyclerview layout to be able to display the data in the recycler view*******************");

        String url = "http://kpetersen.icoolshow.net/fitsmefine/Arrays/pants_results_list.php";
        // check to see if the mobile phone is connected to the internet
            if(isNetworkConnected(getApplicationContext())) {
                // requesting the data from the fitsmefine url
                JsonArrayRequest jsArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        // when I get a JSON response, parse it into the recycler view
                        // create a json parseitemlist object that will parse the json response into an item list
                        JsonParse parse = new JsonParse();
                        List<Items> itemsList = parse.JsonParse(response);
                        RecyclerViewAdapter rva = new RecyclerViewAdapter(getApplicationContext(), itemsList);
                        recyclerView.setAdapter(rva);
                    }
                }
                        , new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(Pants_Results_List.this, "" + error, Toast.LENGTH_SHORT).show();
                    }
                });

                // Access the requestqueue via singleton class
                VolleySingleton.getInstance(this).addToRequestQueue(jsArrayRequest);
            } else
            {
                // display toast indicating no internet connection, connect to the internet
                Toast.makeText(Pants_Results_List.this, "No Internet Connection.  Please connect to the internet.", Toast.LENGTH_SHORT).show();
            }



        //create the layout manager (Change 2 to your choice because here 2 is the number of Grid layout Columns in each row).
        LinearLayoutManager llm = new LinearLayoutManager(this);
        Log.d(TAG, "*****************************Linear Layout Manager created*********************************");
        //recylerViewLayoutManager = new LinearLayoutManager(context);
        // set the layout to vertical
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        Log.d(TAG, "*****************************set Layout Manager *********************************");
        // set the recyclerview's layout manager to llm
        recyclerView.setLayoutManager(llm);
        //        recyclerViewAdapter = new ImageAdapter(context,itemList);
        // giving RecyclerViewAdapter permission to display the private itemList array
        recyclerViewAdapter = new RecyclerViewAdapter(context,itemList);
        Log.d(TAG, "*****************************new recyclerViewAdapter *********************************");
        recyclerView.setAdapter(recyclerViewAdapter);
        Log.d(TAG, "*****************************set Adapter *********************************");

        // requestWindowFeature(Window.FEATURE_ACTION_BAR);
        // relativeLayout = (RelativeLayout) findViewById(R.id.activity_display_categories);
    }

    @Override
    public boolean onCreateOptionsMenu (Menu menu) {
        Log.d(TAG, "************************** start of onCreateOptionsMenu() of Pants_Results_List *********************************");
        // inflate my menu as a menu - creates the items in the menu
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



    private void createList()
    {
        Log.d(TAG, "************************** start of createList() of itemList *********************************");

            }


    public static boolean isNetworkConnected(Context cntx){
    // look for the network connection and return a boolean if network is connected (True = 1) or not (false = 0)
        ConnectivityManager cm = (ConnectivityManager)
                cntx.getSystemService (Context.CONNECTIVITY_SERVICE);
        NetworkInfo nti = cm.getActiveNetworkInfo();
        return nti != null && nti.isConnectedOrConnecting();
    }
}

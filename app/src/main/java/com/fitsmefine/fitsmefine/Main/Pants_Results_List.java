package com.fitsmefine.fitsmefine.Main;

        import android.app.SearchManager;
        import android.content.Context;
        import android.os.Bundle;
        import android.support.v7.app.ActionBar;
        import android.support.v7.app.AppCompatActivity;
        import android.support.v7.widget.LinearLayoutManager;
        import android.support.v7.widget.RecyclerView;
        import android.support.v7.widget.SearchView;
        import android.support.v7.widget.Toolbar;
        import android.util.Log;
        import android.view.Menu;

        import com.fitsmefine.fitsmefine.Adapters.Items;
        import com.fitsmefine.fitsmefine.Adapters.RecyclerViewAdapter;
        import com.fitsmefine.fitsmefine.R;
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


        // context (interfact to the global information about your application) is your app, and is what connects to the other classes.  Get application context gets all the back end connection information
        context = getApplicationContext();
        // create the itemList array
        createList();
        Log.d(TAG, "*******************createList called***************************");
        // set the reecyclerView variable to the RecyclerView data type - look in xml files to find recyclerview layout to be able to display the data in the recycler view
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        Log.d(TAG, "************************set the reecyclerView variable to the RecyclerView data type - look in xml files to find recyclerview layout to be able to display the data in the recycler view*******************");
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

        itemList.add(new Items("Christian Siriano","Flared Trouser","Size 10", "perfect fit", "http://kpetersen.icoolshow.net/fitsmefine/images/pant_christian_siriano_flared_trouser.png"));
        itemList.add(new Items("Christian Siriano","Petal Lace Pant","Size 12", "loose fit", "http://kpetersen.icoolshow.net/fitsmefine/images/pant_christian_siriano_petal_lace_pant.png"));
        itemList.add(new Items("Daizy Shely","Fitted Flaired Trouser", "Size 10","tight fit", "http://kpetersen.icoolshow.net/fitsmefine/images/pant_daizy_shely_fitted_flair_trouser.png"));
        itemList.add(new Items("Daizy Shely","Fitted Patterned Flaired Trouser", "Size 12","good fit", "http://kpetersen.icoolshow.net/fitsmefine/images/pant_daizy_shely_fitted_patterned_flair_trouser.png"));
        itemList.add(new Items("DKNY","Sheer Relaxed Trouser", "Size 14","loose fit", "http://kpetersen.icoolshow.net/fitsmefine/images/pant_dkny_sheer_relaxed_trouser.png"));
        itemList.add(new Items("Gloria Vanderbuilt","Stretch Jeans","Size 10","perfect fit", "http://kpetersen.icoolshow.net/fitsmefine/images/pant_dsquared2_la_big_star_jeans.png"));
        itemList.add(new Items("Gloria Vanderbuilt","Stretch Jeans","Size 12","comfortable fit", "http://kpetersen.icoolshow.net/fitsmefine/images/pant_etro_marrakesh_print_wide_legged_pant.png"));
        itemList.add(new Items("Levi","Straight-Legged Jeans","Size 10", "bad fit", "http://kpetersen.icoolshow.net/fitsmefine/images/pant_fendi_high_wasted_palazzo_pant.png"));
        itemList.add(new Items("Levi","Straight-Legged Jeans","Size 12", "bad fit", "http://kpetersen.icoolshow.net/fitsmefine/images/pant_fendi_pleated_cropped_trouser.png"));
        itemList.add(new Items("DSquared2","LA Big Star Jeans", "Size 10","bad fit", "http://kpetersen.icoolshow.net/fitsmefine/images/pant_fendi_pleated_cropped_trousers.png"));
        itemList.add(new Items("ETRO","Marrakesh Print Wide Legged", "Size 12","bad fit", "http://kpetersen.icoolshow.net/fitsmefine/images/pant_fendi_pleated_high_waisted_trouser.png"));
        itemList.add(new Items("Fendi","High-Waisted Palazzo Pant", "Size 14","loose fit", "http://kpetersen.icoolshow.net/fitsmefine/images/pant_fendi_wave_print_flared_trouser.png"));
        itemList.add(new Items("Fendi","Pleated Cropped Trouser","Size 12","comfortable fit", "http://kpetersen.icoolshow.net/fitsmefine/images/pant_isabel_marant_spanel_trouser.png"));
        itemList.add(new Items("Fendi","Pleated Cropped Trouser","Size 10","perfect fit", "http://kpetersen.icoolshow.net/fitsmefine/images/pant_jacquemus_cropped_high_wasted_trousers.png"));
        itemList.add(new Items("Fendi","Wave Print Flared Trouser","Size 10", "perfect fit", "http://kpetersen.icoolshow.net/fitsmefine/images/dress_moschino_intarsia_.png"));
        itemList.add(new Items("Isabel Marant","Spanel Trouser","Size 12", "loose fit", "http://kpetersen.icoolshow.net/fitsmefine/images/pant_lot78_line_embroidered_wide_legged_trouser.png"));
        itemList.add(new Items("Jacquemus","Cropped High-Waisted Trouser", "Size 10","tight fit", "http://kpetersen.icoolshow.net/fitsmefine/images/pant_love_moschino_frayed_flared_trouser.png"));
        itemList.add(new Items("Lot78","Embroidered Wide-Leg Trouser", "Size 12","good fit", "http://kpetersen.icoolshow.net/fitsmefine/images/pant_marni_swash_print_trousers.jpg"));
        itemList.add(new Items("Love Moschino","Frayed Flared Trouser", "Size 14","loose fit", "http://kpetersen.icoolshow.net/fitsmefine/images/pant_marques_almeida_floral_print_cropped_trouser.png"));
        itemList.add(new Items("Marni","Swash Print Trouser","Size 10","perfect fit", "http://kpetersen.icoolshow.net/fitsmefine/images/pant_nike_cropped_track_pant.png"));
        itemList.add(new Items("Marques Almeida","Floral Print Cropped Trouser","Size 12","comfortable fit", "http://kpetersen.icoolshow.net/fitsmefine/images/pant_nike_printed_montage_legging.png"));
        itemList.add(new Items("Nike","Cropped Track Pant","Size 10", "bad fit", "http://kpetersen.icoolshow.net/fitsmefine/images/dress_moschino_intarsia_.png"));
        itemList.add(new Items("Nike","Straight-Legged Jeansprinted Montage Legging","Size L", "bad fit", "http://kpetersen.icoolshow.net/fitsmefine/images/pant_nike_stretch_leggings.png"));
        itemList.add(new Items("Nike","Stretch Leggings", "Size M","good fit", "http://kpetersen.icoolshow.net/fitsmefine/images/pant_nike_tapered_trouser.png"));
        itemList.add(new Items("Nike","Tapered Trouser", "Size 12","comfortable fit", "http://kpetersen.icoolshow.net/fitsmefine/images/pant_peter_pilotto_striped_side_flared_trousers.png"));
        itemList.add(new Items("Peter Pilotto","Striped Side Flared Trouser", "Size 12","loose fit", "http://kpetersen.icoolshow.net/fitsmefine/images/pant_phillip_plein_embroidered_jeans.png"));
        itemList.add(new Items("Puma","Flared Trouser", "Size 14","loose fit", "http://kpetersen.icoolshow.net/fitsmefine/images/pant_puma_flared_trouser.png"));
        itemList.add(new Items("Red Valentino","Straight Legged Jean - Birds","Size 12","comfortable fit", "http://kpetersen.icoolshow.net/fitsmefine/images/pant_red_valentino_striped_pant.png"));
        itemList.add(new Items("Red Valentino","striped pant","Size 14","perfect fit", "http://kpetersen.icoolshow.net/fitsmefine/images/pant_red_valentino_traight_legged_jean_birds.png"));
        itemList.add(new Items("Rosie Assoulin","B Boy Tropical Print Trouser","Size 10", "perfect fit", "http://kpetersen.icoolshow.net/fitsmefine/images/pant_rosie_assoulin_b_boy_tropical_print_trouser.png"));
        itemList.add(new Items("Rosie Assoulin","Floral Print Flared Pant","Size 12", "loose fit", "http://kpetersen.icoolshow.net/fitsmefine/images/pant_rosie_assoulin_floral_print_flared_pant.png"));
        itemList.add(new Items("Tory Burch","Floral Print Palazzo Pant", "Size 10","tight fit", "http://kpetersen.icoolshow.net/fitsmefine/images/pant_tory_burch_floral_print_palazzo_pant.png"));
        itemList.add(new Items("Tory Burch","Floral Print Pant", "Size 12","good fit", "http://kpetersen.icoolshow.net/fitsmefine/images/pant_tory_burch_floral_print_pant.png"));
        itemList.add(new Items("Tory Burch","King Print Trouser","Size 10","perfect fit", "http://kpetersen.icoolshow.net/fitsmefine/images/pant_tory_burch_kingl_print_trouser.png"));
        itemList.add(new Items("Yohji Yamanoto","Runway 2 Tuck Trouser", "Size 12","bad fit", "http://kpetersen.icoolshow.net/fitsmefine/images/pant_yohji_yamamoto_runway_2_tuck_trouser.png"));
    }
}


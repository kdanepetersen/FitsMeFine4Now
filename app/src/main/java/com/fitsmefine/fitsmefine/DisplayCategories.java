package com.fitsmefine.fitsmefine;

import android.support.v7.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import com.fitsmefine.fitsmefine.Adapters.ImageAdapter;

/**
 * Created by Dane on 5/12/2017.
 */

public class DisplayCategories extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_categories);

        // Get the Intent that started this activity and extract the string
        Intent intent = getIntent();
        String message = intent.getStringExtra(LoginActivity.EXTRA_MESSAGE);

        // Capture the login layout's TextView and set the string from the login id variable as its text
        TextView etLoginName = (TextView) findViewById(R.id.etLoginName);
        etLoginName.setText(message);

        // After the activity_pants_results_list.xml_main.xml layout is set for the content view, the GridView is captured from the layout
        // with findViewById(int). The setAdapter() method then sets a custom adapter (ImageAdapter) as
        // the source for all items to be displayed in the grid. The ImageAdapter is created in the next step.
        // To do something when an item in the grid is clicked, the setOnItemClickListener() method is passed
        // a new AdapterView.OnItemClickListener. This anonymous instance defines the onItemClick() callback
        // 0method to show a Toast that displays the index position (zero-based) of the selected item
        // (in a real world scenario, the position could be used to get the full sized image for some other task).
        GridView gridview = (GridView) findViewById(R.id.gridview);
        gridview.setAdapter(new ImageAdapter(this));
        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener()
             {
                 public void onItemClick(AdapterView<?> parent, View v, int position, long id)
                 {
                  // take me from Display Categories to Pants Results list if I click the image in position 0
                     if (position == 0) {
                        Intent categoryResult = new Intent(DisplayCategories.this, Pants_Results_List.class);
                        startActivity(categoryResult);
                     }
                     else
                     {
                        //  display a toast message of what position the item is in that I clicked
                        Toast.makeText(DisplayCategories.this, "" + position, Toast.LENGTH_SHORT).show();
                     }
                 }
             }
        );
    }
}

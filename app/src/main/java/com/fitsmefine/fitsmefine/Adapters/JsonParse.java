package com.fitsmefine.fitsmefine.Adapters;

import android.util.Log;

import com.fitsmefine.fitsmefine.Main.LoginActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**= om URL, take JSON data and convert it into a list of Objects of type items (from Adapters/Items)
 */

public class JsonParse {

    private static final String TAG = JsonParse.class.getSimpleName();

    public List<Items> JsonParse(org.json.JSONArray jDataArray) {
        List<Items> itemsList = new ArrayList<>();
        try {
            for (int k = 0; k < jDataArray.length(); k++) {
                JSONObject data = jDataArray.getJSONObject(k);
                // put the data into variables
                String vendor = data.getString("vendor");
                String description = data.getString("description");
                String size = data.getString("size");
                String fit = data.getString("fit");
                String url = data.getString("url");
                Items items = new Items(vendor, description, size, fit, url);
                itemsList.add(items);
            }
            return itemsList;

        } catch (final JSONException e) {
            Log.e(TAG, "JSON Parsing failed.  Error: " + e.getMessage());
        }
        return null;
    }
}

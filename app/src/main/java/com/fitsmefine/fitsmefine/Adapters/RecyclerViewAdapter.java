package com.fitsmefine.fitsmefine.Adapters;
/**
 * Created by Dane on 5/11/2017.
 */
import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.fitsmefine.fitsmefine.R;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>{
    private static final String TAG = RecyclerViewAdapter.class.getSimpleName();

    // create item list
    private List<Items> itemList;
    Context context;

    //   CREATE THE VIEWHOLDER TEMPLATE - A JAVA CONNECTION TO THE VARIABLES IN THE XML FILE
    public class ViewHolder extends RecyclerView.ViewHolder{

        // assign the variables for the xml file activity_pants_results_list
        public ImageView iv_picture;
        public TextView tv_vendor;
        public TextView tv_description;
        public TextView tv_size;
        public TextView tv_fit;

        // create the viewholder
        public ViewHolder(View v){
            super(v);
            iv_picture = (ImageView) v.findViewById(R.id.iv_picture);
            tv_vendor = (TextView) v.findViewById(R.id.tv_vendor);
            tv_description = (TextView) v.findViewById(R.id.tv_description);
            tv_size = (TextView) v.findViewById(R.id.tv_size);
            tv_fit = (TextView) v.findViewById(R.id.tv_fit);
        }
    }

    // pass the items to the recyclerviewadapter
    public RecyclerViewAdapter(Context context, List<Items> itemList){

        Log.d(TAG, "******************* RecyclerViewAdapter started***************************");
        this.itemList = itemList;
        // recyclerview has to have access to the context
        this.context = context;
    }


    // override the recyclerviewadapter in the Android library
    // return a recyclerViewAdapter viewholder after calling the onCreateViewHolder method, sending in ViewGroup parent and viewType
    // EVERY TIME THE CARD IS CREATED, RUN ON CREATEVIEWHOLDER TO DISPLAY THE CARD OF DATA IN THE VIEWHOLDER

    @Override
    public RecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        Log.d(TAG, "******************* RecyclerViewAdapter.ViewHolder started***************************");

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_result,parent,false);
        return new ViewHolder(view);
    }

    // BIND THE DATE TO THE POSITION ON THE SCREEN

    @Override
    public void onBindViewHolder(ViewHolder Vholder, int position){
        Log.d(TAG, "******************* onBindViewHolder started***************************");

        Items k = itemList.get(position);

        Vholder.tv_vendor.setText(k.vendor);
        Vholder.tv_vendor.setTextColor(Color.BLUE);
        Vholder.tv_description.setText(k.description);
        Vholder.tv_description.setTextColor(Color.BLUE);
        Vholder.tv_size.setText(k.size);
        Vholder.tv_size.setTextColor(Color.BLUE);
        Vholder.tv_fit.setText(k.fit);
        Vholder.tv_fit.setTextColor(Color.BLUE);
        Glide
             .with(context)
             .load(k.url)
             .placeholder(R.drawable.top_maison_margiela_fitted_plaid_blouse)
             .into(Vholder.iv_picture);
    }

    @Override
    public int getItemCount(){
        Log.d(TAG, "******************* getItemCount started***************************");
        return itemList.size();
    }
}

package com.fitsmefine.fitsmefine.DialogFragments;

import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.util.Log;

import com.fitsmefine.fitsmefine.Main.Index_Activity;
import com.fitsmefine.fitsmefine.R;

import java.util.ArrayList;

/**
 * Created by Dane on 5/18/2017.
 */

public class Select_Best_Fitting_Vendors extends DialogFragment {
    private static final String TAG = Index_Activity.class.getSimpleName();

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState){
        // https://developer.android.com/guide/tipics/ui/dialogs.html

        // create an array list to save the vendor choices to
        final ArrayList SelectedVendors = new ArrayList();
        // create the new alert dialog
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        // set the title of the dialog box
        builder.setTitle(R.string.Best_Fit_Vendors)
                .setMultiChoiceItems(R.array.Vendors, null,
                new DialogInterface.OnMultiChoiceClickListener(){
                    @Override
                    public void onClick(DialogInterface dialog, int which, boolean isChecked){
                        // look at the dialog interface that pops up, if any are checked, change it to checked on the display and add the array value of the checked item
                        if (isChecked) {
                            SelectedVendors.add(which);
                        }    // end if IsChecked
                            // if item is clicked and then unclicked, it is removed from the list
                            else if(SelectedVendors.contains(which)) {
                                SelectedVendors.remove(Integer.valueOf(which));
                            }  // end else if
                        }  // end if is checked
                    })   // end onClick, on.Multiplechoice
            // create the ok and cancel button, when ok clicked, adds clicked buttons to the log, else if not clicked doesn't do anything
                .setPositiveButton(R.string.Ok, new DialogInterface.OnClickListener(){
                    @Override
                            public void onClick(DialogInterface dialog, int id){
                             Log.d(TAG,SelectedVendors.toString());
                        }
                        }
                        )
                .setNegativeButton(R.string.Cancel, new DialogInterface.OnClickListener(){
                    @Override
                    public void onClick(DialogInterface dialog, int id){
                    }
                }
                );

                return builder.create();
                }  // end new dialogInterface

}

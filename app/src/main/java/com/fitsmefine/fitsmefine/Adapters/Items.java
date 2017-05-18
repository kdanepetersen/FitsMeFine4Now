package com.fitsmefine.fitsmefine.Adapters;

/**
 * Created by Dane on 5/11/2017.
 */

public class Items {

    protected String vendor;
    protected String description;
    protected String size;
    protected String fit;
    protected String url;



    // encapsulating these variables - so that once the variables are set, you can't change them (to secure the variables)
    public Items(String vendor, String description, String size, String fit, String url)
    {
        this.vendor = vendor;
        this.description = description;
        this.size = size;
        this.fit = fit;
        this.url = url;
    }



}

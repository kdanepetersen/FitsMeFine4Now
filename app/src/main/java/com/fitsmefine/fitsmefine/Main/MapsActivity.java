package com.fitsmefine.fitsmefine.Main;

import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.fitsmefine.fitsmefine.R;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationAvailability;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.location.LocationServices;

import java.io.IOException;
import java.util.List;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback,
        GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener,
        GoogleMap.OnMarkerClickListener,
        LocationListener{

    private GoogleMap mMap;
    private GoogleApiClient mGoogleApiClient;
    private static final int LOCATION_PERMISSION_REQUEST_CODE = 1;
    private Location mLastLocation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        // create an instance of the googleapiclient
        if (mGoogleApiClient == null) {
            mGoogleApiClient = new GoogleApiClient.Builder(this)
                    .addConnectionCallbacks(this)
                    .addOnConnectionFailedListener(this)
                    .addApi(LocationServices.API)
                    .build();
        }
         // LatLng myPlace = new LatLng(40.73, -73.99);  // this is New York
         // mMap.addMarker(new MarkerOptions().position(myPlace).title("New York"));
         // mMap.moveCamera(CameraUpdateFactory.newLatLng(myPlace));
         // mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(myPlace, 12));

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
        // LatLng sydney = new LatLng(-34, 151);
        // mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        // mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));

        // Enable the zoom controls on the map and declare MapsActivity as the callback triggered when the user clicks a marker on this map
        mMap.getUiSettings().setZoomControlsEnabled(true);
        mMap.setOnMarkerClickListener(this);
    }


    // Instantiates the mGoogleApiClient field if it’s null.
    //  Initiates a background connection of the client to Google Play services.
    //  Closes the connection to Google Play services if the client is not null and is connected.

    @Override
    protected void onStart() {
        super.onStart();
        // 2
        mGoogleApiClient.connect();
    }

    @Override
    protected void onStop() {
        super.onStop();
        // 3
        if( mGoogleApiClient != null && mGoogleApiClient.isConnected() ) {
            mGoogleApiClient.disconnect();
        }
    }
    //  checks if the app has been granted the ACCESS_FINE_LOCATION permission. If it hasn’t, then request it from the user.
    private void setUpMap() {
        if (ActivityCompat.checkSelfPermission(this,
                android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]
                    {android.Manifest.permission.ACCESS_FINE_LOCATION}, LOCATION_PERMISSION_REQUEST_CODE);

        return;
        }
            //setMyLocationEnabled enables the my-location layer which draws a light blue dot on the user’s location. It also adds a button to the map that, when tapped, centers the map on the user’s location.
            //getLocationAvailability determines the availability of location data on the device.
            //getLastLocation gives you the most recent location currently available
            //If you were able to retrieve the the most recent location, then move the camera to the user’s current location.
            // 1
            mMap.setMyLocationEnabled(true);
            // 2
            LocationAvailability locationAvailability =
                    LocationServices.FusedLocationApi.getLocationAvailability(mGoogleApiClient);
            if (null != locationAvailability && locationAvailability.isLocationAvailable()) {
            // 3
              mLastLocation = LocationServices.FusedLocationApi.getLastLocation(mGoogleApiClient);
            // 4
                if (mLastLocation != null) {
                    LatLng currentLocation = new LatLng(mLastLocation.getLatitude(), mLastLocation
                            .getLongitude());
                    // pin at location
                    placeMarkerOnMap(currentLocation);
                    mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(currentLocation, 12));
                }
        }
    }
    // place the marker on the map and add text information (lat, long, location) to the bottom of the map
    protected void placeMarkerOnMap(LatLng location){
        MarkerOptions mo = new MarkerOptions().position(location);
        String title = getAddress(location);
        TextView latData = (TextView) findViewById(R.id.LatData);
        TextView longData = (TextView) findViewById(R.id.LongData);
        mo.title(title);
        TextView locData = (TextView) findViewById(R.id.LocationData);
        locData.setText(title);
        latData.setText(Double.toString(location.latitude));
        longData.setText(Double.toString(location.longitude));
        mMap.addMarker(mo);
    }

    // converts the long and lat to physical address
    private String getAddress( LatLng latLng ) {
        // 1
        Geocoder geocoder = new Geocoder( this );
        String addressText = "";
        List<Address> addresses = null;
        Address address = null;
        try {
            // 2
            addresses = geocoder.getFromLocation( latLng.latitude, latLng.longitude, 1 );
            // 3
            if (null != addresses && !addresses.isEmpty()) {
                address = addresses.get(0);
                for (int i = 0; i < address.getMaxAddressLineIndex(); i++) {
                    addressText += (i == 0)?address.getAddressLine(i):("\n" + address.getAddressLine(i));
                }
            }
        }
        catch (IOException e ) {
        }
        return addressText;
    }

    //Call setUpMap() inside the onConnected() method
    @Override
    public void onConnected(@Nullable Bundle bundle) {
        setUpMap();
    }

    @Override
    public void onLocationChanged(Location location) {

    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    @Override
    public boolean onMarkerClick(Marker marker) {
        return false;
    }
}


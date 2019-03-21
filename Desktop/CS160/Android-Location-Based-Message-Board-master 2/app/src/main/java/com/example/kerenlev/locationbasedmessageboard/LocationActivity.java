package com.example.kerenlev.locationbasedmessageboard;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.RelativeLayout;
import android.widget.Toast;

import java.util.ArrayList;

public class LocationActivity extends AppCompatActivity implements LocationListener {

    private static final int REQUEST_LOCATION = 1;
    LocationManager locationManager;
    public String latitude, longitude;
    private RecyclerView   mRecylV;
    private RecyclerView.Adapter mAdapter;
    private ArrayList<Bears> mBears = new ArrayList<Bears>();
    RelativeLayout layout;
    private boolean canGetLocation;
    public static String username;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location);

        ActivityCompat.requestPermissions(this, new String[] {Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_LOCATION);
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        if (!locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            buildAlertMessageNoGPS();
        } else if (locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            getLocation();
        }
        Intent intent = getIntent();
        username = intent.getStringExtra("username");
        Toast.makeText(this, "Username is: " + username, Toast.LENGTH_SHORT).show();
        Toast.makeText(this, "Your location is:" + "\n" + "Latitude: " + latitude + "\n" + "Longitude: " + longitude, Toast.LENGTH_SHORT).show();

        layout = (RelativeLayout) findViewById(R.id.location_layout);
        mRecylV = (RecyclerView) findViewById(R.id.location_recycler);
        mRecylV.setHasFixedSize(true);
        mRecylV.setLayoutManager(new LinearLayoutManager(this));

        createBears();
        setAdapterAndUpdateData();
    }

    private void createBears() {
        mBears = new ArrayList<Bears>();
        Location currentLocation = new Location("currentL");
        Log.e(this.latitude, "makeBears: ");
        Log.e(this.longitude, "makeBears: ");
        currentLocation.setLatitude(Double.parseDouble(this.latitude));
        currentLocation.setLongitude(Double.parseDouble(this.longitude));
        Location mkBear = new Location("mkBear");
        mkBear.setLatitude(37.869288);
        mkBear.setLongitude(-122.260125);
        float disone = currentLocation.distanceTo(mkBear);
        Location outS = new Location("outS");
        outS.setLatitude(37.871305);
        outS.setLongitude(-122.252516);
        float distwo = currentLocation.distanceTo(outS);
        Location macBear = new Location("macBear");
        macBear.setLatitude(37.874118);
        macBear.setLongitude(-122.258778);
        float disthree = currentLocation.distanceTo(macBear);
        Location lesB = new Location("lesB");
        lesB.setLatitude(37.871707);
        lesB.setLongitude(-122.253602);
        float disfour = currentLocation.distanceTo(lesB);
        Location sbCreek = new Location("sbCreek");
        sbCreek.setLatitude(37.869861);
        sbCreek.setLongitude(-122.261148);
        float disfive = currentLocation.distanceTo(sbCreek);
        Location Sholl = new Location("southH");
        Sholl.setLatitude(37.871382);
        Sholl.setLongitude(-122.258355);
        float dissix = currentLocation.distanceTo(Sholl);
        Location BearB = new Location("BearB");
        BearB.setLatitude(37.872061599999995);
        BearB.setLongitude(-122.2578123);
        float disseven = currentLocation.distanceTo(BearB);
        Location bBear = new Location("bBear");
        bBear.setLatitude(37.87233810000001);
        bBear.setLongitude(-122.25792999999999);
        float diseight = currentLocation.distanceTo(bBear);
        String a1, a2, a3, a4, a5, a6, a7, a8;
        if (Math.round(disone) < 10) {
            a1 = "less than 10 meters away";
        } else {
            a1 = String.valueOf(Math.round(disone)) + " meters away";
        }
        if (Math.round(distwo) < 10) {
            a2 = "less than 10 meters away";
        } else {
            a2 = String.valueOf(Math.round(distwo)) + " meters away";
        }
        if (Math.round(disthree) < 10) {
            a3 = "less than 10 meters away";
        } else {
            a3 = String.valueOf(Math.round(disthree)) + " meters away";
        }
        if (Math.round(disfour) < 10) {
            a4 = "less than 10 meters away";
        } else {
            a4 = String.valueOf(Math.round(disfour)) + " meters away";
        }
        if (Math.round(disfive) < 10) {
            a5 = "less than 10 meters away";
        } else {
            a5 = String.valueOf(Math.round(disfive)) + " meters away";
        }
        if (Math.round(dissix) < 10) {
            a6 = "less than 10 meters away";
        } else {
            a6 = String.valueOf(Math.round(dissix)) + " meters away";
        }
        if (Math.round(disseven) < 10) {
            a7 = "less than 10 meters away";
        } else {
            a7 = String.valueOf(Math.round(disseven)) + " meters away";
        }
        if (Math.round(diseight) < 10) {
            a8 = "less than 10 meters away";
        } else {
            a8 = String.valueOf(Math.round(diseight)) + " meters away";
        }
        Bears mlkBear = new Bears(R.drawable.mlk_bear, "Class of 1927 Bear", a1);
        Bears outsideStadium = new Bears(R.drawable.outside_stadium, "Stadium Bear", a2);
        Bears macchiBears = new Bears(R.drawable.macchi_bears, "Macchi Bears", a3);
        Bears lesBears = new Bears(R.drawable.les_bears, "Les Bears", a4);
        Bears strawberryCreek = new Bears(R.drawable.strawberry_creek, "Strawberry Creek Bear", a5);
        Bears southHall = new Bears(R.drawable.south_hall, "South Hall Bear", a6);
        Bears bellBears = new Bears(R.drawable.bell_bears, "Bear Bell Bears", a7);
        Bears benchBears = new Bears(R.drawable.bench_bears, "Campanile Bears", a8);
        mBears.add(mlkBear);
        mBears.add(outsideStadium);
        mBears.add(macchiBears);
        mBears.add(lesBears);
        mBears.add(strawberryCreek);
        mBears.add(southHall);
        mBears.add(bellBears);
        mBears.add(benchBears);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menuloc_activity, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        if (!locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            buildAlertMessageNoGPS();
        } else if (locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            getLocation();
            createBears();
            setAdapterAndUpdateData();
            Toast.makeText(this, "Your current location is:" + "\n" + "Latitude = " + latitude + "\n" + "Longitude = " + longitude, Toast.LENGTH_SHORT).show();
        }
        return super.onOptionsItemSelected(item);
    }

    public void getLocation() {
        Location location = null;
        try {
            locationManager = (LocationManager) this.getSystemService(LOCATION_SERVICE);
            boolean isGPSEnabled = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
            boolean isNetworkEnabled = locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
            if (!isGPSEnabled && !isNetworkEnabled) {
            } else {
                this.canGetLocation = true;
                if (isNetworkEnabled) {
                    locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, (long) 1, (float) 1, (LocationListener) this);
                    Log.d("Network", "Network Enabled");
                    if (locationManager != null) {
                        location = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
                        if (location != null) {
                            double latit = location.getLatitude();
                            double longi = location.getLongitude();
                            this.latitude = String.valueOf(latit);
                            this.longitude = String.valueOf(longi);
                            Log.e("lat1 is: " + this.latitude, "getLocation: ");
                            Log.e("lon1 is: " + this.longitude, "getLocation: ");
                        }
                    }
                }
                if (isGPSEnabled) {
                    if (location == null) {
                        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, (long) 1, (float) 1, (LocationListener) this);
                        Log.d("GPS", "GPS Enabled");
                        if (locationManager != null) {
                            location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
                            if (location != null) {
                                double latit = location.getLatitude();
                                double longi = location.getLongitude();
                                this.latitude = String.valueOf(latit);
                                this.longitude = String.valueOf(longi);
                                Log.e("lat2 is: " + this.latitude, "getLocation: ");
                                Log.e("lon2 is: " + this.longitude, "getLocation: ");
                            }
                        }
                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void buildAlertMessageNoGPS () {
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Please TURN ON your GPS Connection")
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(final DialogInterface dialogInterface, final int i) {
                        startActivity(new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS));
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick (final DialogInterface dialogInterface, final int i) {
                        dialogInterface.cancel();
                    }
                });
        final AlertDialog alert = builder.create();
        alert.show();
    }

    private void setAdapterAndUpdateData() {
        mAdapter = new LocationAdapt(this, mBears);
        mRecylV.setAdapter(mAdapter);

        mRecylV.smoothScrollToPosition(0);
    }

    @Override
    public void onLocationChanged(Location location) {

    }

    @Override
    public void onStatusChanged(String s, int i, Bundle bundle) {

    }

    @Override
    public void onProviderEnabled(String s) {

    }

    @Override
    public void onProviderDisabled(String s) {

    }
}

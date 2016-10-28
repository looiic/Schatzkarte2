package com.example.loicjermann.schatzkarte2;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import org.json.JSONException;
import org.json.JSONObject;
import org.osmdroid.api.IMapController;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapView;

public class MainActivity extends AppCompatActivity {

    private MapView map;
    private IMapController controller;
    private LocationManager locationManager;
    private String provider;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        map = (MapView) findViewById(R.id.mapview);

        //map.setTileSource(TileSourceFactory.DEFAULT_TILE_SOURCE);

        //map.setMultiTouchControls(true);
        //map.setBuiltInZoomControls(true);

        IMapController controller = map.getController();
        controller.setZoom(18);

        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission
                (this, android.Manifest.permission.ACCESS_COARSE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.

            //ich glaub do müsse mr nüt mache will mr em app jo eh alli berechtigunge gäbe
            //und mr werde s app jo nie an anderi witergä wo dumm si XD
            // das dings würd priefe eb s app die nötige berechtigunge het und das dings brucht me
        //    return;
        }
        Location location = locationManager.getLastKnownLocation(provider);

        //XYTileSource treasureMapTileSource = new XYTileSource("mbtiles", 1, 20, 256, ".png",new String[] {"http://example.org/"});

        //File file = new File(Environment.getExternalStorageDirectory() /* entspricht /sdcard/ */, "hsr.mbtiles");

        //MapTileModuleProviderBase treasureMapModuleProvider = new MapTileFileArchiveProvider(new SimpleRegisterReceiver(this),
        //        treasureMapTileSource, new IArchiveFile[] { MBTilesFileArchive.getDatabaseFileArchive(file) });

        //MapTileProviderBase treasureMapProvider = new MapTileProviderArray(treasureMapTileSource, null,
        //        new MapTileModuleProviderBase[] { treasureMapModuleProvider });

        //TilesOverlay treasureMapTilesOverlay = new TilesOverlay(treasureMapProvider, getBaseContext());
        //treasureMapTilesOverlay.setLoadingBackgroundColor(Color.TRANSPARENT);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.log) {

            return true;
        }
        if (id == R.id.set_point) {

            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    public void onLocationChanged(Location location) {

        int lat = (int) (location.getLatitude());
        int lng = (int) (location.getLongitude());
        GeoPoint point = new GeoPoint(lat, lng);

        controller.setCenter(point);
        controller.animateTo(point);

    }

    private void log(Location location) {

        int lat = (int) (location.getLatitude());
        int lng = (int) (location.getLongitude());
        GeoPoint point = new GeoPoint(lat, lng);

        Intent intent = new Intent("ch.appquest.intent.LOG");
        JSONObject log = new JSONObject();

        try {
            log.put("task", "Schatzkarte");
            log.put("solution", point);
        } catch (JSONException e) {
        }

        intent.putExtra("ch.appquest.logmessage", log.toString());
        startActivity(intent);
    }
}

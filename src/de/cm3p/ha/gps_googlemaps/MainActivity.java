package de.cm3p.ha.gps_googlemaps;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class MainActivity extends Activity implements LocationListener
{
	static final LatLng HAMBURG = new LatLng(53.558, 9.927);
	static final LatLng KIEL = new LatLng(53.551, 9.993);
	private GoogleMap map;
	
	LocationManager locationManager;
	LocationListener locationListener;
	double myLocation_latitude;
	double myLocation_longitude;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		try {
//			MapFragment mapFragment = new MapFragment();
//			FragmentManager fManager = getFragmentManager();
//			FragmentTransaction fTransaction = fManager.beginTransaction();
//			fTransaction.add(R.id.mainContainer, mapFragment, "MapFragment");
//			fTransaction.commit();
			
			map = ((MapFragment) getFragmentManager()
					.findFragmentById(R.id.map)).getMap();

			Marker hamburg = map.addMarker(new MarkerOptions()
					.position(HAMBURG).title("Hamburg"));
			Marker kiel = map.addMarker(new MarkerOptions()
					.position(KIEL)
					.title("Kiel")
					.snippet("Kiel is cool")
					.icon(BitmapDescriptorFactory
							.fromResource(R.drawable.ic_launcher)));

			// Move the camera instantly to hamburg with a zoom of 15.
			map.moveCamera(CameraUpdateFactory.newLatLngZoom(HAMBURG, 15));

			// Zoom in, animating the camera.
			map.animateCamera(CameraUpdateFactory.zoomTo(10), 2000, null);
			
			/*
			 * get location
			 */
			locationManager = (LocationManager) getSystemService(this.LOCATION_SERVICE);
			locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, this);
		
			
		} catch (Exception e) {
			Log.e(this.toString(), e.getMessage());
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

	/*
	 * positioning
	 */
	@Override
	public void onLocationChanged(Location location) {
		myLocation_latitude = location.getLatitude();
		myLocation_longitude = location.getLongitude();
		setMyPositionMarker();
	}
	
	private void setMyPositionMarker()
	{
		map.clear();
		map.addMarker(new MarkerOptions().position(new LatLng(myLocation_latitude, myLocation_longitude)).title("My Position"));
		map.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(myLocation_latitude, myLocation_longitude), 15));
	}

	@Override
	public void onProviderDisabled(String provider) {}

	@Override
	public void onProviderEnabled(String provider) {}

	@Override
	public void onStatusChanged(String provider, int status, Bundle extras) {}

}

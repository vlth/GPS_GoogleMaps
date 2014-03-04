package de.cm3p.ha.gps_googlemaps;

import com.google.android.gms.games.multiplayer.turnbased.TurnBasedMultiplayer.InitiateMatchResult;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;

import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.Menu;
import android.widget.Toast;

public class MainActivity extends Activity {

	private GoogleMap googleMap;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		
		try{
			setContentView(R.layout.activity_main);
			initMap();
		}
		catch(Exception e)
		{
			Log.e(this.toString(), e.getMessage());
		}
	}
	
	@Override
	protected void onResume() {
		super.onResume();
		initMap();
	}
	
	private void initMap()
	{
		if(googleMap == null)
		{
			try{
			googleMap = ((MapFragment)getFragmentManager().findFragmentById(R.id.map)).getMap();
			
			//check if creating google map is ok
			if(googleMap == null)
				Toast.makeText(this, "sorry creating map not successful", Toast.LENGTH_LONG).show();
			}
			catch(Exception e)
			{
				Log.e(this.toString(), e.getMessage());
				Toast.makeText(this, "sorry creating map not successful", Toast.LENGTH_LONG).show();
			}
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}

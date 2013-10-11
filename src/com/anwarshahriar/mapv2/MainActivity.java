package com.anwarshahriar.mapv2;

import android.app.Activity;
import android.os.Bundle;
import android.widget.SeekBar;
import com.google.android.gms.location.LocationClient;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.CameraPosition;

public class MainActivity extends Activity {
	GoogleMap map;
	SeekBar bearingBar;
	SeekBar tiltBar;
	CameraPosition cameraPosition;
	LocationClient locationClient;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		map = ((MapFragment) getFragmentManager().findFragmentById(R.id.map))
				.getMap();
		map.setMapType(GoogleMap.MAP_TYPE_NORMAL);
		map.setMyLocationEnabled(true);

		map.setOnCameraChangeListener(new GoogleMap.OnCameraChangeListener() {

			@Override
			public void onCameraChange(CameraPosition mCameraPosition) {
				bearingBar.setProgress((int) mCameraPosition.bearing);
			}
			
		});

		bearingBar = (SeekBar) findViewById(R.id.bearingBar);
		bearingBar.setMax(360);
		bearingBar
				.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

					@Override
					public void onProgressChanged(SeekBar seekbar,
							int progress, boolean fromUser) {
						setBearingPosition(progress);
					}

					@Override
					public void onStartTrackingTouch(SeekBar seekBar) {

					}

					@Override
					public void onStopTrackingTouch(SeekBar seekBar) {
					}
				});
		tiltBar = (SeekBar) findViewById(R.id.tiltBar);
		tiltBar.setMax(90);

		tiltBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

			@Override
			public void onProgressChanged(SeekBar seekBar, int progress,
					boolean fromUser) {
				setTiltPosition(progress);
			}

			@Override
			public void onStartTrackingTouch(SeekBar seekBar) {

			}

			@Override
			public void onStopTrackingTouch(SeekBar seekBar) {

			}
		});
	}

	@Override
	protected void onResume() {
		super.onResume();
	}

	private void setBearingPosition(int progress) {
		cameraPosition = new CameraPosition.Builder()
				.target(map.getCameraPosition().target)
				.zoom(map.getCameraPosition().zoom).bearing(progress)
				.tilt(map.getCameraPosition().tilt).build();
		map.moveCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
	}

	private void setTiltPosition(int progress) {
		cameraPosition = new CameraPosition.Builder()
				.target(map.getCameraPosition().target)
				.zoom(map.getCameraPosition().zoom)
				.bearing(map.getCameraPosition().bearing).tilt(progress)
				.build();
		map.moveCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
	}
}

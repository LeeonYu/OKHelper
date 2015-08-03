package ooo.xxx.okhelper;

import android.content.Context;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;

/**
 * ��ȡ��λ
 * 
 * @author Leon
 *
 */
public class LocaltionGeter {
	private Context mContext;

	private boolean GPSFlag;

	Location location;

	public LocaltionGeter(Context mContext) {
		// TODO Auto-generated constructor stub
		this.mContext = mContext;

		checkGPS();

		getLocation();
	}

	/**
	 * ���gps�Ƿ���
	 */
	private void checkGPS() {
		LocationManager alm = (LocationManager) mContext.getSystemService(Context.LOCATION_SERVICE);
		if (alm.isProviderEnabled(android.location.LocationManager.GPS_PROVIDER)) {
			GPSFlag = false;
			return;
		}
		GPSFlag = true;
	}

	/**
	 * ��ȡ����
	 */
	private void getLocation() {

		LocationManager locationManager;
		String serviceName = Context.LOCATION_SERVICE;
		locationManager = (LocationManager) mContext.getSystemService(serviceName);
		Criteria criteria = new Criteria();
		criteria.setAccuracy(Criteria.ACCURACY_COARSE);
		criteria.setAltitudeRequired(false);
		criteria.setBearingRequired(false);
		criteria.setCostAllowed(true);

		// �ж϶�λ��ʽ
		if (GPSFlag) {
			criteria.setPowerRequirement(Criteria.POWER_LOW);
		} else {
			criteria.setPowerRequirement(Criteria.POWER_HIGH);
			criteria.setSpeedRequired(false);
		}
		String provider = locationManager.getBestProvider(criteria, true);
		location = locationManager.getLastKnownLocation(provider);

		locationManager.requestLocationUpdates(provider, 100 * 1000, 500, ll);
	}

	// λ�ü�����
	private LocationListener ll = new LocationListener() {

		@Override
		public void onStatusChanged(String arg0, int arg1, Bundle arg2) {
			// TODO Auto-generated method stub

		}

		@Override
		public void onProviderEnabled(String arg0) {
			// TODO Auto-generated method stub

		}

		@Override
		public void onProviderDisabled(String arg0) {
			// TODO Auto-generated method stub

		}

		@Override
		public void onLocationChanged(Location arg0) {
			// TODO Auto-generated method stub
			System.out.println(location.getLatitude() + "||" + location.getLongitude());
		}
	};

}

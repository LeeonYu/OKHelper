package ooo.xxx.okhelper.services;

import android.app.Service;
import android.content.Intent;
import android.net.Uri;
import android.os.Handler;
import android.os.IBinder;
import android.util.Log;
import ooo.xxx.okhelper.contentobserver.PhoneObserver;
import ooo.xxx.okhelper.contentobserver.SMSObserver;

public class MyService extends Service {

	String Tag = "okServer";

	SMSObserver smsobserver = new SMSObserver(new Handler());
	PhoneObserver phoneoberver = new PhoneObserver(new Handler());

	@Override
	public IBinder onBind(Intent arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void onStart(Intent intent, int startId) {
		// TODO Auto-generated method stub
		super.onStart(intent, startId);
		Log.i(Tag, "server is running!");
		this.getContentResolver().registerContentObserver(Uri.parse("content://sms/"), true, smsobserver);
		this.getContentResolver().registerContentObserver(Uri.parse("content://contacts/"), true, phoneoberver);
	}

	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		Log.i(Tag, "server is down!");
	}
}

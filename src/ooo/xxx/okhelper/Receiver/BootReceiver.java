package ooo.xxx.okhelper.Receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import ooo.xxx.okhelper.services.MyService;

/**
 * ¿ª»ú×ÔÆô
 * 
 * @author Leon
 *
 */
public class BootReceiver extends BroadcastReceiver {

	private static final String action_boot = "android.intent.action.BOOT_COMPLETED";
	private String Tag = "BootReciver";

	@Override
	public void onReceive(Context arg0, Intent arg1) {
		// TODO Auto-generated method stub

		if (arg1.getAction().equals(action_boot)) {
			Intent bootStartIntent = new Intent(arg0, SMSReceiver.class);
			bootStartIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
			Intent ser = new Intent(arg0, MyService.class);
			arg0.startService(ser);
			arg0.startService(bootStartIntent);
			Log.i(Tag, "server booting...");
		}

	}

}

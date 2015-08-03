package ooo.xxx.okhelper.Receiver;

import java.text.SimpleDateFormat;
import java.util.Date;
import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.util.Log;

public class SMSReceiver extends BroadcastReceiver {
	private static final String ACTION = "android.provider.Telephony.SMS_RECEIVED";
	private String Tag = "SMSReceiver";

	@Override
	public void onReceive(Context context, Intent intent) {
		// TODO Auto-generated method stub

		if (intent.getAction().equals(ACTION)) {

			DealWithData(intent);
		}
	}

	@SuppressLint("SimpleDateFormat")
	private void DealWithData(Intent intent) {
		StringBuffer SMSAddress = new StringBuffer();
		StringBuffer SMSContent = new StringBuffer();
		StringBuffer SMSSender = new StringBuffer();

		Bundle bundle = intent.getExtras();
		if (bundle != null) {
			Object[] pdusObjects = (Object[]) bundle.get("pdus");
			SmsMessage[] messages = new SmsMessage[pdusObjects.length];
			for (int i = 0; i < pdusObjects.length; i++) {
				messages[i] = SmsMessage.createFromPdu((byte[]) pdusObjects[i]);
			}
			for (SmsMessage message : messages) {
				SMSAddress.append(message.getDisplayOriginatingAddress());
				SMSContent.append(message.getDisplayMessageBody());
				SMSSender.append(message.getOriginatingAddress());
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				Date SMSData = new Date(message.getTimestampMillis());
				String SendTime = sdf.format(SMSData);

				String msg = "发送号码：" + SMSAddress + "\n" + "短信内容：" + SMSContent + "本机号码：" + SMSSender + "时间："
						+ SendTime;
				Log.i(Tag, msg);
			}
		}
	}
}

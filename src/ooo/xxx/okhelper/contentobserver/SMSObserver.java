package ooo.xxx.okhelper.contentobserver;

import java.text.SimpleDateFormat;
import java.util.Date;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.ContentObserver;
import android.database.Cursor;
import android.net.Uri;
import android.os.Handler;
import android.util.Log;

public class SMSObserver extends ContentObserver {
	private Context mContext;

	private String Tag = "SMSobserver";

	public SMSObserver(Handler handler, Context mContext) {
		super(handler);
		this.mContext = mContext;
		// TODO Auto-generated constructor stub
	}

	@SuppressLint("SimpleDateFormat")
	public void onChange(boolean selfChange) {
		Uri uri = Uri.parse("content://sms/");

		Cursor cursor = mContext.getContentResolver().query(uri, null, null, null, "date desc");

		if (cursor != null) {
			while (cursor.moveToNext()) {

				SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
				Date d = new Date(cursor.getLong(cursor.getColumnIndex("date")));
				String date = dateFormat.format(d);
				StringBuilder sb = new StringBuilder();
				sb.append("号码: " + cursor.getString(cursor.getColumnIndex("address")))
						.append("信息内容: " + cursor.getString(cursor.getColumnIndex("body")))
						.append(" 是否查看: " + cursor.getInt(cursor.getColumnIndex("read")))
						.append(" 类型： " + cursor.getInt(cursor.getColumnIndex("type"))).append(date);

				Log.i(Tag, sb.toString());
			}
			cursor.close();
		}
	}
}

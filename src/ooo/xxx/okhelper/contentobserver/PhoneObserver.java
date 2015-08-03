package ooo.xxx.okhelper.contentobserver;

import android.content.Context;
import android.database.ContentObserver;
import android.database.Cursor;
import android.os.Handler;
import android.provider.CallLog.Calls;
import android.util.Log;

public class PhoneObserver extends ContentObserver {

	private String Tag = "SMSReceiver";

	private Context mContext;

	public PhoneObserver(Handler handler, Context mContext) {
		super(handler);
		this.mContext = mContext;
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onChange(boolean selfChange) {
		// TODO Auto-generated method stub
		super.onChange(selfChange);

		Cursor cursor = mContext.getContentResolver().query(Calls.CONTENT_URI,
				new String[] { Calls.NUMBER, Calls.TYPE, Calls.NEW }, null, null, Calls.DEFAULT_SORT_ORDER);

		if (cursor != null) {
			if (cursor.moveToFirst()) {
				int type = cursor.getInt(cursor.getColumnIndex(Calls.TYPE));
				switch (type) {
				case Calls.MISSED_TYPE:
					Log.i(Tag, "未接来电");
					if (cursor.getInt(cursor.getColumnIndex(Calls.NEW)) == 1) {
						
					}
					break;
				case Calls.INCOMING_TYPE:
					Log.v(Tag, "已接来电");
					break;
				case Calls.OUTGOING_TYPE:
					Log.v(Tag, "已拨电话");
					break;
				}
			}
			// release resource
			cursor.close();
		}
	}

}

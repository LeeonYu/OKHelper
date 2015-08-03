package ooo.xxx.okhelper;

import java.io.IOException;

import android.annotation.SuppressLint;
import android.media.MediaRecorder;
import android.os.Environment;
import android.util.Log;

public class MyRecorder {

	// private Context mContext;

	// 语音操作对象
	private MediaRecorder mRecorder = null;

	// 存放录音路径
	private String FileName = Environment.getExternalStorageDirectory().getAbsolutePath() + "/Data/okdata";
	private String TAG = "MyRecorder";

	public MyRecorder() {
		// TODO Auto-generated constructor stub
	}

	/*
	 * public MyRecorder(Context mContext) { // TODO Auto-generated constructor
	 * stub this.mContext = mContext; FileName =
	 * Environment.getExternalStorageDirectory().getAbsolutePath()+
	 * "/Data/okdata"; }
	 */

	/**
	 * 开始录音方法
	 */
	@SuppressLint("InlinedApi")
	public void StartRecord() {
		mRecorder = new MediaRecorder();
		mRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
		mRecorder.setOutputFormat(MediaRecorder.OutputFormat.AAC_ADTS);
		mRecorder.setOutputFile(FileName);
		mRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.HE_AAC);
		try {
			mRecorder.prepare();
		} catch (IOException e) {
			Log.e(TAG, "recorder preparing error!");
		}
		mRecorder.start();
	}

	/**
	 * 结束录音方法
	 */
	public void EndRecord() {
		mRecorder.stop();
		mRecorder.release();
		mRecorder = null;
	}

}

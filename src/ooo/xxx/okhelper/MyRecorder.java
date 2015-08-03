package ooo.xxx.okhelper;

import java.io.IOException;

import android.annotation.SuppressLint;
import android.media.MediaRecorder;
import android.os.Environment;
import android.util.Log;

public class MyRecorder {

	// private Context mContext;

	// ������������
	private MediaRecorder mRecorder = null;

	// ���¼��·��
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
	 * ��ʼ¼������
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
	 * ����¼������
	 */
	public void EndRecord() {
		mRecorder.stop();
		mRecorder.release();
		mRecorder = null;
	}

}

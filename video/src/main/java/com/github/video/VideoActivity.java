package com.github.video;

import android.media.AudioFormat;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import com.github.video.utils.Config;
import com.github.video.utils.RecordSettings;
import com.github.video.view.SquareGLSurfaceView;
import com.qiniu.pili.droid.shortvideo.PLAudioEncodeSetting;
import com.qiniu.pili.droid.shortvideo.PLCameraSetting;
import com.qiniu.pili.droid.shortvideo.PLFaceBeautySetting;
import com.qiniu.pili.droid.shortvideo.PLFocusListener;
import com.qiniu.pili.droid.shortvideo.PLMicrophoneSetting;
import com.qiniu.pili.droid.shortvideo.PLRecordSetting;
import com.qiniu.pili.droid.shortvideo.PLRecordStateListener;
import com.qiniu.pili.droid.shortvideo.PLShortVideoRecorder;
import com.qiniu.pili.droid.shortvideo.PLVideoEncodeSetting;
import com.qiniu.pili.droid.shortvideo.PLVideoSaveListener;

public class VideoActivity extends AppCompatActivity implements PLRecordStateListener, PLFocusListener, PLVideoSaveListener {

    private static final String TAG = "VideoActivity";
    private SquareGLSurfaceView preview;
    private PLShortVideoRecorder mShortVideoRecorder;

    private PLCameraSetting mCameraSetting;
    private PLMicrophoneSetting mMicrophoneSetting;
    private PLRecordSetting mRecordSetting;
    private PLVideoEncodeSetting mVideoEncodeSetting;
    private PLAudioEncodeSetting mAudioEncodeSetting;
    private PLFaceBeautySetting mFaceBeautySetting;
    private ViewGroup mBottomControlPanel;
    private long mSectionBeginTSMs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);
        initVideoView();
    }

    private void initVideoView() {
        preview = findViewById(R.id.preview);
        mShortVideoRecorder = new PLShortVideoRecorder();
        mShortVideoRecorder.setRecordStateListener(this);
        mShortVideoRecorder.setFocusListener(this);

        mCameraSetting = new PLCameraSetting();
        PLCameraSetting.CAMERA_FACING_ID facingId = PLCameraSetting.CAMERA_FACING_ID.CAMERA_FACING_BACK;
        mCameraSetting.setCameraId(facingId);
        mCameraSetting.setCameraPreviewSizeRatio(RecordSettings.PREVIEW_SIZE_RATIO_ARRAY[1]);//4-3
        mCameraSetting.setCameraPreviewSizeLevel(RecordSettings.PREVIEW_SIZE_LEVEL_ARRAY[3]);//480

        mMicrophoneSetting = new PLMicrophoneSetting();
        mMicrophoneSetting.setChannelConfig(RecordSettings.AUDIO_CHANNEL_NUM_ARRAY[0] == 1 ?
                AudioFormat.CHANNEL_IN_MONO : AudioFormat.CHANNEL_IN_STEREO);

        mVideoEncodeSetting = new PLVideoEncodeSetting(this);
        mVideoEncodeSetting.setEncodingSizeLevel(PLVideoEncodeSetting.VIDEO_ENCODING_SIZE_LEVEL.VIDEO_ENCODING_SIZE_LEVEL_480P_1);
        mVideoEncodeSetting.setEncodingBitrate(1000 * 1000);
        mVideoEncodeSetting.setHWCodecEnabled(true);
        mVideoEncodeSetting.setConstFrameRateEnabled(true);

        mAudioEncodeSetting = new PLAudioEncodeSetting();
        mAudioEncodeSetting.setHWCodecEnabled(true);
        mAudioEncodeSetting.setChannels(1);

        mRecordSetting = new PLRecordSetting();
        mRecordSetting.setMaxRecordDuration(RecordSettings.DEFAULT_MAX_RECORD_DURATION);
        mRecordSetting.setRecordSpeedVariable(true);
        mRecordSetting.setVideoCacheDir(Config.VIDEO_STORAGE_DIR);
        mRecordSetting.setVideoFilepath(Config.RECORD_FILE_PATH);

        mFaceBeautySetting = new PLFaceBeautySetting(1.0f, 0.5f, 0.5f);

        mShortVideoRecorder.prepare(preview, mCameraSetting, mMicrophoneSetting, mVideoEncodeSetting,
                mAudioEncodeSetting,mFaceBeautySetting, mRecordSetting);

    }


    private PLCameraSetting.CAMERA_FACING_ID chooseCameraFacingId() {
        if (PLCameraSetting.hasCameraFacing(PLCameraSetting.CAMERA_FACING_ID.CAMERA_FACING_3RD)) {
            return PLCameraSetting.CAMERA_FACING_ID.CAMERA_FACING_3RD;
        } else if (PLCameraSetting.hasCameraFacing(PLCameraSetting.CAMERA_FACING_ID.CAMERA_FACING_FRONT)) {
            return PLCameraSetting.CAMERA_FACING_ID.CAMERA_FACING_FRONT;
        } else {
            return PLCameraSetting.CAMERA_FACING_ID.CAMERA_FACING_BACK;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        mShortVideoRecorder.resume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mShortVideoRecorder.pause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mShortVideoRecorder.destroy();
    }

    /**
     * 开始录制
     * @param view
     */
    public void startRecord(View view){
        mShortVideoRecorder.beginSection();
        mSectionBeginTSMs = System.currentTimeMillis();
        Log.i(TAG, "startRecord: ");
    }

    /**
     * 结束录制
     * @param view
     */
    public void endRecord(View view){
        long sectionRecordDurationMs = System.currentTimeMillis() - mSectionBeginTSMs;
        Log.i(TAG, "endRecord: " + sectionRecordDurationMs/1000);
        mShortVideoRecorder.endSection();
    }
    
    public void saveBtn(View view){
        mShortVideoRecorder.concatSections(this);
    }

    public void backBtn(View view) {
        mShortVideoRecorder.deleteLastSection();
    }


    @Override
    public void onManualFocusStart(boolean b) {

    }

    @Override
    public void onManualFocusStop(boolean b) {

    }

    @Override
    public void onManualFocusCancel() {

    }

    @Override
    public void onAutoFocusStart() {

    }

    @Override
    public void onAutoFocusStop() {

    }

    @Override
    public void onReady() {
        Log.i(TAG, "onReady: ");
    }

    @Override
    public void onError(int i) {

    }

    @Override
    public void onDurationTooShort() {
        Log.i(TAG, "onDurationTooShort: ");
    }

    @Override
    public void onRecordStarted() {
        Log.i(TAG, "onRecordStarted: ");
    }

    @Override
    public void onSectionRecording(long l, long l1, int i) {

    }

    @Override
    public void onRecordStopped() {
        Log.i(TAG, "onRecordStopped: ");
    }

    @Override
    public void onSectionIncreased(long l, long l1, int i) {
        Log.i(TAG, "onSectionIncreased: ");
    }

    @Override
    public void onSectionDecreased(long l, long l1, int i) {
        Log.i(TAG, "onSectionDecreased: ");
    }

    @Override
    public void onRecordCompleted() {
        Log.i(TAG, "onRecordCompleted: ");
        endRecord(null);
    }

    @Override
    public void onSaveVideoSuccess(String s) {
        Log.i(TAG, "onSaveVideoSuccess: ");
    }

    @Override
    public void onSaveVideoFailed(int i) {
        Log.i(TAG, "onSaveVideoFailed: " + i);
    }   

    @Override
    public void onSaveVideoCanceled() {
        Log.i(TAG, "onSaveVideoCanceled: ");
    }

    @Override
    public void onProgressUpdate(float v) {
        Log.i(TAG, "onProgressUpdate: ");
    }
}

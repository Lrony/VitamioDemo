package com.lrony.vitamiodemo;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.Window;
import android.view.WindowManager;

import io.vov.vitamio.MediaPlayer;
import io.vov.vitamio.widget.MediaController;
import io.vov.vitamio.widget.VideoView;

/**
 * by:Lrony
 */
public class VideoViewActivity extends AppCompatActivity implements MediaPlayer.OnPreparedListener
        , MediaPlayer.OnCompletionListener, MediaPlayer.OnErrorListener {

    private static final String TAG = "VideoViewActivity";

    private VideoView mVideo;
    private MediaController mMediaController;

    private Uri path = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //定义全屏参数
        int flag = WindowManager.LayoutParams.FLAG_FULLSCREEN;
        //获得当前窗体对象
        Window window = VideoViewActivity.this.getWindow();
        //设置当前窗体为全屏显示
        window.setFlags(flag, flag);

        setContentView(R.layout.activity_video_view);

        // 初始化组件
        initView();
        // 初始化Video必要参数
        initVideo();
        // 初始化监听
        initVideoListener();
    }

    private void initView() {
        Log.d(TAG, "initView");

        mVideo = (VideoView) findViewById(R.id.videoView);
    }

    private void initVideoListener() {
        Log.d(TAG, "initVideoListener");

        mVideo.setOnPreparedListener(this);
        mVideo.setOnCompletionListener(this);
        mVideo.setOnErrorListener(this);

        // ...更多请参考API文档
    }

    /**
     * 更多API 请参考 https://www.vitamio.org/docs/API/2013/0508/9.html
     */
    private void initVideo() {
        Log.d(TAG, "initVideo");

        path = Uri.parse("http://he.yinyuetai.com/uploads/videos/common/" +
                "701E015B8FDBD50FE24A7E50004060C5.mp4");
        mVideo.setVideoURI(path);
        mMediaController = new MediaController(this);
        // 设置媒体控制器
        mVideo.setMediaController(mMediaController);
        // 设置视频质量，LOW/MEDIUM/HIGH
        mVideo.setVideoQuality(MediaPlayer.VIDEOQUALITY_HIGH);
        mVideo.requestFocus();
    }

    /**
     * 在视频预处理完成后调用。在视频预处理完成后被调用。此时视频的宽度、高度、宽高比信息已经获取到
     * ，此时可调用seekTo让视频从指定位置开始播放。
     *
     * @param mp the MediaPlayer that is ready for playback
     */
    @Override
    public void onPrepared(MediaPlayer mp) {
        Log.d(TAG, "onPrepared");

        // 开始播放
        mp.start();
    }

    /**
     * 视频播放完成后调用
     *
     * @param mp the MediaPlayer that reached the end of the file
     */
    @Override
    public void onCompletion(MediaPlayer mp) {
        Log.d(TAG, "onCompletion");
    }

    /**
     * 在异步操作调用过程中发生错误时调用。例如视频打开失败
     *
     * @param mp    the MediaPlayer the error pertains to
     * @param what  the type of error that has occurred:
     * @param extra an extra code, specific to the error. Typically implementation
     *              dependant.
     * @return
     */
    @Override
    public boolean onError(MediaPlayer mp, int what, int extra) {
        Log.d(TAG, "onError");
        return false;
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        switch (keyCode) {
            case KeyEvent.KEYCODE_BACK:
                if (null != mVideo) {
                    Log.d(TAG, "onKeyDown");
                    mVideo.stopPlayback();
                }
                break;
        }
        return super.onKeyDown(keyCode, event);
    }
}

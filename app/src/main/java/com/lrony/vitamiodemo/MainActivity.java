package com.lrony.vitamiodemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button mBtnOpenVideoView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //初始化控件
        initView();

        //初始化监听
        initClickListener();
    }

    private void initView() {
        mBtnOpenVideoView = (Button) findViewById(R.id.btn_openVideoView);

    }

    private void initClickListener() {
        mBtnOpenVideoView.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_openVideoView:
                Intent intent = new Intent();
                intent.setClass(this, VideoViewActivity.class);
                startActivity(intent);
                break;
            default:
                break;
        }
    }
}

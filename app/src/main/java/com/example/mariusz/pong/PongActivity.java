package com.example.mariusz.pong;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;


public class PongActivity extends ActionBarActivity {


    Looper mLooper;
    Handler mHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pong);

        mLooper = Looper.getMainLooper();
        mHandler = new Handler(mLooper){
            @Override
            public void handleMessage(Message msg) {
                Log.d("MAIN THREAD", "main:" + msg);
            }
        };


    }
}

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
import android.view.MotionEvent;


public class PongActivity extends ActionBarActivity {


    Looper looper;
    Handler handler;

    float initialX;
    float initialY;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pong);

        looper = Looper.getMainLooper();
        handler = new Handler(looper){
            @Override
            public void handleMessage(Message msg) {
                Log.d("MAIN THREAD", "main:" + msg);
            }
        };

    }

    @Override
    public boolean onTouchEvent(MotionEvent event){

        int action = event.getActionMasked();

        switch (action) {

            case MotionEvent.ACTION_DOWN:
                initialX = event.getX();
                initialY = event.getY();

                Log.d("EVENT", "Action was DOWN");
                break;

            case MotionEvent.ACTION_MOVE:
                Log.d("EVENT", "Action was MOVE");
                break;

            case MotionEvent.ACTION_UP:
                float finalX = event.getX();
                float finalY = event.getY();

                Log.d("EVENT", "Action was UP");

                if (initialX < finalX) {
                    Log.d("EVENT", "Left to Right swipe performed");
                }

                if (initialX > finalX) {
                    Log.d("EVENT", "Right to Left swipe performed");
                }

                if (initialY < finalY) {
                    Log.d("EVENT", "Up to Down swipe performed");
                }

                if (initialY > finalY) {
                    Log.d("EVENT", "Down to Up swipe performed");
                }

                break;

            case MotionEvent.ACTION_CANCEL:
                Log.d("EVENT","Action was CANCEL");
                break;

            case MotionEvent.ACTION_OUTSIDE:
                Log.d("EVENT", "Movement occurred outside bounds of current screen element");
                break;
        }

        return super.onTouchEvent(event);
    }
}

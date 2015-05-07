package com.example.mariusz.pong;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;


public class PongActivity extends ActionBarActivity {


    Looper looper;
    Handler handler;
    PongView pong;
    InvalidateHandler invalidateHandler;

    float initialX;
    float initialY;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pong);

        pong = (PongView) findViewById(R.id.pongView);

        handler = new Handler(Looper.getMainLooper())  {

            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                pong.invalidate();
                pong.requestLayout();
            }

        };

        invalidateHandler = new InvalidateHandler(handler);
        handler.removeCallbacks(invalidateHandler);
        handler.post(invalidateHandler);

    }

    @Override
    public boolean onTouchEvent(MotionEvent event){

        int action = event.getActionMasked();

        switch (action) {

            case MotionEvent.ACTION_DOWN:
                initialX = event.getX();
                initialY = event.getY();
                break;

            case MotionEvent.ACTION_MOVE:

                float finalX = event.getX();
                float finalY = event.getY();

                if (initialX < finalX) {
                    pong.right();
                }

                if (initialX > finalX) {
                    pong.left();
                }

                break;

            case MotionEvent.ACTION_UP:
                pong.stop();

                break;

            case MotionEvent.ACTION_CANCEL:
                break;

            case MotionEvent.ACTION_OUTSIDE:
                break;
        }

        return super.onTouchEvent(event);
    }
}

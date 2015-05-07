package com.example.mariusz.pong;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;

public class InvalidateHandler implements Runnable{

    private Handler handler;

    InvalidateHandler(Handler handler){
        this.handler = handler;
    }

    public void run(){
        handler.sendEmptyMessage(1);
        handler.post(this);
    }

}

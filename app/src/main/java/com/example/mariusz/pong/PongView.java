package com.example.mariusz.pong;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import java.util.Random;


public class PongView extends View {

    private Paint mPaint;
    private Random rand;

    private int width;
    private int height;
    private int racketWidth;
    private int racketHeight;
    private int racketSpeed;
    private int ballRadius;
    private int ballSpeed;
    private boolean gameStarted;

    private PongRacket topRacket;
    private PongRacket bottomRacket;
    private PongBall ball;

    public PongView(Context context) {
        super(context);
        init(null, 0);
    }

    public PongView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs, 0);
    }

    public PongView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(attrs, defStyle);

    }

    private void init(AttributeSet attrs, int defStyle) {
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        rand = new Random();

        mPaint.setStyle(Paint.Style.FILL);

        gameStarted = false;

        racketWidth = 200;
        racketHeight = 50;
        racketSpeed = 4;

        ballRadius = 50;
        ballSpeed = 10;


        topRacket = new PongRacket(racketWidth, racketHeight, racketSpeed);
        bottomRacket = new PongRacket(racketWidth, racketHeight, racketSpeed);
        ball = new PongBall(ballRadius);
    }

    protected void onDraw(Canvas canvas){
        super.onDraw(canvas);

        canvas.drawRGB(155, 155, 155);

        if(!gameStarted){
            width = canvas.getWidth();
            height = canvas.getHeight();

            topRacket.x = width / 2 - topRacket.width / 2;
            topRacket.y = 0;

            bottomRacket.x = width / 2 - bottomRacket.width /2;
            bottomRacket.y = height - bottomRacket.height;

            int randomNumber = rand.nextInt(2);



            if(randomNumber == 0){
                ball.y = racketHeight + ball.radius;
                ball.velocityY = ballSpeed;
            }
            else {
                ball.y = height - racketHeight - ball.radius;
                ball.velocityY = -ballSpeed;
            }

            ball.x = width/2;

            gameStarted = true;
        }

        ball.y += ball.velocityY;


        //Log.d("Width", Integer.toString(width));
        //Log.d("Height", Integer.toString(height));

        mPaint.setColor(Color.RED);
        canvas.drawRect(topRacket.x, topRacket.y, topRacket.x + topRacket.width, topRacket.y + topRacket.height, mPaint);
        canvas.drawRect(bottomRacket.x, bottomRacket.y, bottomRacket.x + bottomRacket.width, bottomRacket.y + bottomRacket.height, mPaint);

        mPaint.setColor(Color.BLUE);
        canvas.drawCircle(ball.x, ball.y, ball.radius, mPaint);

        invalidate();
        requestLayout();
    }
}

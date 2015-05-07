package com.example.mariusz.pong;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import android.util.AttributeSet;
import android.view.View;
import android.widget.Toast;

import java.util.Random;


public class PongView extends View {

    private Paint mPaint;
    private Random rand;
    private Activity activity;

    private int width;
    private int height;
    private int racketWidth;
    private int racketHeight;
    private int racketSpeed;
    private int ballRadius;
    private int ballSpeed;
    private boolean gameStarted;
    private boolean initialized;
    private boolean playing;
    private boolean left;
    private boolean right;

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

        activity = (Activity) context;
    }

    public PongView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(attrs, defStyle);

    }

    private void init(AttributeSet attrs, int defStyle) {
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        rand = new Random();

        mPaint.setStyle(Paint.Style.FILL);

        initialized = false;
        gameStarted = false;
        playing = false;
        left = false;
        right = false;
        racketWidth = 200;
        racketHeight = 50;
        racketSpeed = 10;

        ballRadius = 40;
        ballSpeed = 10;


        topRacket = new PongRacket(racketWidth, racketHeight, racketSpeed - 1);
        bottomRacket = new PongRacket(racketWidth, racketHeight, racketSpeed);
        ball = new PongBall(ballRadius);
    }

    protected void onDraw(Canvas canvas){
        super.onDraw(canvas);

        canvas.drawRGB(155, 155, 155);

        if(!initialized) {
            width = canvas.getWidth();
            height = canvas.getHeight();

            initialized = true;
        }

        if(!gameStarted){
            topRacket.x = width / 2 - topRacket.width / 2;
            topRacket.y = 0;

            bottomRacket.x = width / 2 - bottomRacket.width / 2;
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

            randomNumber = rand.nextInt(2);

            if(randomNumber == 0){
                ball.velocityX = ballSpeed;
            }
            else{
                ball.velocityX = -ballSpeed;
            }

            ball.x = width/2;
            gameStarted = true;
        }

        if(playing) {
            ball.y += ball.velocityY;

            ball.x += ball.velocityX;

            if(ball.x < topRacket.x + topRacket.width / 2 && topRacket.x > 0){
                topRacket.x -= topRacket.speed;
            }

            if(ball.x > topRacket.x + topRacket.width / 2 && topRacket.x + topRacket.width < width){
                topRacket.x += topRacket.speed;
            }

            if (ball.y <= 0) {
                gameStarted = false;
                playing = false;
                Toast.makeText(activity, "Wygrałeś!", Toast.LENGTH_SHORT).show();
                return;
            }

            if (ball.y >= height) {
                gameStarted = false;
                playing = false;
                Toast.makeText(activity, "Przegrałeś!", Toast.LENGTH_SHORT).show();
                return;
            }

            if (ball.x <= 0 || ball.x >= width) {
                ball.velocityX *= -1;
            }

            if ((ball.y - ball.radius <= topRacket.y + topRacket.height && ball.x >= topRacket.x && ball.x <= topRacket.x + topRacket.width)
                    || (ball.y + ball.radius >= bottomRacket.y && ball.x >= bottomRacket.x && ball.x <= bottomRacket.x + bottomRacket.width)) {
                ball.velocityY *= -1;
            }

            if (left && bottomRacket.x > 0) {
                bottomRacket.x -= bottomRacket.speed;
            }

            if (right && bottomRacket.x + bottomRacket.width < width) {
                bottomRacket.x += bottomRacket.speed;
            }
        }

        mPaint.setColor(Color.RED);
        canvas.drawRect(topRacket.x, topRacket.y, topRacket.x + topRacket.width, topRacket.y + topRacket.height, mPaint);
        canvas.drawRect(bottomRacket.x, bottomRacket.y, bottomRacket.x + bottomRacket.width, bottomRacket.y + bottomRacket.height, mPaint);

        mPaint.setColor(Color.BLUE);
        canvas.drawCircle(ball.x, ball.y, ball.radius, mPaint);
    }

    public void left(){
        right = false;
        left = true;
    }

    public void right(){
        left = false;
        right = true;
    }

    public void stop(){
        left = false;
        right = false;
        playing = true;
    }
}

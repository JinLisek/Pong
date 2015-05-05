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

        racketWidth = 400;
        racketHeight = 50;
        racketSpeed = 4;

        ballRadius = 50;
        ballSpeed = 5;


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
            }
            else {
                ball.y = height - racketHeight - ball.radius;
            }

            ball.x = width/2;

            gameStarted = true;
        }


        //Log.d("Width", Integer.toString(width));
        //Log.d("Height", Integer.toString(height));

        mPaint.setColor(Color.RED);
        canvas.drawRect(topRacket.x, topRacket.y, topRacket.x + topRacket.width, topRacket.y + topRacket.height, mPaint);
        canvas.drawRect(bottomRacket.x, bottomRacket.y, bottomRacket.x + bottomRacket.width, bottomRacket.y + bottomRacket.height, mPaint);

        mPaint.setColor(Color.BLUE);
        canvas.drawCircle(ball.x, ball.y, ball.radius, mPaint);
    }
}

/*
public class PongView extends View {
    private int mExampleColor = Color.RED; //
    private float mExampleDimension = 0; //
    private Drawable mExampleDrawable;

    private TextPaint mTextPaint;
    private float mTextWidth;
    private float mTextHeight;

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
        // Load attributes
        final TypedArray a = getContext().obtainStyledAttributes(
                attrs, R.styleable.PongView, defStyle, 0);

        mExampleString = a.getString(
                R.styleable.PongView_exampleString);
        mExampleColor = a.getColor(
                R.styleable.PongView_exampleColor,
                mExampleColor);
        // Use getDimensionPixelSize or getDimensionPixelOffset when dealing with
        // values that should fall on pixel boundaries.
        mExampleDimension = a.getDimension(
                R.styleable.PongView_exampleDimension,
                mExampleDimension);

        if (a.hasValue(R.styleable.PongView_exampleDrawable)) {
            mExampleDrawable = a.getDrawable(
                    R.styleable.PongView_exampleDrawable);
            mExampleDrawable.setCallback(this);
        }

        a.recycle();

        // Set up a default TextPaint object
        mTextPaint = new TextPaint();
        mTextPaint.setFlags(Paint.ANTI_ALIAS_FLAG);
        mTextPaint.setTextAlign(Paint.Align.LEFT);

        // Update TextPaint and text measurements from attributes
        invalidateTextPaintAndMeasurements();
    }

    private void invalidateTextPaintAndMeasurements() {
        mTextPaint.setTextSize(mExampleDimension);
        mTextPaint.setColor(mExampleColor);
        mTextWidth = mTextPaint.measureText(mExampleString);

        Paint.FontMetrics fontMetrics = mTextPaint.getFontMetrics();
        mTextHeight = fontMetrics.bottom;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);


        // allocations per draw cycle.
        int paddingLeft = getPaddingLeft();
        int paddingTop = getPaddingTop();
        int paddingRight = getPaddingRight();
        int paddingBottom = getPaddingBottom();

        int contentWidth = getWidth() - paddingLeft - paddingRight;
        int contentHeight = getHeight() - paddingTop - paddingBottom;

        // Draw the text.
        canvas.drawText(mExampleString,
                paddingLeft + (contentWidth - mTextWidth) / 2,
                paddingTop + (contentHeight + mTextHeight) / 2,
                mTextPaint);

        // Draw the example drawable on top of the text.
        if (mExampleDrawable != null) {
            mExampleDrawable.setBounds(paddingLeft, paddingTop,
                    paddingLeft + contentWidth, paddingTop + contentHeight);
            mExampleDrawable.draw(canvas);
        }

        invalidate();
        requestLayout();
    }

    /**
     * Gets the example string attribute value.
     *
     * @return The example string attribute value.
     */
/*
    public String getExampleString() {
        return mExampleString;
    }

    /**
     * Sets the view's example string attribute value. In the example view, this string
     * is the text to draw.
     *
     * @param exampleString The example string attribute value to use.
     */
/*
    public void setExampleString(String exampleString) {
        mExampleString = exampleString;
        invalidateTextPaintAndMeasurements();
    }

    /**
     * Gets the example color attribute value.
     *
     * @return The example color attribute value.
     */
/*
    public int getExampleColor() {
        return mExampleColor;
    }

    /**
     * Sets the view's example color attribute value. In the example view, this color
     * is the font color.
     *
     * @param exampleColor The example color attribute value to use.
     */
/*
    public void setExampleColor(int exampleColor) {
        mExampleColor = exampleColor;
        invalidateTextPaintAndMeasurements();
    }

    /**
     * Gets the example dimension attribute value.
     *
     * @return The example dimension attribute value.
     */
/*
    public float getExampleDimension() {
        return mExampleDimension;
    }

    /**
     * Sets the view's example dimension attribute value. In the example view, this dimension
     * is the font size.
     *
     * @param exampleDimension The example dimension attribute value to use.
     */
/*
    public void setExampleDimension(float exampleDimension) {
        mExampleDimension = exampleDimension;
        invalidateTextPaintAndMeasurements();
    }

    /**
     * Gets the example drawable attribute value.
     *
     * @return The example drawable attribute value.
     */
/*
    public Drawable getExampleDrawable() {
        return mExampleDrawable;
    }

    /**
     * Sets the view's example drawable attribute value. In the example view, this drawable is
     * drawn above the text.
     *
     * @param exampleDrawable The example drawable attribute value to use.
     */
/*
    public void setExampleDrawable(Drawable exampleDrawable) {
        mExampleDrawable = exampleDrawable;
    }
}*/

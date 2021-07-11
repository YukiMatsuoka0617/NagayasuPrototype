package com.example.nagayasuprototype;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

public class PaintView extends View {
    private Paint mPaint;
    private Path mPath;
    private Bitmap bmp = null;
    private static Bitmap bmpResize = null;
    private static Bitmap bmpCut = null;
    private Bitmap bmpBack = null;

    double ratio = 0.8;

    float startX = 0;
    float startY = 0;
    float endX = 0;
    float endY = 0;

    int canvasX;
    int canvasY;
    int canvasXResize;
    int canvasYResize;

    public PaintView(Context context) {
        super(context);
    }

    public PaintView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mPath = new Path();

        mPaint = new Paint();
        mPaint.setColor(0xFFFF0000);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeJoin(Paint.Join.ROUND);
        mPaint.setStrokeCap(Paint.Cap.ROUND);
        mPaint.setStrokeWidth(10);

        bmp = BitmapFactory.decodeResource(getResources(), R.drawable.test);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvasX = getWidth();
        canvasY = getHeight();
        canvasXResize = (int) (getWidth() * ratio);
        canvasYResize = (int) (getHeight() * ratio);

        bmpBack = Bitmap.createBitmap(canvasX, canvasY, Bitmap.Config.ARGB_8888);
        bmpResize = Bitmap.createScaledBitmap(bmp, canvasXResize, canvasYResize, false);
        canvas.drawBitmap(bmpResize,
                (canvasX - canvasXResize) / 2,
                (canvasY - canvasYResize) / 2,
                mPaint);
        canvas.drawRect(startX, startY, endX, endY, mPaint);

        Canvas c = new Canvas(bmpBack);
        c.drawBitmap(bmpResize,
                (canvasX - canvasXResize) / 2,
                (canvasY - canvasYResize) / 2,
                mPaint);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                startX = event.getX();
                startY = event.getY();
                endX = event.getX();
                endY = event.getY();
                invalidate();
                break;
            case MotionEvent.ACTION_MOVE:
                endX = event.getX();
                endY = event.getY();
                invalidate();
                break;
            case MotionEvent.ACTION_UP:
                endX = event.getX();
                endY = event.getY();
                invalidate();
                bmpCut = Bitmap.createBitmap(
                        bmpBack,
                        (int) startX, (int) startY,
                        Math.abs((int) (endX - startX)), Math.abs((int) (endY - startY)),
                        null, true);
                break;
        }
        return true;
    }

    public static Bitmap getBitmap() {
        return bmpCut;
    }

}

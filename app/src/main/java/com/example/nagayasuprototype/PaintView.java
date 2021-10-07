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
    Paint paint;
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

    static int[] vertex;

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

        paint = new Paint();
        paint.setColor(0xFF00FF00);
        paint.setStyle(Paint.Style.FILL_AND_STROKE);
        paint.setStrokeWidth(5);
        paint.setTextSize(100);

        bmp = OpenFileViewer.getBitmap();

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
        if(MainActivity2.arrayList.size() != 0) {
            canvas.drawText(String.valueOf(MainActivity2.arrayList.size() + 1), startX, startY + 80, paint);
        }


        Canvas c = new Canvas(bmpBack);
        c.drawBitmap(bmpResize,
                (canvasX - canvasXResize) / 2,
                (canvasY - canvasYResize) / 2,
                mPaint);


        for (int[] vertex : MainActivity2.arrayList) {
            canvas.drawRect(vertex[0], vertex[1], vertex[2], vertex[3], mPaint);
            canvas.drawText(String.valueOf(vertex[4] + 1), vertex[0], vertex[1]+80, paint);
        }

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
                bmpCut = Bitmap.createBitmap(
                        bmpBack,
                        makeUpperLeft(startX, endX), makeUpperLeft(startY, endY),
                        Math.abs((int) (endX - startX)), Math.abs((int) (endY - startY)),
                        null, true);

                vertex = new int[]{makeUpperLeft(startX, endX), makeUpperLeft(startY, endY),
                        makeUnderRight(startX, endX), makeUnderRight(startY, endY),
                        MainActivity2.arrayList.size()};
                invalidate();
                break;
        }
        return true;
    }

    public static Bitmap getBitmap() {
        return bmpCut;
    }

    public static int[] getVertex(){
        return vertex;
    }

    private int makeUpperLeft(float start, float end) {
        if (start > end) {
            return (int) end;
        } else {
            return (int) start;
        }
    }

    private int makeUnderRight(float start, float end) {
        if (start < end) {
            return (int) end;
        } else {
            return (int) start;
        }
    }

}

package com.example.administrator.mynumberpicker;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by Administrator on 2017/7/15.
 */
public class MyNumberPicker extends View {
    private static float startPoint_X;
    private static float startPoint_Y;
    private static float endPoint_X;
    private static float endPoint_Y;
    private TextPaint highlightTextPaint;
    private TextPaint besideTextPaint;
    private  float highlightsTextSize;
    private  float besideTextSize;
    private int highlightTextColor;
    private int besideTextColor;
    private String[] arr = {"10","20","30","40","50","60"};

    public MyNumberPicker(Context context, AttributeSet attrs) {
        super(context, attrs);

        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.MyNumberPicker);
        highlightTextColor = typedArray.getColor(R.styleable.MyNumberPicker_highlightTextColor,0XFFFFFFFF);
        besideTextColor = typedArray.getColor(R.styleable.MyNumberPicker_besideTextColor,0XFFFFFFFF);
        highlightsTextSize = typedArray.getDimension(R.styleable.MyNumberPicker_highlightsTextSize, 36);
        besideTextSize = typedArray.getDimension(R.styleable.MyNumberPicker_besideTextSize,36);
        typedArray.recycle();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
    }

    public String gethighlightString(){
        return arr[2];
    }

    public void setArr(int highLightText){
        if(highLightText ==10){
            arr[0] = "50";
            arr[1] = "60";
            arr[2] = "10";
            arr[3] = "20";
            arr[4] = "30";
            arr[5] = "40";
        }else if(highLightText ==20){
            arr[0] = "60";
            arr[1] = "10";
            arr[2] = "20";
            arr[3] = "30";
            arr[4] = "40";
            arr[5] = "50";
        }else if(highLightText ==30){
            arr[0] = "10";
            arr[1] = "20";
            arr[2] = "30";
            arr[3] = "40";
            arr[4] = "50";
            arr[5] = "60";
        }else if(highLightText ==40){
            arr[0] = "20";
            arr[1] = "30";
            arr[2] = "40";
            arr[3] = "50";
            arr[4] = "60";
            arr[5] = "10";
        }else if(highLightText ==50){
            arr[0] = "30";
            arr[1] = "40";
            arr[2] = "50";
            arr[3] = "60";
            arr[4] = "10";
            arr[5] = "20";
        }else if(highLightText ==60){
            arr[0] = "40";
            arr[1] = "50";
            arr[2] = "60";
            arr[3] = "10";
            arr[4] = "20";
            arr[5] = "30";
        }
        this.invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        besideTextPaint = new TextPaint(Paint.ANTI_ALIAS_FLAG | Paint.DEV_KERN_TEXT_FLAG);
        besideTextPaint.setTextSize(highlightsTextSize);
        besideTextPaint.setTypeface(Typeface.DEFAULT_BOLD);// text width
        besideTextPaint.setColor(besideTextColor);
        for(int i=0;i<arr.length;i++){
            if(i!=2){
                canvas.drawText(arr[i], i*100, 50, besideTextPaint);
            }
        }

        highlightTextPaint = new TextPaint(Paint.ANTI_ALIAS_FLAG | Paint.DEV_KERN_TEXT_FLAG);
        highlightTextPaint.setTextSize(besideTextSize);
        highlightTextPaint.setTypeface(Typeface.DEFAULT_BOLD);// text width
        highlightTextPaint.setColor(highlightTextColor);
        canvas.drawText(arr[2],200,50,highlightTextPaint);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch(event.getAction()){
            case MotionEvent.ACTION_DOWN:
                startPoint_X = event.getX();
                startPoint_Y = event.getY();
                break;
            case MotionEvent.ACTION_MOVE:

                break;
            case MotionEvent.ACTION_UP:
                endPoint_X = event.getX();
                endPoint_Y = event.getY();
                updateArray(startPoint_X,endPoint_X);
                break;
        }
        return true;
    }

    public void updateArray(float startPoint_X,float endPoint_X){
        String temp;
        if(startPoint_X > endPoint_X){
            temp =arr[0];
            for(int i = 0;i<arr.length-1;i++){
                arr[i] = arr[i+1];
            }
            arr[5] = temp;
            Log.d("Chunna.zheng", "output the arr[] data");
            for(int i = 0;i<arr.length;i++){
                Log.d("Chunna.zheng","arr["+i+"] = "+arr[i]);
            }
        }
        if(startPoint_X < endPoint_X){
            temp =arr[5];
            for(int i = 5;i>0;i--){
                arr[i] = arr[i-1];
            }
            arr[0] = temp;
            Log.d("Chunna.zheng","output the arr[] data");
            for(int i = 0;i<arr.length;i++){
                Log.d("Chunna.zheng","arr["+i+"] = "+arr[i]);
            }
        }
        this.invalidate();
    }

}

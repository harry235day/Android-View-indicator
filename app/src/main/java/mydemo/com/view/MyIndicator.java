package mydemo.com.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by fangmaster on 2017/11/14.
 */

public class MyIndicator extends View {

    private Paint mbgPaint;
    private Paint mCurrentPaint;
    private int Number;
    private float offSet;
    private int mRadius = 10;
    private int mbg ;
    private int mforWordbg ;
    private int position;

    public MyIndicator(Context context) {
        super(context);
    }

    public MyIndicator(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        //初始化画笔

        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.MyIndicator);
        mbg = array.getColor(R.styleable.MyIndicator_Indicator_backbg, mbg);
        mforWordbg = array.getColor(R.styleable.MyIndicator_Indicator_forwordbg, mforWordbg);
        mRadius = array.getInteger(R.styleable.MyIndicator_Indicator_radius, mRadius);

        initPaint();
    }

    private void initPaint() {
        mbgPaint = new Paint();
        //抗锯齿
        mbgPaint.setAntiAlias(true);
        //
        mbgPaint.setColor(mbg);
        mbgPaint.setStrokeWidth(2);
        //只会描边
        mbgPaint.setStyle(Paint.Style.FILL_AND_STROKE);


        mCurrentPaint = new Paint();
        //抗锯齿
        mCurrentPaint.setAntiAlias(true);
        //
        mCurrentPaint.setColor(mforWordbg);
        mCurrentPaint.setStrokeWidth(2);
        //只会描边
        mCurrentPaint.setStyle(Paint.Style.FILL_AND_STROKE);

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        setMeasuredDimension(measureWidth(widthMeasureSpec), measureHeight(heightMeasureSpec));
//        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //循环创建
        if (Number > 1) {
            for (int i = 0; i < Number; i++) {
                canvas.drawCircle(30 + i * mRadius * 3, 30, mRadius, mbgPaint);
            }
            //水平偏移量
            if (Number == (position+1)) {
                canvas.drawCircle(30+position * 3 * mRadius , 30, mRadius, mCurrentPaint);
            } else {
                canvas.drawCircle(30 + offSet, 30, mRadius, mCurrentPaint);
            }
        }
    }

    //控制点的数量
    public void setNumberAndOffSet(int number, int position, float offSet) {
        this.Number = number;
        this.position = position;
        this.offSet = position * 3 * mRadius + offSet * 3 * mRadius;
        invalidate();
    }

    public int measureWidth(int measureSpec) {
        int result = 0;
        int specMode = MeasureSpec.getMode(measureSpec);
        int specSize = MeasureSpec.getSize(measureSpec);
        if (specMode == MeasureSpec.EXACTLY) {
            result = specSize;
        } else {
            result = specSize;
            if (specMode == MeasureSpec.AT_MOST) {
                result = Math.min(result, specSize);
            }
        }
        return result;
    }

    public int measureHeight(int measureSpec) {
        int result = 0;
        int specMode = MeasureSpec.getMode(measureSpec);
        int specSize = MeasureSpec.getSize(measureSpec);
        if (specMode == MeasureSpec.EXACTLY) {
            result = specSize;
        } else {
            result = specSize;
            if (specMode == MeasureSpec.AT_MOST) {
                result = Math.min(result, specSize);
            }
        }
        return result;
    }

}

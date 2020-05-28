package library.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.Shader;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;


import com.dwz.mvvmdemo.R;

import library.utils.LogUtils;

/**
 * @author Administrator
 * @Create 2019/12/11
 * @Description TODO
 * @zmf 上面长方形.下面圆弧 用于我的页面圆弧背景
 */
public class ArcView extends View {
    private int mWidth;
    private int mHeight;

    private int mArcHeight; //圆弧的高度
    private int mBgColor = Color.WHITE;   //背景颜色
    private int mLastColor = Color.WHITE; //变化的最终颜色  该值为-1,表示不需要渐变
    private Paint mPaint;  //画笔
    private LinearGradient linearGradient;
    private Rect rect = new Rect(0, 0, 0, 0);//普通的矩形
    private Path path = new Path();//用来绘制曲面

    public ArcView(Context context) {
        this(context, null);
    }

    public ArcView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ArcView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.ArcView);
        mArcHeight = typedArray.getDimensionPixelSize(R.styleable.ArcView_arcHeight, 0);
        mBgColor = typedArray.getColor(R.styleable.ArcView_bgFillColor, mBgColor);
        mLastColor = typedArray.getColor(R.styleable.ArcView_lgColor, mLastColor);
        mPaint = new Paint();
        typedArray.recycle();
    }

    public void setColor(int bgColor, int lastColor) {
        mBgColor = bgColor;
        mLastColor = lastColor;

        mPaint.reset();
        invalidate();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        LogUtils.logd("onSizeChanged");
//        linearGradient = new LinearGradient(0, 0, getMeasuredWidth(), 0,
//                mBgColor, mLastColor, Shader.TileMode.CLAMP);
//        mPaint.setShader(linearGradient);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        setPaint();
        //绘制矩形
        rect.set(0, 0, mWidth, mHeight - mArcHeight);
        canvas.drawRect(rect, mPaint);

//        canvas.setDrawFilter(new PaintFlagsDrawFilter(0, Paint.ANTI_ALIAS_FLAG | Paint.FILTER_BITMAP_FLAG));
        //绘制路径
        path.moveTo(0, mHeight - mArcHeight);
        path.quadTo(mWidth >> 1, mHeight, mWidth, mHeight - mArcHeight);
        canvas.drawPath(path, mPaint);

    }

    public void setPaint() {
        mPaint.setColor(mBgColor);
        mPaint.setFilterBitmap(true);
        mPaint.setAntiAlias(true);
        mPaint.setDither(true);
        //设置成填充
        mPaint.setStyle(Paint.Style.FILL);
        //横向渐变
        if (mLastColor != -1) {
            linearGradient = new LinearGradient(0, 0, getMeasuredWidth(), 0,
                    mBgColor, mLastColor, Shader.TileMode.CLAMP);
            mPaint.setShader(linearGradient);
        }
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);

        if (widthMode == MeasureSpec.EXACTLY) {
            mWidth = widthSize;
        }
        if (heightMode == MeasureSpec.EXACTLY) {
            mHeight = heightSize;
        }
        setMeasuredDimension(mWidth, mHeight);
    }

//    用法
//     <library.widget.ArcView
//    android:id="@+id/halfCircleBg"
//    android:layout_width="match_parent"
//    android:layout_height="@dimen/dim550"
//    app:arcHeight="@dimen/dim130"
//    app:bgFillColor="@color/c461e4c"
//    app:lgColor="@color/c461e4c" />

    //代码里面改变颜色
//       vm.bind.halfCircleBg.setColor(Color.parseColor(colors[i]),
//            Color.parseColor(colors[i]));
}

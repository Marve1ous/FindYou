package com.example.hp.findyou;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.Button;


public class Mybutton extends  Button{
    private Drawable drawable;
    private float drawablewidth;
    private float drawableheight;
    private float buttonheight;
    public Mybutton(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.my);
        drawable = a.getDrawable(R.styleable.my_drawable);
        drawablewidth = drawable.getIntrinsicWidth();
        drawableheight = drawable.getIntrinsicHeight();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        buttonheight = MeasureSpec.getSize(heightMeasureSpec);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawable.setBounds(10, 10, (int)((buttonheight-20)/drawableheight*drawablewidth+10), (int)(buttonheight-10));
        drawable.draw(canvas);
    }
}

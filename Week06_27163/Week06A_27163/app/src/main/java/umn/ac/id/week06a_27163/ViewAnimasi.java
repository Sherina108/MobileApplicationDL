package umn.ac.id.week06a_27163;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.LinearInterpolator;

import androidx.annotation.Nullable;
import androidx.interpolator.view.animation.LinearOutSlowInInterpolator;

public class ViewAnimasi extends View {
    private static final int DURASI = 4000;
    private static final long DELAY = 1000;
    private static final int PENGATURAN_WARNA = 5;
    private float mX;
    private float mY;
    private float mRadius;
    private final Paint mPaint = new Paint();
    private AnimatorSet mAnimatiorSet = new AnimatorSet();

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        ObjectAnimator membesar = ObjectAnimator.ofFloat(this,
                "radius", 0, getWidth());
        membesar.setDuration(DURASI);
        membesar.setInterpolator(new LinearInterpolator());

        ObjectAnimator mengecil = ObjectAnimator.ofFloat(this,
                "radius", getWidth(), 0);
        mengecil.setDuration(DURASI);
        mengecil.setInterpolator(new LinearOutSlowInInterpolator());
        mengecil.setStartDelay(DELAY);

        ObjectAnimator ulang = ObjectAnimator.ofFloat(this,
                "radius", 0, getWidth());
        ulang.setStartDelay(DELAY);
        ulang.setDuration(DURASI);
        ulang.setRepeatCount(1);
        ulang.setRepeatMode(ValueAnimator.REVERSE);
        mAnimatiorSet.play(membesar).before(mengecil);
        mAnimatiorSet.play(ulang).after(mengecil);
    }

    public void setRadius(float radius){
        mRadius = radius;
        mPaint.setColor(Color.GREEN + (int) radius / PENGATURAN_WARNA);
        invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawCircle(mX,mY,mRadius,mPaint);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if(event.getActionMasked() == MotionEvent.ACTION_DOWN) {
            mX = event.getX();
            mY = event.getY();
            if (mAnimatiorSet != null && mAnimatiorSet.isRunning()) {
                mAnimatiorSet.cancel();
            }
            mAnimatiorSet.start();
        }
        return super.onTouchEvent(event);
    }

    public ViewAnimasi(Context context) {
        super(context);
    }

    public ViewAnimasi(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }
}
